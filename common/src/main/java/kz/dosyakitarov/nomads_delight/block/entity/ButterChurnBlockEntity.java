package kz.dosyakitarov.nomads_delight.block.entity;

import kz.dosyakitarov.nomads_delight.block.ButterChurnBlock;
import kz.dosyakitarov.nomads_delight.client.ButterChurnClientEffects;
import kz.dosyakitarov.nomads_delight.crafting.ChurnRecipes;
import kz.dosyakitarov.nomads_delight.crafting.ChurnRecipes.ChurnRecipe;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

/**
 * State holder for the butter churn. Lives in the LOWER half only; the upper half has no
 * block entity and only carries the {@link ButterChurnBlock#FILLED} property for its model.
 * <p>
 * Networking follows Farmer's Delight's {@code SyncedBlockEntity}: the full tag is sent on
 * discrete events (insert, extract, ready) via {@link #contentChanged()}, never per tick.
 * Manual plunges are announced with a block event so the client can animate without a sync.
 */
public class ButterChurnBlockEntity extends BlockEntity {

    // --- Tuning constants -------------------------------------------------------------

    /** Ticks the plunger takes to travel down after a manual click. */
    public static final int DOWN_STROKE_TICKS = 8;
    /** Ticks the plunger takes to return to rest after the impact. */
    public static final int UP_STROKE_TICKS = 12;
    /** Full plunge length; also the cooldown, so the plunger is at rest before the next click. */
    public static final int PLUNGE_TICKS = DOWN_STROKE_TICKS + UP_STROKE_TICKS;
    public static final int MANUAL_COOLDOWN_TICKS = PLUNGE_TICKS;
    /** Progress added per accepted manual click, in ticks of passive churning. */
    public static final int PROGRESS_PER_CLICK = 200;
    /** How far the plunger travels down on a manual plunge, in blocks. */
    public static final float STROKE_DEPTH = 6.0F / 16.0F;
    /** Subtle bob while churning passively, in blocks and ticks per cycle. */
    public static final float IDLE_BOB_DEPTH = 1.0F / 16.0F;
    public static final int IDLE_BOB_PERIOD_TICKS = 40;
    /** White droplets spawned at the bottom of each manual plunge, and their size multiplier. */
    public static final int IMPACT_PARTICLES = 3;
    public static final float IMPACT_PARTICLE_SCALE = 0.3F;

    /** Block event id sent to clients when a manual plunge starts. */
    public static final int EVENT_PLUNGE = 1;

    private static final String TAG_CONTENT = "Content";
    private static final String TAG_PROGRESS = "Progress";
    private static final String TAG_CHURN_TIME = "ChurnTime";

    // --- Persistent state ------------------------------------------------------------

    /** The inserted milk bucket, or empty. */
    private ItemStack content = ItemStack.EMPTY;
    private int progress;
    private int churnTime;

    // --- Transient state -------------------------------------------------------------

    @Nullable
    private ChurnRecipe cachedRecipe;
    /** Server only: ticks until the next manual click is accepted. */
    private int manualCooldown;
    /** Client only: ticks remaining in the current plunge animation, 0 when at rest. */
    private int plungeTicks;

    public ButterChurnBlockEntity(BlockPos pos, BlockState state) {
        super(NomadsDelightBlockEntityTypes.BUTTER_CHURN.get(), pos, state);
    }

    // --- Ticking --------------------------------------------------------------------

    public static void serverTick(Level level, BlockPos pos, BlockState state, ButterChurnBlockEntity churn) {
        if (churn.manualCooldown > 0) {
            churn.manualCooldown--;
        }
        if (churn.isChurning()) {
            churn.progress++;
            churn.checkReady();
        }
    }

    public static void clientTick(Level level, BlockPos pos, BlockState state, ButterChurnBlockEntity churn) {
        if (churn.plungeTicks > 0) {
            churn.plungeTicks--;
            if (churn.plungeTicks == UP_STROKE_TICKS) {
                churn.onPlungeImpact(level, pos);
            }
        }
    }

    /** Client: sound and splash at the bottom of the down-stroke, where the impact lands. */
    private void onPlungeImpact(Level level, BlockPos pos) {
        RandomSource random = level.random;
        double x = pos.getX() + 0.5D;
        double y = pos.getY() + 1.0D + 2.0D / 16.0D;
        double z = pos.getZ() + 0.5D;
        level.playLocalSound(x, y, z, SoundEvents.SLIME_BLOCK_STEP, SoundSource.BLOCKS,
                0.8F, 0.9F + random.nextFloat() * 0.2F, false);
        ButterChurnClientEffects.spawnImpactDroplets(level, x, y, z, IMPACT_PARTICLES, IMPACT_PARTICLE_SCALE);
    }

    // --- Interaction ----------------------------------------------------------------

    /**
     * Server: tries to insert the held stack as churn input.
     *
     * @return true if the stack was accepted (the caller handles feedback)
     */
    public boolean tryInsert(ItemStack stack, Player player) {
        if (!content.isEmpty()) {
            return false;
        }
        ChurnRecipe recipe = ChurnRecipes.forInput(stack).orElse(null);
        if (recipe == null) {
            return false;
        }
        content = stack.copyWithCount(1);
        cachedRecipe = recipe;
        churnTime = recipe.churnTicks();
        progress = 0;
        manualCooldown = 0;
        if (!player.isCreative()) {
            stack.shrink(1);
            player.getInventory().placeItemBackInInventory(new ItemStack(Items.BUCKET));
        }
        setFilled(true);
        contentChanged();
        return true;
    }

