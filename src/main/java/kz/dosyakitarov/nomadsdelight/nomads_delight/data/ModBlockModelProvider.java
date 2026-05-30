package kz.dosyakitarov.nomadsdelight.nomads_delight.data;

import kz.dosyakitarov.nomadsdelight.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModBlockModelProvider extends BlockStateProvider {
    public ModBlockModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Nomads_delight.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        createCurdBag(ModBlocks.CURD_BAG.get());
    }

    private void createCurdBag(Block block) {
        // Замени lantern на нужный когда закончишь
        var model = models().withExistingParent("curd_bag", "minecraft:block/lantern");
        simpleBlock(block, model);
        simpleBlockItem(block, model);
    }
}

