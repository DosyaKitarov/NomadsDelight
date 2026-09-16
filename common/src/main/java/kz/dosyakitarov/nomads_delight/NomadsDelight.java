package kz.dosyakitarov.nomads_delight;

import com.mojang.logging.LogUtils;
import kz.dosyakitarov.nomads_delight.platform.Services;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlockEntityTypes;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.network.chat.Component;
import org.slf4j.Logger;

/**
 * Loader-independent bootstrap. Each loader module calls {@link #init()} from its own
 * entry point; all actual registration goes through {@link Services#REGISTRAR}.
 */
public class NomadsDelight {
    public static final String MODID = "nomads_delight";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static void init() {
        NomadsDelightItems.init();
        NomadsDelightBlocks.init();
        NomadsDelightBlockEntityTypes.init();
        registerCreativeTab();
    }

    private static void registerCreativeTab() {
        Services.REGISTRAR.registerCreativeTab(
                "nomads_delight_tab",
                Component.translatable("itemGroup.nomads_delight"),
                () -> NomadsDelightItems.BESHBARMAK.get().getDefaultInstance(),
                (parameters, output) -> {
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
                    output.accept(NomadsDelightItems.ROASTED_MILLET.get());
                    output.accept(NomadsDelightItems.TALKAN.get());
                    output.accept(NomadsDelightItems.READY_MADE_TALKAN.get());

                    output.accept(NomadsDelightItems.ROLLED_DOUGH.get());
                    output.accept(NomadsDelightItems.ZHAYMA.get());

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

                    output.accept(NomadsDelightItems.KAZY.get());
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
                });
    }
}
