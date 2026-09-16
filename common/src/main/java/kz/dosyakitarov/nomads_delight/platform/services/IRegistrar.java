package kz.dosyakitarov.nomads_delight.platform.services;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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
 * Registration abstraction: common code declares content, each loader registers it
 * idiomatically (DeferredRegister on NeoForge, direct Registry.register on Fabric).
 * Implementations must return suppliers that are safe to call once registration for
 * the given registry has run.
 */
public interface IRegistrar {

    <T extends Item> Supplier<T> registerItem(String name, Supplier<T> factory);

    <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> factory);

    /**
     * Registers a block entity type. The type itself is built by the loader because the
     * vanilla builder's supplier interface is package-private without an access transformer
     * (NeoForge opens it, Fabric offers {@code FabricBlockEntityTypeBuilder}).
     *
     * @param factory     creates a block entity for a position and state
     * @param validBlocks suppliers of the blocks this type may attach to; resolved when the
     *                    registry runs, after blocks are registered on both loaders
     */
    <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(
            String name, BiFunction<BlockPos, BlockState, T> factory, List<Supplier<? extends Block>> validBlocks);

    /**
     * Registers a creative tab. Tab construction is loader-specific (Fabric requires
     * FabricItemGroup.builder(), the NeoForge patch adds CreativeModeTab.builder()),
     * so common code only supplies the display data.
     */
    void registerCreativeTab(String name, Component title, Supplier<ItemStack> icon,
                             CreativeModeTab.DisplayItemsGenerator displayItems);
}
