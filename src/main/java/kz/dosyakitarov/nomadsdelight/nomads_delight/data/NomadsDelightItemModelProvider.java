package kz.dosyakitarov.nomadsdelight.nomads_delight.data;

import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.data.DataGenerator;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class NomadsDelightItemModelProvider extends ItemModelProvider {
    public NomadsDelightItemModelProvider(DataGenerator generator, String modId, ExistingFileHelper existingFileHelper) {
        super(generator.getPackOutput(), modId, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        tryBasicItem(NomadsDelightItems.ROLLING_PIN.get());
        tryBasicItem(NomadsDelightItems.QUMYZ_BUCKET.get());
        tryBasicItem(NomadsDelightItems.HORSE_MILK_BUCKET.get());
        tryBasicItem(NomadsDelightItems.SHUBAT_BUCKET.get());
        tryBasicItem(NomadsDelightItems.CAMEL_MILK_BUCKET.get());
        tryBasicItem(NomadsDelightItems.BUTTER.get());
        tryBasicItem(NomadsDelightItems.COTTAGE_CHEESE.get());
        tryBasicItem(NomadsDelightItems.RAW_HORSE_MEAT.get());
        tryBasicItem(NomadsDelightItems.COOKED_HORSE_MEAT.get());
        tryBasicItem(NomadsDelightItems.ROLLED_DOUGH.get());
        tryBasicItem(NomadsDelightItems.ZHAYMA.get());
        tryBasicItem(NomadsDelightItems.ROASTED_MILLET.get());
        tryBasicItem(NomadsDelightItems.HORSE_INTESTINES.get());
        tryBasicItem(NomadsDelightItems.TALKAN.get());
        tryBasicItem(NomadsDelightItems.READY_MADE_TALKAN.get());
        tryBasicItem(NomadsDelightItems.BESHBARMAK.get());
        tryBasicItem(NomadsDelightItems.PILAF.get());
        tryBasicItem(NomadsDelightItems.KUURDAK.get());
        tryBasicItem(NomadsDelightItems.MANTI.get());
        tryBasicItem(NomadsDelightItems.KHANUM.get());
        tryBasicItem(NomadsDelightItems.DIMLAMA.get());
        tryBasicItem(NomadsDelightItems.LAGHMAN.get());
        tryBasicItem(NomadsDelightItems.KAZAN_KEBAB.get());
        tryBasicItem(NomadsDelightItems.ASIP.get());
        tryBasicItem(NomadsDelightItems.RAW_KAZY.get());
        tryBasicItem(NomadsDelightItems.COOKED_KAZY.get());
        tryBasicItem(NomadsDelightItems.QARTA.get());
        tryBasicItem(NomadsDelightItems.SORPA.get());
        tryBasicItem(NomadsDelightItems.KESPE_KOZHE.get());
        tryBasicItem(NomadsDelightItems.RAW_SAMSA.get());
        tryBasicItem(NomadsDelightItems.SAMSA.get());
        tryBasicItem(NomadsDelightItems.RAW_CHICKEN_SAMSA.get());
        tryBasicItem(NomadsDelightItems.CHICKEN_SAMSA.get());
        tryBasicItem(NomadsDelightItems.RAW_PUMPKIN_SAMSA.get());
        tryBasicItem(NomadsDelightItems.PUMPKIN_SAMSA.get());
        tryBasicItem(NomadsDelightItems.RAW_PEREMECH.get());
        tryBasicItem(NomadsDelightItems.PEREMECH.get());
        tryBasicItem(NomadsDelightItems.RAW_TANDOOR_BREAD.get());
        tryBasicItem(NomadsDelightItems.TANDOOR_BREAD.get());
        tryBasicItem(NomadsDelightItems.RAW_KATTAMA.get());
        tryBasicItem(NomadsDelightItems.KATTAMA.get());
        tryBasicItem(NomadsDelightItems.RAW_FLATBREAD.get());
        tryBasicItem(NomadsDelightItems.FLATBREAD.get());
        tryBasicItem(NomadsDelightItems.RAW_BAURSAKS.get());
        tryBasicItem(NomadsDelightItems.BAURSAKS.get());
        tryBasicItem(NomadsDelightItems.HALVA.get());
        tryBasicItem(NomadsDelightItems.MAYSOK.get());
        tryBasicItem(NomadsDelightItems.ZHENT.get());
        tryBasicItem(NomadsDelightItems.QATYQ_BUCKET.get());
        tryBasicItem(NomadsDelightItems.QURT.get());
        tryBasicItem(NomadsDelightItems.AYRAN_BUCKET.get());
        tryBasicItem(NomadsDelightItems.MAYMYZHYK.get());
        tryBasicItem(NomadsDelightItems.CURD.get());
        tryBasicItem(NomadsDelightItems.ZHARMA_BUCKET.get());
        tryBasicItem(NomadsDelightItems.ACHUCHUK_SALAD.get());
        tryBasicItem(NomadsDelightItems.MEAT_SALAD.get());
        tryBasicItem(NomadsDelightItems.MORKOVCHA.get());
    }

    private void tryBasicItem(net.minecraft.world.item.Item item) {
        try {
            basicItem(item);
        } catch (IllegalArgumentException e) {
            System.err.println("Warning: Skipping model for " + item.toString() + " because texture is missing.");
        }
    }
}