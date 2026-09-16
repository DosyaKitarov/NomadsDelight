package kz.dosyakitarov.nomads_delight.registry;

import kz.dosyakitarov.nomads_delight.block.ButterChurnBlock;
import kz.dosyakitarov.nomads_delight.platform.Services;
import kz.dosyakitarov.nomads_delight.util.CeilingHangingBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Supplier;

public class NomadsDelightBlocks {

    public static <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> block) {
        Supplier<T> registered = Services.REGISTRAR.registerBlock(name, block);
        registerBlockItem(name, registered);
        return registered;
    }

    public static <T extends Block> void registerBlockItem(String name, Supplier<T> block) {
        Services.REGISTRAR.registerItem(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static final Supplier<Block> CURD_BAG = registerBlock("curd_bag",
            () -> new CeilingHangingBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.WOOL)
                    .strength(0.8f)
                    .instabreak()
                    .noOcclusion()
            )
    );

    public static final Supplier<Block> BUTTER_CHURN = registerBlock("butter_churn",
            () -> new ButterChurnBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.WOOD)
                    .strength(2.0f)
                    .destroyTime(3.0f)
                    .mapColor(MapColor.WOOD)
                    .ignitedByLava()
            )
    );

    /** Triggers static registration; called once from {@code NomadsDelight.init()}. */
    public static void init() {
    }
}
