package kz.dosyakitarov.nomads_delight.integration.jei;

import kz.dosyakitarov.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;


@JeiPlugin
public class NomadsDelightJEIPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_ID = ResourceLocation.fromNamespaceAndPath(Nomads_delight.MODID, "jei_plugin");

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
        ItemStack emptyBucket = new ItemStack(Items.BUCKET);

        List<ChurningRecipe> churningRecipes = List.of(
                new ChurningRecipe(
                        new ItemStack(NomadsDelightItems.HORSE_MILK_BUCKET.get()),
                        new ItemStack(NomadsDelightItems.QUMYZ_BUCKET.get()),
                        emptyBucket,
                        6000
                ),
                new ChurningRecipe(
                        new ItemStack(NomadsDelightItems.CAMEL_MILK_BUCKET.get()),
                        new ItemStack(NomadsDelightItems.SHUBAT_BUCKET.get()),
                        emptyBucket,
                        6000
                ),
                new ChurningRecipe(
                        new ItemStack(Items.MILK_BUCKET),
                        new ItemStack(NomadsDelightItems.BUTTER.get()),
                        6000)
        );

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