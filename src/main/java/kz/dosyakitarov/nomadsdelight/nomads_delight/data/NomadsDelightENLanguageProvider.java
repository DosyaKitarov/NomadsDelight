package kz.dosyakitarov.nomadsdelight.nomads_delight.data;

import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class NomadsDelightENLanguageProvider extends LanguageProvider {
    public NomadsDelightENLanguageProvider(DataGenerator generator, String modId, String locale) {
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
        add(NomadsDelightItems.RAW_CHICKEN_SAMSA.get(), "Raw Chicken Samsa");
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
        add(NomadsDelightItems.MORKOVCHA_SALAD.get(), "Morkovcha Salad");

        addAdvancement("root", "Nomad's Delight", "Savor the flavors of the boundless steppe.");
        addAdvancement("make_curd_bag", "Hot Swap?", "Craft a curd bag and start straining.");
        addAdvancement("make_curd", "Drip Dry", "Put Qatyq in the bag to get fresh curd.");
        addAdvancement("make_churn", "Churn, Baby, Churn", "Craft a churn.");
        addAdvancement("make_shubat_or_qymyz", "White Gold", "Churn shubat or qymyz in your churn.");
        addAdvancement("get_drunk", "Steppe Stumble", "Drink enough qymyz to feel the world spin a little.");
        addAdvancement("make_butter", "Smooth Operator", "Churn butter.");
        addAdvancement("make_rolling_pin", "Tung Tung Tung Sahur", "Craft a rolling pin.");
        addAdvancement("bonk", "Bonk", "Defeat a mob with a rolling pin.");
        addAdvancement("make_zhayma", "Cut the Roll", "Cut out some zhayma.");
        addAdvancement("make_beshbarmak", "Five Fingers, One Bowl", "Cook a hearty plate of beshbarmak.");
        addAdvancement("milk_horse", "Got Milk? (The Hard Way)", "Milk a horse. No, that's not a typo.");
        addAdvancement("milk_camel", "Ship of the Desert", "Milk a camel.");
        addAdvancement("make_any_salad", "Fresh Off the Steppe", "Prepare any nomadic salad.");
        addAdvancement("make_any_meat_dish", "Meat Lover's Dream", "Cook any hearty nomadic meat dish.");
        addAdvancement("make_any_snack", "Steppe Snack Attack", "Whip up any sweet nomadic snack.");
        addAdvancement("eat_everything", "Son Of The Steppe", "Taste your way through everything — the full nomadic spread.");
        addAdvancement("eat_any_bread", "Eat With Bread! You will be full.", "Eat any kind of traditional bread.");
        addAdvancement("eat_horse_on_horse", "Tasty Transport", "Eat horse meat... on a horse?");
    }

    private void addAdvancement(String id, String title, String description) {
        add(NomadsDelightAdvancementProvider.titleKey(id), title);
        add(NomadsDelightAdvancementProvider.descKey(id), description);
    }
}