package kz.dosyakitarov.nomads_delight.registry;

import kz.dosyakitarov.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomads_delight.util.CeilingHangingBlock;
import kz.dosyakitarov.nomads_delight.util.TallBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NomadsDelightBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Nomads_delight.MODID);

    public static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    public static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        NomadsDelightItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final DeferredBlock<Block> CURD_BAG = registerBlock("curd_bag",
            () -> new CeilingHangingBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.WOOL)
                    .strength(0.8f)
                    .instabreak()
                    .noOcclusion()
            )
    );

    public static final DeferredBlock<Block> BUTTER_CHURN = registerBlock("butter_churn",
            () -> new TallBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.WOOD)
                    .strength(2.0f)
                    .destroyTime(3.0f)
                    .mapColor(MapColor.WOOD)
                    .ignitedByLava()
            ) {
                @Override
                public float getDestroyProgress(BlockState state, Player player, BlockGetter world, BlockPos pos) {
                    float baseSpeed = super.getDestroyProgress(state, player, world, pos);
                    if (player.getMainHandItem().getItem() instanceof AxeItem) {
                        return baseSpeed * 5.0f;
                    }
                    return baseSpeed;
                }
            }
    );


    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

}
