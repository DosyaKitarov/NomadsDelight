package kz.dosyakitarov.nomads_delight.block;

import com.mojang.serialization.MapCodec;
import kz.dosyakitarov.nomads_delight.block.entity.ButterChurnBlockEntity;
import kz.dosyakitarov.nomads_delight.crafting.ChurnRecipes.ChurnRecipe;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

/**
 * Two-block-tall butter churn. All gameplay state lives in the {@link ButterChurnBlockEntity}
 * of the LOWER half; the UPPER half only carries {@link #FILLED} so its model can show milk.
 * Interactions on either half are resolved to the lower half.
 */
public class ButterChurnBlock extends BaseEntityBlock {
    public static final MapCodec<ButterChurnBlock> CODEC = simpleCodec(ButterChurnBlock::new);

    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    /** Upper half only: whether the churn holds milk. Drives the top texture. */
    public static final BooleanProperty FILLED = BooleanProperty.create("filled");

    private static final VoxelShape LOWER_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape UPPER_SHAPE = makeUpperShape();
    /**
     * The plunger stick inside the upper half, in block-local coordinates. Matches the stick
     * element of {@code churn_plunger.json} and the stick part of {@link #UPPER_SHAPE}.
     * Its floor is nudged up so clicks on the plate's top face right beside the stick
     * (which land at exactly y = 2/16) are not counted as plunger hits.
     */
    private static final AABB PLUNGER_HITBOX = new AABB(
            6.0D / 16.0D, 2.0D / 16.0D + 0.001D, 6.0D / 16.0D,
            10.0D / 16.0D, 1.0D + 0.001D, 10.0D / 16.0D)
            .inflate(0.001D, 0.0D, 0.001D);

