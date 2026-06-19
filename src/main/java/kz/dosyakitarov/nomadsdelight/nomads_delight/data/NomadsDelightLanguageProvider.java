package kz.dosyakitarov.nomadsdelight.nomads_delight.data;

import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class NomadsDelightLanguageProvider extends LanguageProvider {
    public NomadsDelightLanguageProvider(DataGenerator generator, String modId, String locale) {
        super(generator.getPackOutput(), modId, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.nomads_delight", "Nomad's Delight");
        add(NomadsDelightBlocks.CURD_BAG.get(), "Curd Bag");
        add(NomadsDelightBlocks.CHURN.get(), "Churn");
        add(NomadsDelightItems.QUMYZ_BUCKET.get(), "Qumyz Bucket");
        add(NomadsDelightItems.HORSE_MILK_BUCKET.get(), "Horse Milk Bucket");
        add(NomadsDelightItems.SHUBAT_BUCKET.get(), "Shubat Bucket");
        add(NomadsDelightItems.CAMEL_MILK_BUCKET.get(), "Camel Milk Bucket");
        add(NomadsDelightItems.BUTTER.get(), "Butter");
        add(NomadsDelightItems.COTTAGE_CHEESE.get(), "Cottage Cheese");
        add(NomadsDelightItems.RAW_HORSE_MEAT.get(), "Raw Horse Meat");
        add(NomadsDelightItems.COOKED_HORSE_MEAT.get(), "Cooked Horse Meat");
        add(NomadsDelightItems.ROLLING_PIN.get(), "Rolling Pin");
        add(NomadsDelightItems.ROLLED_DOUGH.get(), "Rolled Dough");
        add(NomadsDelightItems.ZHAYMA.get(), "Zhayma");
        add(NomadsDelightItems.ROASTED_MILLET.get(), "Roasted Millet");
        add(NomadsDelightItems.HORSE_INTESTINES.get(), "Horse Intestines");
        add(NomadsDelightItems.TALKAN.get(), "Talkan");
        add(NomadsDelightItems.READY_MADE_TALKAN.get(), "Ready Made Talkan");
        add(NomadsDelightItems.BESHBARMAK.get(), "Beshbarmak");
        add(NomadsDelightItems.PILAF.get(), "Pilaf");
        add(NomadsDelightItems.KUURDAK.get(), "Kuurdak");
        add(NomadsDelightItems.MANTI.get(), "Manti");
        add(NomadsDelightItems.KHANUM.get(), "Khanum");
        add(NomadsDelightItems.DIMLAMA.get(), "Dimlama");
        add(NomadsDelightItems.LAGHMAN.get(), "Laghman");
        add(NomadsDelightItems.KAZAN_KEBAB.get(), "Kazan Kebab");
        add(NomadsDelightItems.ASIP.get(), "Asip");
        add(NomadsDelightItems.RAW_KAZY.get(), "Raw Kazy");
        add(NomadsDelightItems.COOKED_KAZY.get(), "Cooked Kazy");
        add(NomadsDelightItems.QARTA.get(), "Qarta");
        add(NomadsDelightItems.SORPA.get(), "Sorpa");
        add(NomadsDelightItems.KESPE_KOZHE.get(), "Kespe Kozhe");
        add(NomadsDelightItems.RAW_SAMSA.get(), "Raw Samsa");
        add(NomadsDelightItems.SAMSA.get(), "Samsa");
        add(NomadsDelightItems.CHICKEN_SAMSA.get(), "Chicken Samsa");
        add(NomadsDelightItems.RAW_PUMPKIN_SAMSA.get(), "Raw Pumpkin Samsa");
        add(NomadsDelightItems.PUMPKIN_SAMSA.get(), "Pumpkin Samsa");
        add(NomadsDelightItems.RAW_PEREMECH.get(), "Raw Peremech");
        add(NomadsDelightItems.PEREMECH.get(), "Peremech");
        add(NomadsDelightItems.RAW_TANDOOR_BREAD.get(), "Raw Tandoor Bread");
        add(NomadsDelightItems.TANDOOR_BREAD.get(), "Tandoor Bread");
        add(NomadsDelightItems.RAW_KATTAMA.get(), "Raw Kattama");
        add(NomadsDelightItems.KATTAMA.get(), "Kattama");
        add(NomadsDelightItems.RAW_FLATBREAD.get(), "Raw Flatbread");
        add(NomadsDelightItems.FLATBREAD.get(), "Flatbread");
        add(NomadsDelightItems.RAW_BAURSAKS.get(), "Raw Baursaks");
        add(NomadsDelightItems.BAURSAKS.get(), "Baursaks");
        add(NomadsDelightItems.HALVA.get(), "Halva");
        add(NomadsDelightItems.MAYSOK.get(), "Maysok");
        add(NomadsDelightItems.ZHENT.get(), "Zhent");
        add(NomadsDelightItems.QATYQ_BUCKET.get(), "Qatуq Bucket");
        add(NomadsDelightItems.QURT.get(), "Qurt");
        add(NomadsDelightItems.AYRAN_BUCKET.get(), "Ayran Bucket");
        add(NomadsDelightItems.MAYMYZHYK.get(), "Maymyzhyk");
        add(NomadsDelightItems.CURD.get(), "Curd");
        add(NomadsDelightItems.ZHARMA_BUCKET.get(), "Zharma Bucket");
        add(NomadsDelightItems.ACHUCHUK_SALAD.get(), "Achuchuk Salad");
        add(NomadsDelightItems.MEAT_SALAD.get(), "Meat Salad");
        add(NomadsDelightItems.MORKOVCHA.get(), "Morkovcha");

    }
}