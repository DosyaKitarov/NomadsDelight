package kz.dosyakitarov.nomads_delight.platform;

import kz.dosyakitarov.nomads_delight.NomadsDelight;
import kz.dosyakitarov.nomads_delight.platform.services.IRegistrar;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * Fabric registrar: vanilla registries are open during mod initialization, so content
 * is registered immediately and the returned supplier just wraps the instance.
 */
public class FabricRegistrar implements IRegistrar {

    @Override
    public <T extends Item> Supplier<T> registerItem(String name, Supplier<T> factory) {
        T item = Registry.register(BuiltInRegistries.ITEM, id(name), factory.get());
        return () -> item;
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> factory) {
        T block = Registry.register(BuiltInRegistries.BLOCK, id(name), factory.get());
        return () -> block;
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(
            String name, BiFunction<BlockPos, BlockState, T> factory, List<Supplier<? extends Block>> validBlocks) {
        BlockEntityType<T> type = FabricBlockEntityTypeBuilder
                .create(factory::apply, validBlocks.stream().map(Supplier::get).toArray(Block[]::new))
                .build();
        Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id(name), type);
        return () -> type;
    }

    @Override
    public void registerCreativeTab(String name, Component title, Supplier<ItemStack> icon,
                                    CreativeModeTab.DisplayItemsGenerator displayItems) {
        // FabricItemGroup.builder() (not the vanilla builder) is required on Fabric.
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, id(name), FabricItemGroup.builder()
                .title(title)
                .icon(icon::get)
                .displayItems(displayItems)
                .build());
    }

    private static ResourceLocation id(String name) {
        return ResourceLocation.fromNamespaceAndPath(NomadsDelight.MODID, name);
    }
}