    /** Client-side prediction of {@link #tryInsert}: decides whether the hand should swing. */
    public boolean tryInsertPreview(ItemStack stack) {
        return content.isEmpty() && ChurnRecipes.forInput(stack).isPresent();
    }

    /** Client-side prediction of {@link #tryExtract}. */
    public boolean tryExtractPreview(ItemStack tool) {
        ChurnRecipe recipe = getRecipe();
        return recipe != null && isReady() && recipe.matchesTool(tool);
    }

    /**
     * Server: tries to take the finished product out with the held tool.
     *
     * @return the recipe that was completed, or null if nothing happened
     */
    @Nullable
    public ChurnRecipe tryExtract(ItemStack tool, Player player) {
        ChurnRecipe recipe = getRecipe();
        if (recipe == null || !isReady() || !recipe.matchesTool(tool)) {
            return null;
        }
        tool.shrink(1);
        player.getInventory().placeItemBackInInventory(recipe.createResult());
        clearContent();
        return recipe;
    }

    /**
     * Server: one manual plunge. Rejected while empty, ready, or still on cooldown.
     *
     * @return true if progress was added
     */
    public boolean tryManualChurn() {
        if (!isChurning() || manualCooldown > 0 || level == null) {
            return false;
        }
        manualCooldown = MANUAL_COOLDOWN_TICKS;
        progress = Math.min(progress + PROGRESS_PER_CLICK, churnTime);
        level.blockEvent(worldPosition, getBlockState().getBlock(), EVENT_PLUNGE, 0);
        setChanged();
        checkReady();
        return true;
    }

    @Override
    public boolean triggerEvent(int id, int param) {
        if (id == EVENT_PLUNGE) {
            plungeTicks = PLUNGE_TICKS;
            return true;
        }
        return super.triggerEvent(id, param);
    }

    // --- Queries --------------------------------------------------------------------

    public boolean hasContent() {
        return !content.isEmpty();
    }

    public boolean isReady() {
        return hasContent() && progress >= churnTime;
    }

    public boolean isChurning() {
        return hasContent() && progress < churnTime;
    }

    public int getProgress() {
        return progress;
    }

    public int getChurnTime() {
        return churnTime;
    }

    /** Client: ticks remaining in the manual plunge animation, 0 at rest. */
    public int getPlungeTicks() {
        return plungeTicks;
    }

    /** 0 empty, 8 churning, 15 ready. */
    public int getComparatorOutput() {
        if (!hasContent()) {
            return 0;
        }
        return isReady() ? 15 : 8;
    }

    @Nullable
    private ChurnRecipe getRecipe() {
        if (cachedRecipe == null && hasContent()) {
            cachedRecipe = ChurnRecipes.forInput(content).orElse(null);
        }
        return cachedRecipe;
    }

    // --- Internal state changes -----------------------------------------------------

    private void checkReady() {
        if (level == null || level.isClientSide || !hasContent() || progress < churnTime) {
            return;
        }
        progress = churnTime;
        level.playSound(null, worldPosition.getX() + 0.5D, worldPosition.getY() + 0.5D, worldPosition.getZ() + 0.5D,
                SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
        contentChanged();
    }

    private void clearContent() {
        content = ItemStack.EMPTY;
        cachedRecipe = null;
        progress = 0;
        churnTime = 0;
        manualCooldown = 0;
        setFilled(false);
        contentChanged();
    }

    /** Flips the upper half's model between empty and filled. */
    private void setFilled(boolean filled) {
        if (level == null) {
            return;
        }
        BlockPos upperPos = worldPosition.above();
        BlockState upper = level.getBlockState(upperPos);
        if (upper.is(getBlockState().getBlock()) && upper.getValue(ButterChurnBlock.FILLED) != filled) {
            level.setBlock(upperPos, upper.setValue(ButterChurnBlock.FILLED, filled), Block.UPDATE_ALL);
        }
    }

    /** Marks dirty, syncs the full tag to clients and refreshes comparators. */
    private void contentChanged() {
        setChanged();
        if (level != null) {
            BlockState state = getBlockState();
            level.sendBlockUpdated(worldPosition, state, state, Block.UPDATE_CLIENTS);
            level.updateNeighbourForOutputSignal(worldPosition, state.getBlock());
        }
    }

    // --- Persistence and sync -------------------------------------------------------

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        if (!content.isEmpty()) {
            tag.put(TAG_CONTENT, content.save(registries));
        }
        tag.putInt(TAG_PROGRESS, progress);
        tag.putInt(TAG_CHURN_TIME, churnTime);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        content = tag.contains(TAG_CONTENT) ? ItemStack.parseOptional(registries, tag.getCompound(TAG_CONTENT)) : ItemStack.EMPTY;
        progress = tag.getInt(TAG_PROGRESS);
        churnTime = tag.getInt(TAG_CHURN_TIME);
        cachedRecipe = null;
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}
