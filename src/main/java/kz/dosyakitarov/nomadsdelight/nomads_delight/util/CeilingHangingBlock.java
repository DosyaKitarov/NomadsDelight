package kz.dosyakitarov.nomadsdelight.nomads_delight.util;

import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CeilingHangingBlock extends Block {
    //Нурс когда сделаешь модель для калташа надо тут подправить, пока что стоит форма фонаря для тестов
    protected static final VoxelShape AABB = Block.box(5.0D, 1.0D, 5.0D, 11.0D, 16.0D, 11.0D);

    public static final IntegerProperty BAG_STATE = IntegerProperty.create("bag_state", 0, 2);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BAG_STATE);
    }

    public CeilingHangingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(BAG_STATE, 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return AABB;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        BlockPos ceilingPos = pos.above();
        return canSupportCenter(level, ceilingPos, Direction.DOWN);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                  net.minecraft.world.level.LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if (direction == Direction.UP && !this.canSurvive(state, level, pos)) {
            return net.minecraft.world.level.block.Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                           Player player, InteractionHand hand, BlockHitResult hit) {
        int currentState = state.getValue(BAG_STATE);
        if (currentState == 0 && stack.getItem() == NomadsDelightItems.QATYQ_BUCKET.get()) {
            if (!level.isClientSide) {
                if (!player.isCreative()) {
                    stack.shrink(1);
                    player.getInventory().placeItemBackInInventory(new ItemStack(Items.BUCKET));
                }
                level.setBlock(pos, state.setValue(BAG_STATE, 1), 3);
                level.scheduleTick(pos, this, 24000);
            }
            level.playSound(null, pos, SoundEvents.BUCKET_EMPTY, SoundSource.BLOCKS);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }

        if (currentState == 2) {
            if (!level.isClientSide) {
                Block.popResource(level, pos, new ItemStack(NomadsDelightItems.COTTAGE_CHEESE.get()));
                level.setBlock(pos, state.setValue(BAG_STATE, 0), 3);
            }
            //play sound
            level.playSound(null, pos, SoundEvents.SLIME_BLOCK_BREAK, SoundSource.BLOCKS);
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        return ItemInteractionResult.SUCCESS;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource source) {
        if (state.getValue(BAG_STATE) == 1) {
            level.setBlock(pos, state.setValue(BAG_STATE, 2), 3);
            level.playSound(null,
                    (double) pos.getX() + 0.5D,
                    (double) pos.getY() + 0.5D,
                    (double) pos.getZ() + 0.5D,
                    SoundEvents.SLIME_BLOCK_BREAK,
                    SoundSource.BLOCKS,
                    1.0F, 1.0F
            );
        }
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            int currentState = state.getValue(BAG_STATE);
            if (currentState == 2) {
                Block.popResource(level, pos, new ItemStack(NomadsDelightItems.COTTAGE_CHEESE.get()));
            }
            Block.popResource(level, pos, new ItemStack(NomadsDelightBlocks.CURD_BAG.get()));

        }

    }
}
