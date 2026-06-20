package kz.dosyakitarov.nomadsdelight.nomads_delight.data;

import kz.dosyakitarov.nomadsdelight.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomadsdelight.nomads_delight.util.CeilingHangingBlock;
import kz.dosyakitarov.nomadsdelight.nomads_delight.util.TallBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
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
        createChurn(NomadsDelightBlocks.CHURN.get());
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

    private void createChurn(Block block) {
        BlockModelBuilder churnBottom = models().withExistingParent("block/churn_bottom", modLoc("block/churn_down"))
                .texture("0", modLoc("block/churn_down_bottom"))
                .texture("1", modLoc("block/churn_down_side"))
                .texture("particle", modLoc("block/churn_down_side"));

        BlockModelBuilder churnTop = models().withExistingParent("block/churn_top", modLoc("block/churn_up"))
                .texture("0", modLoc("block/churn_up_top"))
                .texture("1", modLoc("block/churn_up_other"))
                .texture("particle", modLoc("block/churn_up_top"));

        BlockModelBuilder churnTopFilled = models().withExistingParent("block/churn_up_top_filled", modLoc("block/churn_up"))
                .texture("0", modLoc("block/churn_up_top_filled"))
                .texture("1", modLoc("block/churn_up_other"))
                .texture("particle", modLoc("block/churn_up_top"));

        getVariantBuilder(block).forAllStates(state -> {
            DoubleBlockHalf half = state.getValue(TallBlock.HALF);

            if (half == DoubleBlockHalf.LOWER) {
                return ConfiguredModel.builder().modelFile(churnBottom).build();
            } else {
                int churnState = state.getValue(TallBlock.CHURN_STATE);
                if (churnState == 0) {
                    return ConfiguredModel.builder().modelFile(churnTop).build();
                }
                return ConfiguredModel.builder().modelFile(churnTopFilled).build();
            }
        });

        itemModels().withExistingParent(BuiltInRegistries.BLOCK.getKey(block).getPath(),
                        modLoc("block/churn_item"))
                .texture("0", modLoc("block/churn_down_bottom"));

    }
}

