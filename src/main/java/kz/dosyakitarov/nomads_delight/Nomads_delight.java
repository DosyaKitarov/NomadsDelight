package kz.dosyakitarov.nomads_delight;

import com.mojang.logging.LogUtils;
import kz.dosyakitarov.nomads_delight.data.*;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

import java.util.List;
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
                output.accept(NomadsDelightBlocks.BUTTER_CHURN.get());
                output.accept(NomadsDelightBlocks.CURD_BAG.get());
                output.accept(NomadsDelightItems.ROLLING_PIN.get());
                output.accept(NomadsDelightItems.HORSE_MILK_BUCKET.get());
                output.accept(NomadsDelightItems.QUMYZ_BUCKET.get());
                output.accept(NomadsDelightItems.CAMEL_MILK_BUCKET.get());
                output.accept(NomadsDelightItems.SHUBAT_BUCKET.get());
                output.accept(NomadsDelightItems.AYRAN_BUCKET.get());
                output.accept(NomadsDelightItems.QATYQ_BUCKET.get());
                output.accept(NomadsDelightItems.ZHARMA_BUCKET.get());
                output.accept(NomadsDelightItems.BUTTER.get());
                output.accept(NomadsDelightItems.CURD.get());
                output.accept(NomadsDelightItems.QURT.get());
                output.accept(NomadsDelightItems.MAYMYZHYK.get());
                output.accept(NomadsDelightItems.ROLLED_DOUGH.get());
                output.accept(NomadsDelightItems.ZHAYMA.get());
                output.accept(NomadsDelightItems.ROASTED_MILLET.get());
                output.accept(NomadsDelightItems.TALKAN.get());
                output.accept(NomadsDelightItems.READY_MADE_TALKAN.get());

                output.accept(NomadsDelightItems.RAW_SAMSA.get());
                output.accept(NomadsDelightItems.SAMSA.get());

                output.accept(NomadsDelightItems.RAW_CHICKEN_SAMSA.get());
                output.accept(NomadsDelightItems.CHICKEN_SAMSA.get());

                output.accept(NomadsDelightItems.RAW_PUMPKIN_SAMSA.get());
                output.accept(NomadsDelightItems.PUMPKIN_SAMSA.get());

                output.accept(NomadsDelightItems.RAW_PEREMECH.get());
                output.accept(NomadsDelightItems.PEREMECH.get());

                output.accept(NomadsDelightItems.RAW_TANDOOR_BREAD.get());
                output.accept(NomadsDelightItems.TANDOOR_BREAD.get());

                output.accept(NomadsDelightItems.RAW_KATTAMA.get());
                output.accept(NomadsDelightItems.KATTAMA.get());

                output.accept(NomadsDelightItems.RAW_FLATBREAD.get());
                output.accept(NomadsDelightItems.FLATBREAD.get());

                output.accept(NomadsDelightItems.RAW_BAURSAKS.get());
                output.accept(NomadsDelightItems.BAURSAKS.get());

                output.accept(NomadsDelightItems.HALVA.get());
                output.accept(NomadsDelightItems.MAYSOK.get());
                output.accept(NomadsDelightItems.ZHENT.get());

                output.accept(NomadsDelightItems.HORSE_INTESTINES.get());

                output.accept(NomadsDelightItems.RAW_KAZY.get());
                output.accept(NomadsDelightItems.COOKED_KAZY.get());
                output.accept(NomadsDelightItems.ASIP.get());
                output.accept(NomadsDelightItems.QARTA.get());

                output.accept(NomadsDelightItems.RAW_HORSE_MEAT.get());
                output.accept(NomadsDelightItems.COOKED_HORSE_MEAT.get());

                output.accept(NomadsDelightItems.ACHUCHUK_SALAD.get());
                output.accept(NomadsDelightItems.MEAT_SALAD.get());
                output.accept(NomadsDelightItems.MORKOVCHA_SALAD.get());
                output.accept(NomadsDelightItems.SORPA.get());
                output.accept(NomadsDelightItems.KESPE_KOZHE.get());

                output.accept(NomadsDelightItems.MANTI.get());
                output.accept(NomadsDelightItems.KHANUM.get());
                output.accept(NomadsDelightItems.DIMLAMA.get());
                output.accept(NomadsDelightItems.LAGHMAN.get());
                output.accept(NomadsDelightItems.KAZAN_KEBAB.get());
                output.accept(NomadsDelightItems.KUURDAK.get());
                output.accept(NomadsDelightItems.PILAF.get());
                output.accept(NomadsDelightItems.BESHBARMAK.get());

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
                new NomadsDelightENLanguageProvider(generator, MODID, "en_us"));

        generator.addProvider(event.includeClient(),
                new NomadsDelightRULanguageProvider(generator, MODID, "ru_ru"));

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
