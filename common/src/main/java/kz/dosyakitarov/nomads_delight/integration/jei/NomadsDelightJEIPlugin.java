package kz.dosyakitarov.nomads_delight.integration.jei;

import kz.dosyakitarov.nomads_delight.NomadsDelight;
import kz.dosyakitarov.nomads_delight.crafting.ChurnRecipes;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import java.util.List;


@JeiPlugin
public class NomadsDelightJEIPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_ID = ResourceLocation.fromNamespaceAndPath(NomadsDelight.MODID, "jei_plugin");

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        var guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new ChurningRecipeCategory(guiHelper));
        registration.addRecipeCategories(new StrainingRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(
                new ItemStack(NomadsDelightBlocks.BUTTER_CHURN.get()),
                ChurningRecipeCategory.CHURNING_TYPE
        );

        registration.addRecipeCatalyst(
                new ItemStack(NomadsDelightBlocks.CURD_BAG.get()),
                StrainingRecipeCategory.STRAINING_TYPE
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Churning conversions come from the same table the block entity uses.
        List<ChurningRecipe> churningRecipes = ChurnRecipes.all().stream()
                .map(recipe -> new ChurningRecipe(
                        new ItemStack(recipe.input().get()),
                        recipe.createResult(),
                        new ItemStack(recipe.extractionTool().get()),
                        recipe.churnTicks()))
                .toList();

        List<StrainingRecipe> strainingRecipes = List.of(
                new StrainingRecipe(
                        new ItemStack(NomadsDelightItems.QATYQ_BUCKET.get()),
                        new ItemStack(NomadsDelightItems.CURD.get()),
                        2400
                )
        );

        registration.addRecipes(ChurningRecipeCategory.CHURNING_TYPE, churningRecipes);
        registration.addRecipes(StrainingRecipeCategory.STRAINING_TYPE, strainingRecipes);

    }
}