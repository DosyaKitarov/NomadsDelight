package kz.dosyakitarov.nomadsdelight.nomads_delight.registry;

import kz.dosyakitarov.nomadsdelight.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomadsdelight.nomads_delight.util.CeilingHangingBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

    // НУРС
    // Замени ассеты на
    // - assets/nomads_delight/models/block/curd_bag.json
    // - assets/nomads_delight/models/item/curd_bag.json
    // Когда будет готово
    public static final DeferredBlock<Block> CURD_BAG = registerBlock("curd_bag",
            () -> new CeilingHangingBlock(BlockBehaviour.Properties.of()
                    .sound(SoundType.WOOL)
                    .strength(0.8f)
                    .instabreak()
                    .noOcclusion()
            )
    );

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

}