    public ButterChurnBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HALF, DoubleBlockHalf.LOWER)
                .setValue(FILLED, false));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF, FILLED);
    }

    // --- Block entity ---------------------------------------------------------------

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER ? new ButterChurnBlockEntity(pos, state) : null;
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, NomadsDelightBlockEntityTypes.BUTTER_CHURN.get(),
                level.isClientSide ? ButterChurnBlockEntity::clientTick : ButterChurnBlockEntity::serverTick);
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Nullable
    private static ButterChurnBlockEntity getChurn(Level level, BlockPos pos, BlockState state) {
        BlockPos lowerPos = lowerPos(pos, state);
        return level.getBlockEntity(lowerPos) instanceof ButterChurnBlockEntity churn ? churn : null;
    }

    private static BlockPos lowerPos(BlockPos pos, BlockState state) {
        return state.getValue(HALF) == DoubleBlockHalf.LOWER ? pos : pos.below();
    }

    // --- Interaction ----------------------------------------------------------------

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                              Player player, InteractionHand hand, BlockHitResult hit) {
        ButterChurnBlockEntity churn = getChurn(level, pos, state);
        if (churn == null) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        BlockPos lowerPos = lowerPos(pos, state);

        if (!churn.hasContent()) {
            if (level.isClientSide) {
                // Mirror the server decision so the hand swings only for a real insert.
                return churn.tryInsertPreview(stack) ? ItemInteractionResult.SUCCESS
                        : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            if (churn.tryInsert(stack, player)) {
                level.playSound(null, lowerPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS);
                return ItemInteractionResult.CONSUME;
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        if (churn.isReady()) {
            if (level.isClientSide) {
                return churn.tryExtractPreview(stack) ? ItemInteractionResult.SUCCESS
                        : ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
            ChurnRecipe recipe = churn.tryExtract(stack, player);
            if (recipe != null) {
                level.playSound(null, lowerPos, recipe.extractSound(), SoundSource.BLOCKS);
                return ItemInteractionResult.CONSUME;
            }
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }

        // Churning: any held item that is not an input or tool falls through to useWithoutItem,
        // which churns only if the plunger stick itself was clicked.
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (!isPlungerHit(state, pos, hit)) {
            return InteractionResult.PASS;
        }
        ButterChurnBlockEntity churn = getChurn(level, pos, state);
        if (churn == null || !churn.isChurning()) {
            return InteractionResult.PASS;
        }
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        // The click is consumed even while on cooldown so no block gets placed mid-plunge.
        churn.tryManualChurn();
        return InteractionResult.CONSUME;
    }

    /** True only when the click landed on the plunger stick of the upper half, not the plate or barrel. */
    private static boolean isPlungerHit(BlockState state, BlockPos pos, BlockHitResult hit) {
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER) {
            return false;
        }
        Vec3 local = hit.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());
        return PLUNGER_HITBOX.contains(local);
    }

    // --- Redstone, drops, mining ----------------------------------------------------

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        ButterChurnBlockEntity churn = getChurn(level, pos, state);
        return churn == null ? 0 : churn.getComparatorOutput();
    }

    /**
     * Breaking a churn spills its contents: nothing inside is dropped. The empty bucket was
     * already handed back on insertion, so dropping the milk (or a finished bucket product)
     * here would create a second bucket out of nothing.
     */
    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, movedByPiston);
        }
    }

    @Override
    public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
        float baseSpeed = super.getDestroyProgress(state, player, level, pos);
        return player.getMainHandItem().getItem() instanceof AxeItem ? baseSpeed * 5.0F : baseSpeed;
    }

    // --- Double block plumbing ------------------------------------------------------

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER ? UPPER_SHAPE : LOWER_SHAPE;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (pos.getY() < level.getMaxBuildHeight() - 1
                && level.getBlockState(pos.above()).canBeReplaced(context)) {
            return defaultBlockState();
        }
        return null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        level.setBlock(pos.above(), state.setValue(HALF, DoubleBlockHalf.UPPER), Block.UPDATE_ALL);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                     LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        DoubleBlockHalf half = state.getValue(HALF);
        boolean linkedNeighbor = (direction == Direction.UP && half == DoubleBlockHalf.LOWER)
                || (direction == Direction.DOWN && half == DoubleBlockHalf.UPPER);
        if (linkedNeighbor && (!neighborState.is(this) || neighborState.getValue(HALF) == half)) {
            return Blocks.AIR.defaultBlockState();
        }
        return half == DoubleBlockHalf.LOWER && direction == Direction.DOWN && !state.canSurvive(level, pos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (state.getValue(HALF) == DoubleBlockHalf.LOWER) {
            return true;
        }
        BlockState below = level.getBlockState(pos.below());
        return below.is(this) && below.getValue(HALF) == DoubleBlockHalf.LOWER;
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide) {
            if (state.getValue(HALF) == DoubleBlockHalf.LOWER && !player.isCreative()) {
                Block.popResource(level, pos, new ItemStack(this));
            }

            DoubleBlockHalf half = state.getValue(HALF);
            BlockPos otherPos = half == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
            BlockState otherState = level.getBlockState(otherPos);
            if (otherState.is(this) && otherState.getValue(HALF) != half) {
                level.setBlock(otherPos, Blocks.AIR.defaultBlockState(),
                        Block.UPDATE_ALL | Block.UPDATE_SUPPRESS_DROPS);
                level.levelEvent(player, 2001, otherPos, Block.getId(otherState));
            }
        }
        super.playerWillDestroy(level, pos, state, player);
        return state;
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(this);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(HALF) != DoubleBlockHalf.LOWER || random.nextFloat() >= 0.5F) {
            return;
        }
        if (level.getBlockEntity(pos) instanceof ButterChurnBlockEntity churn && churn.isReady()) {
            level.addParticle(ParticleTypes.EFFECT,
                    pos.getX() + random.nextDouble(), pos.getY() + 1.0D, pos.getZ() + random.nextDouble(),
                    1.0D, 1.0D, 1.0D);
        }
    }

    private static VoxelShape makeUpperShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.125, 0.375, 0.625, 1, 0.625), BooleanOp.OR);
        return shape;
    }
}
