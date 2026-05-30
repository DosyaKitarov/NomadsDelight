package kz.dosyakitarov.nomadsdelight.nomads_delight.data;

import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.ModBlocks;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {
    public ModLanguageProvider(DataGenerator generator, String modId, String locale) {
        super(generator.getPackOutput(), modId, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.nomads_delight", "Nomad's Delight");
        add(ModBlocks.CURD_BAG.get(), "Curd Bag");
        add(ModItems.KYMYZ_BUCKET.get(), "Kymyz Bucket");
        add(ModItems.KYMYZ_BOTTLE.get(), "Kymyz Bottle");
        add(ModItems.RAW_HORSE_MEAT.get(), "Raw Horse Meat");
        add(ModItems.COOKED_HORSE_MEAT.get(), "Cooked Horse Meat");
        add(ModItems.ROLLING_PIN.get(), "Rolling Pin");
        add(ModItems.ROLLED_DOUGH.get(), "Rolled Dough");
        add(ModItems.ZHAYMA.get(), "Zhayma");
        add(ModItems.ROASTED_MILLET.get(), "Roasted Millet");
        add(ModItems.HORSE_INTESTINES.get(), "Horse Intestines");
        add(ModItems.TALKAN.get(), "Talkan");
        add(ModItems.TALKAN_BOWL.get(), "Talkan Bowl");
        add(ModItems.BESHBARMAK.get(), "Beshbarmak");
        add(ModItems.PILAF.get(), "Pilaf");
        add(ModItems.KUURDAK.get(), "Kuurdak");
        add(ModItems.MANTI.get(), "Manti");
        add(ModItems.KHANUM.get(), "Khanum");
        add(ModItems.DIMLAMA.get(), "Dimlama");
        add(ModItems.LAGHMAN.get(), "Laghman");
        add(ModItems.KAZAN_KEBAB.get(), "Kazan Kebab");
        add(ModItems.ASIP.get(), "Asip");
        add(ModItems.RAW_KAZY.get(), "Raw Kazy");
        add(ModItems.COOKED_KAZY.get(), "Cooked Kazy");
        add(ModItems.QARTA.get(), "Qarta");
        add(ModItems.SORPA.get(), "Sorpa");
        add(ModItems.KESPE_KOZHE.get(), "Kespe Kozhe");
        add(ModItems.RAW_SAMSA.get(), "Raw Samsa");
        add(ModItems.SAMSA.get(), "Samsa");
        add(ModItems.CHICKEN_SAMSA.get(), "Chicken Samsa");
        add(ModItems.RAW_PUMPKIN_SAMSA.get(), "Raw Pumpkin Samsa");
        add(ModItems.PUMPKIN_SAMSA.get(), "Pumpkin Samsa");
        add(ModItems.RAW_PEREMECH.get(), "Raw Peremech");
        add(ModItems.PEREMECH.get(), "Peremech");
        add(ModItems.RAW_TANDOOR_BREAD.get(), "Raw Tandoor Bread");
        add(ModItems.TANDOOR_BREAD.get(), "Tandoor Bread");
        add(ModItems.RAW_KATTAMA.get(), "Raw Kattama");
        add(ModItems.KATTAMA.get(), "Kattama");
        add(ModItems.RAW_FLATBREAD.get(), "Raw Flatbread");
        add(ModItems.FLATBREAD.get(), "Flatbread");
        add(ModItems.RAW_BAURSAKS.get(), "Raw Baursaks");
        add(ModItems.BAURSAKS.get(), "Baursaks");
        add(ModItems.HALVA.get(), "Halva");
        add(ModItems.MAYSOK.get(), "Maysok");
        add(ModItems.ZHENT.get(), "Zhent");
        add(ModItems.QATYQ_BUCKET.get(), "Qatiq Bucket");
        add(ModItems.QURT.get(), "Qurt");
        add(ModItems.AYRAN_BUCKET.get(), "Ayran Bucket");
        add(ModItems.MAIMYZHYK.get(), "Maimyzhyk");
        add(ModItems.CURD.get(), "Curd");
        add(ModItems.ZHARMA_BUCKET.get(), "Zharma Bucket");
        add(ModItems.ACHUCHUK_SALAD.get(), "Achuchuk Salad");
        add(ModItems.MEAT_SALAD.get(), "Meat Salad");
        add(ModItems.MORKOVCHA.get(), "Morkovcha");

    }
}