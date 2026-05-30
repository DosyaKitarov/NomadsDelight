package kz.dosyakitarov.nomadsdelight.nomads_delight.data;

import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.ModItems;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), modId, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.KYMYZ_BUCKET.get());
        basicItem(ModItems.KYMYZ_BOTTLE.get());
        basicItem(ModItems.RAW_HORSE_MEAT.get());
        basicItem(ModItems.ROLLING_PIN.get());
    }
}