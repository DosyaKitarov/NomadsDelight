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
        add(ModItems.ROLLING_PIN.get(), "Rolling Pin");
    }
}