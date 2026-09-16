package kz.dosyakitarov.nomads_delight.data;

import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class NomadsDelightRULanguageProvider extends NomadsDelightMergingLanguageProvider {
    public NomadsDelightRULanguageProvider(DataGenerator generator, String modId, String locale) {
        super(generator.getPackOutput(), modId, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.nomads_delight", "Восторг кочевника");
        add(NomadsDelightBlocks.CURD_BAG.get(), "Мешочек");
        add(NomadsDelightBlocks.BUTTER_CHURN.get(), "Маслобойка");
        add(NomadsDelightItems.QUMYZ_BUCKET.get(), "Ведро кумыса");
        add(NomadsDelightItems.HORSE_MILK_BUCKET.get(), "Ведро кобыльего молока");
        add(NomadsDelightItems.SHUBAT_BUCKET.get(), "Ведро шубата");
        add(NomadsDelightItems.CAMEL_MILK_BUCKET.get(), "Ведро верблюжьего молока");
        add(NomadsDelightItems.BUTTER.get(), "Сливочное масло");
        add(NomadsDelightItems.RAW_HORSE_MEAT.get(), "Сырая конина");
        add(NomadsDelightItems.COOKED_HORSE_MEAT.get(), "Жареная конина");
        add(NomadsDelightItems.ROLLING_PIN.get(), "Скалка");
        add(NomadsDelightItems.ROLLED_DOUGH.get(), "Раскатанное тесто");
        add(NomadsDelightItems.ZHAYMA.get(), "Жайма");
        add(NomadsDelightItems.ROASTED_MILLET.get(), "Жареное просо");
        add(NomadsDelightItems.HORSE_INTESTINES.get(), "Конские кишки");
        add(NomadsDelightItems.TALKAN.get(), "Талкан");
        add(NomadsDelightItems.READY_MADE_TALKAN.get(), "Готовый талкан");
        add(NomadsDelightItems.BESHBARMAK.get(), "Бешбармак");
        add(NomadsDelightItems.PILAF.get(), "Плов");
        add(NomadsDelightItems.KUURDAK.get(), "Куырдак");
        add(NomadsDelightItems.MANTI.get(), "Манты");
        add(NomadsDelightItems.KHANUM.get(), "Ханум");
        add(NomadsDelightItems.DIMLAMA.get(), "Димляма");
        add(NomadsDelightItems.LAGHMAN.get(), "Лагман");
        add(NomadsDelightItems.KAZAN_KEBAB.get(), "Казан-кебаб");
        add(NomadsDelightItems.ASIP.get(), "Асип");
        add(NomadsDelightItems.KAZY.get(), "Казы");
        add(NomadsDelightItems.QARTA.get(), "Карта");
        add(NomadsDelightItems.SORPA.get(), "Сорпа");
        add(NomadsDelightItems.KESPE_KOZHE.get(), "Кеспе-коже");
        add(NomadsDelightItems.RAW_SAMSA.get(), "Сырая самса");
        add(NomadsDelightItems.SAMSA.get(), "Самса");
        add(NomadsDelightItems.RAW_CHICKEN_SAMSA.get(), "Сырая самса с курицей");
        add(NomadsDelightItems.CHICKEN_SAMSA.get(), "Самса с курицей");
        add(NomadsDelightItems.RAW_PUMPKIN_SAMSA.get(), "Сырая самса с тыквой");
        add(NomadsDelightItems.PUMPKIN_SAMSA.get(), "Самса с тыквой");
        add(NomadsDelightItems.RAW_PEREMECH.get(), "Сырой беляш");
        add(NomadsDelightItems.PEREMECH.get(), "Беляш");
        add(NomadsDelightItems.RAW_TANDOOR_BREAD.get(), "Сырая тандырная лепешка");
        add(NomadsDelightItems.TANDOOR_BREAD.get(), "Тандырная лепешка");
        add(NomadsDelightItems.RAW_KATTAMA.get(), "Сырая каттма");
        add(NomadsDelightItems.KATTAMA.get(), "Каттма");
        add(NomadsDelightItems.RAW_FLATBREAD.get(), "Сырой шелпек");
        add(NomadsDelightItems.FLATBREAD.get(), "Шелпек");
        add(NomadsDelightItems.RAW_BAURSAKS.get(), "Сырые баурсаки");
        add(NomadsDelightItems.BAURSAKS.get(), "Баурсаки");
        add(NomadsDelightItems.HALVA.get(), "Халва");
        add(NomadsDelightItems.MAYSOK.get(), "Майсок");
        add(NomadsDelightItems.ZHENT.get(), "Жент");
        add(NomadsDelightItems.QATYQ_BUCKET.get(), "Ведро катыка");
        add(NomadsDelightItems.QURT.get(), "Курт");
        add(NomadsDelightItems.AYRAN_BUCKET.get(), "Ведро айрана");
        add(NomadsDelightItems.CURD.get(), "Творожная масса");
        add(NomadsDelightItems.ZHARMA_BUCKET.get(), "Ведро жармы");
        add(NomadsDelightItems.ACHUCHUK_SALAD.get(), "Салат Ачучук");
        add(NomadsDelightItems.MEAT_SALAD.get(), "Мясной салат");
        add(NomadsDelightItems.MORKOVCHA_SALAD.get(), "Салат Морковча");

        addAdvancement("root", "Радость кочевника", "Вкусите незабываемые ароматы бескрайней степи.");
        addAdvancement("make_curd_bag", "Хот свап?", "Сделайте мешок для сцеживания сыворотки и начните фильтрацию.");
        addAdvancement("make_curd", "Сухой остаток", "Залейте катык в мешок, чтобы отцедить свежую творожную массу.");
        addAdvancement("make_butter_churn", "Крути-верти", "Смастерите маслобойку.");
        addAdvancement("make_shubat_or_qymyz", "Белое золото", "Приготовьте шубат или кумыс в маслобойке.");
        addAdvancement("get_drunk", "Степная качка", "Выпейте столько кумыса, чтобы мир вокруг слегка закружился.");
        addAdvancement("make_butter", "Как по маслу", "Сбейте немного сливочного масла.");
        addAdvancement("make_rolling_pin", "Тунг тунг тунг cахур!", "Смастерите деревянную скалку.");
        addAdvancement("bonk", "Бонк!", "Убейте моба с помощью скалки.");
        addAdvancement("make_zhayma", "Тонкий расчёт", "Нарежьте жайму для бешбармака.");
        addAdvancement("make_beshbarmak", "Пять пальцев, одно блюдо", "Приготовьте огромную, сытную тарелку бешбармака.");
        addAdvancement("milk_horse", "Скрытый потенциал", "Подоите кобылу. Да-да, вам не послышалось.");
        addAdvancement("milk_camel", "Корабль пустыни", "Подоите верблюда.");
        addAdvancement("make_any_salad", "Свежесть степи", "Приготовьте любой свежий степной салат.");
        addAdvancement("make_any_meat_dish", "Мечта мясоеда", "Приготовьте любое сытное мясное блюдо кочевников.");
        addAdvancement("make_any_snack", "Степная закуска", "Стряпайте любую кочевую закуску.");
        addAdvancement("eat_everything", "Сверхчеловек", "Попробуйте абсолютно всё — устройте настоящий пир кочевников.");
        addAdvancement("eat_any_bread", "Ешь с хлебом! Будешь сыт.", "Сделайте кусь любой традиционной лепёшки. Хлеб — всему голова!");
        addAdvancement("eat_horse_on_horse", "Вкусный транспорт", "Есть конину... сидя на коне? Как-то иронично.");

        add("jei.nomads_delight.category.churning", "Взбивание");
        add("jei.nomads_delight.category.churning.time", "Время взбивания: %s сек.");

        add("jei.nomads_delight.category.straining", "Процеживание");
        add("jei.nomads_delight.category.straining.time", "Время процеживания: %s сек.");

        add("tooltip.nomads_delight.removes_effects", "Снимает все эффекты");
        add("tooltip.nomads_delight.effect_format", "%s (%s)");
        add("tooltip.nomads_delight.effect.comfort", "Комфорт");
        add("tooltip.nomads_delight.effect.nourishment", "Сытость");
        add("tooltip.nomads_delight.effect.regeneration", "Регенерация");

        mergeManualTranslations();
    }

    private void addAdvancement(String id, String title, String description) {
        add(NomadsDelightAdvancementProvider.titleKey(id), title);
        add(NomadsDelightAdvancementProvider.descKey(id), description);
    }
}