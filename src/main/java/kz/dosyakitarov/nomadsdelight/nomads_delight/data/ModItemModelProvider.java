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
        tryBasicItem(ModItems.KYMYZ_BUCKET.get());
        tryBasicItem(ModItems.KYMYZ_BOTTLE.get());
        tryBasicItem(ModItems.RAW_HORSE_MEAT.get());
        tryBasicItem(ModItems.COOKED_HORSE_MEAT.get());
        tryBasicItem(ModItems.ROLLING_PIN.get());
        tryBasicItem(ModItems.ROLLED_DOUGH.get());
        tryBasicItem(ModItems.ZHAYMA.get());
        tryBasicItem(ModItems.ROASTED_MILLET.get());
        tryBasicItem(ModItems.HORSE_INTESTINES.get());
        tryBasicItem(ModItems.TALKAN.get());
        tryBasicItem(ModItems.TALKAN_BOWL.get());
        tryBasicItem(ModItems.BESHBARMAK.get());
        tryBasicItem(ModItems.PILAF.get());
        tryBasicItem(ModItems.KUURDAK.get());
        tryBasicItem(ModItems.MANTI.get());
        tryBasicItem(ModItems.KHANUM.get());
        tryBasicItem(ModItems.DIMLAMA.get());
        tryBasicItem(ModItems.LAGHMAN.get());
        tryBasicItem(ModItems.KAZAN_KEBAB.get());
        tryBasicItem(ModItems.ASIP.get());
        tryBasicItem(ModItems.RAW_KAZY.get());
        tryBasicItem(ModItems.COOKED_KAZY.get());
        tryBasicItem(ModItems.QARTA.get());
        tryBasicItem(ModItems.SORPA.get());
        tryBasicItem(ModItems.KESPE_KOZHE.get());
        tryBasicItem(ModItems.RAW_SAMSA.get());
        tryBasicItem(ModItems.SAMSA.get());
        tryBasicItem(ModItems.RAW_CHICKEN_SAMSA.get());
        tryBasicItem(ModItems.CHICKEN_SAMSA.get());
        tryBasicItem(ModItems.RAW_PUMPKIN_SAMSA.get());
        tryBasicItem(ModItems.PUMPKIN_SAMSA.get());
        tryBasicItem(ModItems.RAW_PEREMECH.get());
        tryBasicItem(ModItems.PEREMECH.get());
        tryBasicItem(ModItems.RAW_TANDOOR_BREAD.get());
        tryBasicItem(ModItems.TANDOOR_BREAD.get());
        tryBasicItem(ModItems.RAW_KATTAMA.get());
        tryBasicItem(ModItems.KATTAMA.get());
        tryBasicItem(ModItems.RAW_FLATBREAD.get());
        tryBasicItem(ModItems.FLATBREAD.get());
        tryBasicItem(ModItems.RAW_BAURSAKS.get());
        tryBasicItem(ModItems.BAURSAKS.get());
        tryBasicItem(ModItems.HALVA.get());
        tryBasicItem(ModItems.MAYSOK.get());
        tryBasicItem(ModItems.ZHENT.get());
        tryBasicItem(ModItems.QATYQ_BUCKET.get());
        tryBasicItem(ModItems.QURT.get());
        tryBasicItem(ModItems.AYRAN_BUCKET.get());
        tryBasicItem(ModItems.MAIMYZHYK.get());
        tryBasicItem(ModItems.CURD.get());
        tryBasicItem(ModItems.ZHARMA_BUCKET.get());
        tryBasicItem(ModItems.ACHUCHUK_SALAD.get());
        tryBasicItem(ModItems.MEAT_SALAD.get());
        tryBasicItem(ModItems.MORKOVCHA.get());
    }

    private void tryBasicItem(net.minecraft.world.item.Item item) {
        try {
            basicItem(item);
        } catch (IllegalArgumentException e) {
            System.err.println("Designer Warning: Skipping model for " + item.toString() + " because texture is missing.");
        }
    }
}