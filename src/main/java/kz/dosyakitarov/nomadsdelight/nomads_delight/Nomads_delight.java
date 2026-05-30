package kz.dosyakitarov.nomadsdelight.nomads_delight;

import com.mojang.logging.LogUtils;
import kz.dosyakitarov.nomadsdelight.nomads_delight.data.ModBlockModelProvider;
import kz.dosyakitarov.nomadsdelight.nomads_delight.data.ModItemModelProvider;
import kz.dosyakitarov.nomadsdelight.nomads_delight.data.ModLanguageProvider;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.ModBlocks;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

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
            .icon(() -> ModItems.RAW_HORSE_MEAT.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                // НУРС ТУТ МЕНЯЙ ПОРЯДОК ТАК КАК НАДО
                output.accept(ModItems.RAW_HORSE_MEAT.get());
                output.accept(ModItems.KYMYZ_BUCKET.get());
                output.accept(ModItems.KYMYZ_BOTTLE.get());
                output.accept(ModItems.ROLLING_PIN.get());
                output.accept(ModBlocks.CURD_BAG.get());
            }).build());

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Nomads_delight(IEventBus modEventBus) {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::gatherData);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

    }

    private void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(),
                new ModBlockModelProvider(generator.getPackOutput(), existingFileHelper));

        generator.addProvider(event.includeClient(),
                new ModItemModelProvider(generator, MODID, existingFileHelper));

        generator.addProvider(event.includeClient(),
                new ModLanguageProvider(generator, MODID, "en_us"));
    }
}
