package kz.dosyakitarov.nomads_delight;

import kz.dosyakitarov.nomads_delight.data.NomadsDelightAdvancementProvider;
import kz.dosyakitarov.nomads_delight.data.NomadsDelightBlockModelProvider;
import kz.dosyakitarov.nomads_delight.data.NomadsDelightENLanguageProvider;
import kz.dosyakitarov.nomads_delight.data.NomadsDelightGLMProvider;
import kz.dosyakitarov.nomads_delight.data.NomadsDelightItemModelProvider;
import kz.dosyakitarov.nomads_delight.data.NomadsDelightLootTableProvider;
import kz.dosyakitarov.nomads_delight.data.NomadsDelightRULanguageProvider;
import kz.dosyakitarov.nomads_delight.data.NomadsDelightRecipeProvider;
import kz.dosyakitarov.nomads_delight.platform.NeoForgeRegistrar;
import kz.dosyakitarov.nomads_delight.platform.Services;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod(NomadsDelight.MODID)
public class NomadsDelightNeoForge {

    public NomadsDelightNeoForge(IEventBus modEventBus) {
        // Common registration first (fills the DeferredRegisters via the registrar service),
        // then hook the registers to the mod event bus.
        NomadsDelight.init();
        ((NeoForgeRegistrar) Services.REGISTRAR).registerTo(modEventBus);

        modEventBus.addListener(this::gatherData);
    }

    private void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeClient(),
                new NomadsDelightBlockModelProvider(generator.getPackOutput(), existingFileHelper));

        generator.addProvider(event.includeClient(),
                new NomadsDelightItemModelProvider(generator, NomadsDelight.MODID, existingFileHelper));

        generator.addProvider(event.includeClient(),
                new NomadsDelightENLanguageProvider(generator, NomadsDelight.MODID, "en_us"));

        generator.addProvider(event.includeClient(),
                new NomadsDelightRULanguageProvider(generator, NomadsDelight.MODID, "ru_ru"));

        generator.addProvider(event.includeClient(),
                new NomadsDelightRecipeProvider(generator, lookupProvider));

        generator.addProvider(event.includeClient(),
                new NomadsDelightGLMProvider(generator.getPackOutput(), lookupProvider));

        generator.addProvider(event.includeServer(),
                new NomadsDelightLootTableProvider(generator.getPackOutput(), lookupProvider));

        generator.addProvider(event.includeServer(),
                new AdvancementProvider(
                        generator.getPackOutput(),
                        lookupProvider,
                        event.getExistingFileHelper(),
                        List.of(new NomadsDelightAdvancementProvider())
                ));
    }
}
