package kz.dosyakitarov.nomadsdelight.nomads_delight.util;

import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TallBlock extends Block {
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final IntegerProperty CHURN_STATE = IntegerProperty.create("churn_state", 0, 2);
    public static final IntegerProperty MILK_TYPE = IntegerProperty.create("milk_type", 0, 2);

    private static final VoxelShape LOWER_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    private static final VoxelShape UPPER_SHAPE = makeShape();

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER ? UPPER_SHAPE : LOWER_SHAPE;
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                           Player player, InteractionHand hand, BlockHitResult hit) {
        BlockPos lowerPos = state.getValue(HALF) == DoubleBlockHalf.LOWER ? pos : pos.below();
        BlockPos upperPos = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos : pos.above();

        BlockState lowerState = level.getBlockState(lowerPos);

        int currentState = lowerState.getValue(CHURN_STATE);
        int milkType = lowerState.getValue(MILK_TYPE);

        if (currentState == 0 && (stack.getItem() == NomadsDelightItems.HORSE_MILK_BUCKET.get()
                || stack.getItem() == NomadsDelightItems.CAMEL_MILK_BUCKET.get()
                || stack.getItem() == Items.MILK_BUCKET)) {

            milkType = (stack.getItem() == NomadsDelightItems.HORSE_MILK_BUCKET.get()) ? 0 :
                    (stack.getItem() == NomadsDelightItems.CAMEL_MILK_BUCKET.get()) ? 1 : 2;

            if (!level.isClientSide) {
                if (!player.isCreative()) {
                    stack.shrink(1);
                    player.getInventory().placeItemBackInInventory(new ItemStack(Items.BUCKET));
                }

                BlockState newLowerState = lowerState.setValue(CHURN_STATE, 1).setValue(MILK_TYPE, milkType);
                level.setBlock(lowerPos, newLowerState, 3);

                BlockState upperState = level.getBlockState(upperPos);
                if (upperState.is(this)) {
                    BlockState newUpperState = upperState.setValue(CHURN_STATE, 1).setValue(MILK_TYPE, milkType);
                    level.setBlock(upperPos, newUpperState, 3);
                }

                level.scheduleTick(lowerPos, this, 6000);
            }

            level.playSound(null, lowerPos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        if (currentState == 2) {
            if (stack.getItem() == Items.BUCKET) {
                if (milkType == 1) {
                    if (!level.isClientSide) {
                        stack.shrink(1);
                        player.getInventory().placeItemBackInInventory(new ItemStack(NomadsDelightItems.QUMYZ_BUCKET.get()));
                    }
                    resetChurnState(level, lowerPos, upperPos, lowerState);
                } else if (milkType == 2) {
                    if (!level.isClientSide) {
                        stack.shrink(1);
                        player.getInventory().placeItemBackInInventory(new ItemStack(NomadsDelightItems.SHUBAT_BUCKET.get()));
                    }
                    resetChurnState(level, lowerPos, upperPos, lowerState);
                }
                level.playSound(null, lowerPos, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }

            if (milkType == 2) {
                if (!level.isClientSide) {
                    player.getInventory().placeItemBackInInventory(new ItemStack(NomadsDelightItems.BUTTER.get()));
                }
                resetChurnState(level, lowerPos, upperPos, lowerState);
                level.playSound(null, lowerPos, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS);
                return ItemInteractionResult.sidedSuccess(level.isClientSide);
            }
        }

        return ItemInteractionResult.SUCCESS;
    }

    private void resetChurnState(Level level, BlockPos lowerPos, BlockPos upperPos, BlockState lowerState) {
        if (!level.isClientSide) {
            level.setBlock(lowerPos, lowerState.setValue(CHURN_STATE, 0), 3);

            BlockState upperState = level.getBlockState(upperPos);
            if (upperState.is(this)) {
                level.setBlock(upperPos, upperState.setValue(CHURN_STATE, 0), 3);
            }
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (state.getValue(CHURN_STATE) == 1) {
            BlockPos lowerPos = state.getValue(HALF) == DoubleBlockHalf.LOWER ? pos : pos.below();
            BlockPos upperPos = state.getValue(HALF) == DoubleBlockHalf.UPPER ? pos : pos.above();

            BlockState lowerState = level.getBlockState(lowerPos);
            BlockState upperState = level.getBlockState(upperPos);

            if (lowerState.is(this)) {
                level.setBlock(lowerPos, lowerState.setValue(CHURN_STATE, 2), 3);
            }
            if (upperState.is(this)) {
                level.setBlock(upperPos, upperState.setValue(CHURN_STATE, 2), 3);
            }

            level.playSound(null,
                    (double) lowerPos.getX() + 0.5D,
                    (double) lowerPos.getY() + 0.5D,
                    (double) lowerPos.getZ() + 0.5D,
                    SoundEvents.SLIME_BLOCK_BREAK,
                    SoundSource.BLOCKS,
                    1.0F, 1.0F
            );
        }
    }

    public TallBlock(Properties properties) {
        super(properties);

        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(HALF, DoubleBlockHalf.LOWER)
                        .setValue(CHURN_STATE, 0)
                        .setValue(MILK_TYPE, 0)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HALF);
        builder.add(CHURN_STATE);
        builder.add(MILK_TYPE);
    }

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
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
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
        if (random.nextFloat() < 0.5F) {
            if (level.isClientSide() && state.getValue(CHURN_STATE) == 2) {
                double x = pos.getX() + random.nextDouble();
                double y = pos.getY();
                double z = pos.getZ() + random.nextDouble();
                double r = 1.0;
                double g = 0.894;
                double b = 0.659;
                level.addParticle(ParticleTypes.EFFECT, x, y, z, r, g, b);
            }
        }
    }


    public static VoxelShape makeShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 0.125, 0.375, 0.625, 1, 0.625), BooleanOp.OR);

        return shape;
    }
}