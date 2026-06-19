package kz.dosyakitarov.nomadsdelight.nomads_delight;

import com.mojang.logging.LogUtils;
import kz.dosyakitarov.nomadsdelight.nomads_delight.data.*;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Nomads_delight.MODID)
public class Nomads_delight {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "nomads_delight";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold CreativeModeTabs which will all be registered under the "nomads_delight" namespace
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Creates a creative tab with the id "nomads_delight:example_tab" for the example item, that is placed after the combat tab
    @SuppressWarnings("unused")
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NOMADS_DELIGHT_TAB = CREATIVE_MODE_TABS.register("nomads_delight_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.nomads_delight"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> NomadsDelightItems.BESHBARMAK.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                NomadsDelightItems.ITEMS.getEntries().stream()
                        .sorted(java.util.Comparator.comparing((net.neoforged.neoforge.registries.DeferredHolder<net.minecraft.world.item.Item, ? extends net.minecraft.world.item.Item> holder) -> (holder.get() instanceof net.minecraft.world.item.BlockItem) ? 0 : 1).thenComparing(holder -> holder.getId().getPath()))
                        .forEach(holder -> output.accept(holder.get()));
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Nomads_delight(IEventBus modEventBus) {
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        NomadsDelightItems.register(modEventBus);
        NomadsDelightBlocks.register(modEventBus);

        modEventBus.addListener(this::gatherData);
    }


    private void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(),
                new NomadsDelightBlockModelProvider(generator.getPackOutput(), existingFileHelper));

        generator.addProvider(event.includeClient(),
                new NomadsDelightItemModelProvider(generator, MODID, existingFileHelper));

        generator.addProvider(event.includeClient(),
                new NomadsDelightLanguageProvider(generator, MODID, "en_us"));

        generator.addProvider(event.includeClient(),
                new NomadsDelightRecipeProvider(generator, lookupProvider));

        generator.addProvider(event.includeClient(),
                new NomadsDelightGLMProvider(generator.getPackOutput(), lookupProvider));

        generator.addProvider(event.includeServer(),
                new NomadsDelightLootTableProvider(generator.getPackOutput(), lookupProvider));


    }
}
