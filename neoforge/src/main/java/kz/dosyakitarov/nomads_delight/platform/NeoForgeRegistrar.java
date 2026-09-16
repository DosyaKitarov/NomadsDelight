package kz.dosyakitarov.nomads_delight.platform;

import kz.dosyakitarov.nomads_delight.NomadsDelight;
import kz.dosyakitarov.nomads_delight.platform.services.IRegistrar;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * NeoForge registrar: collects content into DeferredRegisters during common init and
 * attaches them to the mod event bus in the mod constructor ({@link #registerTo}).
 */
public class NeoForgeRegistrar implements IRegistrar {

    private final DeferredRegister.Items items = DeferredRegister.createItems(NomadsDelight.MODID);
    private final DeferredRegister.Blocks blocks = DeferredRegister.createBlocks(NomadsDelight.MODID);
    private final DeferredRegister<BlockEntityType<?>> blockEntityTypes =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, NomadsDelight.MODID);
    private final DeferredRegister<CreativeModeTab> tabs =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NomadsDelight.MODID);

    @Override
    public <T extends Item> Supplier<T> registerItem(String name, Supplier<T> factory) {
        return items.register(name, factory);
    }

    @Override
    public <T extends Block> Supplier<T> registerBlock(String name, Supplier<T> factory) {
        return blocks.register(name, factory);
    }

    @Override
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(
            String name, BiFunction<BlockPos, BlockState, T> factory, List<Supplier<? extends Block>> validBlocks) {
        return blockEntityTypes.register(name, () -> BlockEntityType.Builder
                .of(factory::apply, validBlocks.stream().map(Supplier::get).toArray(Block[]::new))
                .build(null));
    }

    @Override
    public void registerCreativeTab(String name, Component title, Supplier<ItemStack> icon,
                                    CreativeModeTab.DisplayItemsGenerator displayItems) {
        tabs.register(name, () -> CreativeModeTab.builder()
                .title(title)
                .icon(icon::get)
                .displayItems(displayItems)
                .build());
    }

    public void registerTo(IEventBus modEventBus) {
        items.register(modEventBus);
        blocks.register(modEventBus);
        blockEntityTypes.register(modEventBus);
        tabs.register(modEventBus);
    }
}
