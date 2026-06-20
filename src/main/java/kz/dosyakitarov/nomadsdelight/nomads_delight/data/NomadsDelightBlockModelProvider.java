package kz.dosyakitarov.nomadsdelight.nomads_delight.data;

import kz.dosyakitarov.nomadsdelight.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomadsdelight.nomads_delight.util.CeilingHangingBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.BlockModelBuilder;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class NomadsDelightBlockModelProvider extends BlockStateProvider {
    public NomadsDelightBlockModelProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Nomads_delight.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        createCurdBag(NomadsDelightBlocks.CURD_BAG.get());
    }

    private void createCurdBag(Block block) {
        BlockModelBuilder modelEmpty = models().withExistingParent("block/curd_bag_empty", modLoc("block/curd_bag_base"))
                .texture("bag", modLoc("block/curd_bag_empty"))
                .texture("particle", modLoc("block/curd_bag_empty"));

        BlockModelBuilder modelFull = models().withExistingParent("block/curd_bag_full", modLoc("block/curd_bag_base"))
                .texture("bag", modLoc("block/curd_bag_full"))
                .texture("particle", modLoc("block/curd_bag_full"));

        BlockModelBuilder modelReady = models().withExistingParent("block/curd_bag_ready", modLoc("block/curd_bag_base"))
                .texture("bag", modLoc("block/curd_bag_ready"))
                .texture("particle", modLoc("block/curd_bag_ready"));

        getVariantBuilder(block).forAllStates(state -> {
            int bagState = state.getValue(CeilingHangingBlock.BAG_STATE);
            return switch (bagState) {
                case 0 -> ConfiguredModel.builder().modelFile(modelEmpty).build();
                case 1 -> ConfiguredModel.builder().modelFile(modelFull).build();
                case 2 -> ConfiguredModel.builder().modelFile(modelReady).build();
                default -> ConfiguredModel.builder().modelFile(modelEmpty).build();
            };
        });

        simpleBlockItem(block, modelEmpty);
    }

}

