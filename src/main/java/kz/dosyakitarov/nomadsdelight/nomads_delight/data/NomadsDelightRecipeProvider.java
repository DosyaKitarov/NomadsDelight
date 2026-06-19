package kz.dosyakitarov.nomadsdelight.nomads_delight.data;

import kz.dosyakitarov.nomadsdelight.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.data.builder.CookingPotRecipeBuilder;
import vectorwing.farmersdelight.data.builder.CuttingBoardRecipeBuilder;

import java.util.concurrent.CompletableFuture;

public class NomadsDelightRecipeProvider extends RecipeProvider {
    public NomadsDelightRecipeProvider(DataGenerator generator, CompletableFuture<HolderLookup.Provider> registries) {
        super(generator.getPackOutput(), registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        // Ингредиенты с Farmer's Delight
        Item Dough = vectorwing.farmersdelight.common.registry.ModItems.WHEAT_DOUGH.get();
        Item PumpkinSlice = vectorwing.farmersdelight.common.registry.ModItems.PUMPKIN_SLICE.get();
        Item Tomato = vectorwing.farmersdelight.common.registry.ModItems.TOMATO.get();
        Item Onion = vectorwing.farmersdelight.common.registry.ModItems.ONION.get();
        Item CabbageLeaf = vectorwing.farmersdelight.common.registry.ModItems.CABBAGE_LEAF.get();
        Item Rice = vectorwing.farmersdelight.common.registry.ModItems.RICE.get();
        Item MuttonChops = vectorwing.farmersdelight.common.registry.ModItems.MUTTON_CHOPS.get();
        Item BeefPatty = vectorwing.farmersdelight.common.registry.ModItems.BEEF_PATTY.get();
        Item RawPasta = vectorwing.farmersdelight.common.registry.ModItems.RAW_PASTA.get();

        Ingredient meatForSamsaAsipAndKespeKozhe = Ingredient.of(
                Items.MUTTON,
                Items.BEEF,
                NomadsDelightItems.RAW_HORSE_MEAT
        );

        //Крафты на верстаке
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.RAW_KAZY)
                .requires(NomadsDelightItems.HORSE_INTESTINES)
                .requires(NomadsDelightItems.RAW_HORSE_MEAT)
                .unlockedBy("has_horse_intestines", has(NomadsDelightItems.HORSE_INTESTINES))
                .unlockedBy("has_raw_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT))
                .save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.RAW_SAMSA, 3)
                .requires(meatForSamsaAsipAndKespeKozhe)
                .requires(Items.POTATO)
                .requires(Dough)
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_potato", has(Items.POTATO))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.RAW_CHICKEN_SAMSA, 3)
                .requires(Items.CHICKEN)
                .requires(Items.POTATO)
                .requires(Dough)
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_chicken", has(Items.CHICKEN))
                .unlockedBy("has_potato", has(Items.POTATO))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.RAW_PUMPKIN_SAMSA, 3)
                .requires(PumpkinSlice)
                .requires(Items.POTATO)
                .requires(Dough)
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_pumpkin_slice", has(PumpkinSlice))
                .unlockedBy("has_potato", has(Items.POTATO))
                .save(recipeOutput);

        Ingredient meatForPeremech = Ingredient.of(
                Items.MUTTON,
                Items.BEEF
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NomadsDelightItems.RAW_PEREMECH, 2)
                .pattern("DMD")
                .define('D', Dough)
                .define('M', meatForPeremech)
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_meat", has(Items.MUTTON))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NomadsDelightItems.RAW_TANDOOR_BREAD, 2)
                .pattern("DD")
                .pattern("DD")
                .define('D', Dough)
                .unlockedBy("has_dough", has(Dough))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NomadsDelightItems.RAW_KATTAMA, 2)
                .pattern("RR")
                .pattern("RR")
                .define('R', NomadsDelightItems.ROLLED_DOUGH)
                .unlockedBy("has_rolled_dough", has(NomadsDelightItems.ROLLED_DOUGH))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NomadsDelightItems.RAW_FLATBREAD, 2)
                .pattern("RR")
                .define('R', NomadsDelightItems.ROLLED_DOUGH)
                .unlockedBy("has_rolled_dough", has(NomadsDelightItems.ROLLED_DOUGH))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NomadsDelightItems.RAW_BAURSAKS)
                .pattern("R")
                .define('R', NomadsDelightItems.ROLLED_DOUGH)
                .unlockedBy("has_rolled_dough", has(NomadsDelightItems.ROLLED_DOUGH))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.HALVA)
                .requires(Items.WHEAT)
                .requires(Items.SUGAR)
                .requires(Items.MILK_BUCKET)
                .requires(NomadsDelightItems.BUTTER)
                .unlockedBy("has_butter", has(NomadsDelightItems.BUTTER))
                .unlockedBy("has_wheat", has(Items.WHEAT))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .unlockedBy("has_milk", has(Items.MILK_BUCKET))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.MAYSOK)
                .requires(NomadsDelightItems.ROASTED_MILLET)
                .requires(Items.SUGAR)
                .requires(NomadsDelightItems.BUTTER)
                .unlockedBy("has_butter", has(NomadsDelightItems.BUTTER))
                .unlockedBy("has_roasted_millet", has(NomadsDelightItems.ROASTED_MILLET))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(recipeOutput);

        Ingredient sweetForTalkan = Ingredient.of(
                Items.SUGAR,
                Items.HONEY_BOTTLE
        );

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.TALKAN_BOWL)
                .requires(NomadsDelightItems.TALKAN)
                .requires(sweetForTalkan)
                .requires(Items.MILK_BUCKET)
                .requires(Items.BOWL)
                .unlockedBy("has_talkan", has(NomadsDelightItems.TALKAN))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .unlockedBy("has_milk", has(Items.MILK_BUCKET))
                .unlockedBy("has_bowl", has(Items.BOWL))
                .unlockedBy("has_honey", has(Items.HONEY_BOTTLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.ZHENT)
                .requires(NomadsDelightItems.TALKAN)
                .requires(sweetForTalkan)
                .requires(NomadsDelightItems.BUTTER)
                .unlockedBy("has_butter", has(NomadsDelightItems.BUTTER))
                .unlockedBy("has_talkan", has(NomadsDelightItems.TALKAN))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .unlockedBy("has_honey", has(Items.HONEY_BOTTLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.QURT)
                .requires(NomadsDelightItems.COTTAGE_CHEESE)
                .unlockedBy("has_cottage_cheese", has(NomadsDelightItems.COTTAGE_CHEESE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.AYRAN_BUCKET)
                .requires(NomadsDelightItems.QATYQ_BUCKET)
                .requires(Items.WATER_BUCKET)
                .unlockedBy("has_qatyq_bucket", has(NomadsDelightItems.QATYQ_BUCKET))
                .unlockedBy("has_water", has(Items.WATER_BUCKET))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.MAYMYZHYK)
                .requires(NomadsDelightItems.TANDOOR_BREAD)
                .requires(NomadsDelightItems.BUTTER)
                .unlockedBy("has_butter", has(NomadsDelightItems.BUTTER))
                .unlockedBy("has_tandoor_bread", has(NomadsDelightItems.TANDOOR_BREAD))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.ZHARMA_BUCKET)
                .requires(NomadsDelightItems.TALKAN)
                .requires(Items.WHEAT)
                .requires(Items.WATER_BUCKET)
                .unlockedBy("has_talkan", has(NomadsDelightItems.TALKAN))
                .unlockedBy("has_wheat", has(Items.WHEAT))
                .unlockedBy("has_water", has(Items.WATER_BUCKET))
                .save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.ACHUCHUK_SALAD)
                .requires(Onion)
                .requires(Tomato)
                .requires(Items.BOWL)
                .unlockedBy("has_tomato", has(Tomato))
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_bowl", has(Items.BOWL))
                .save(recipeOutput);

        Ingredient meatForSalad = Ingredient.of(
                Items.COOKED_BEEF,
                Items.COOKED_MUTTON
        );

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.MEAT_SALAD)
                .requires(CabbageLeaf)
                .requires(Tomato)
                .requires(meatForSalad)
                .requires(Items.BOWL)
                .unlockedBy("has_tomato", has(Tomato))
                .unlockedBy("has_cabbage_leaf", has(CabbageLeaf))
                .unlockedBy("has_cooked_beef", has(Items.COOKED_BEEF))
                .unlockedBy("has_cooked_mutton", has(Items.COOKED_MUTTON))
                .unlockedBy("has_bowl", has(Items.BOWL))
                .save(recipeOutput);


        // Крафты в кастрюле
        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.BAURSAKS.get(),
                        1,
                        200,
                        1.0F
                )
                .addIngredient(NomadsDelightItems.RAW_BAURSAKS)
                .unlockedBy("has_raw_baursaks", has(NomadsDelightItems.RAW_BAURSAKS))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);


        Ingredient meatForBeshbarmakPilafAndSorpa = Ingredient.of(
                Items.MUTTON,
                Items.BEEF,
                NomadsDelightItems.RAW_HORSE_MEAT,
                NomadsDelightItems.RAW_KAZY
        );

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.BESHBARMAK.get(),
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(meatForBeshbarmakPilafAndSorpa)
                .addIngredient(Items.WATER_BUCKET)
                .addIngredient(Onion)
                .addIngredient(NomadsDelightItems.ZHAYMA)
                .unlockedBy("has_zhayma", has(NomadsDelightItems.ZHAYMA))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT))
                .unlockedBy("has_kazy", has(NomadsDelightItems.RAW_KAZY))
                .unlockedBy("has_water", has(Items.WATER_BUCKET))
                .unlockedBy("has_onion", has(Onion))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.PILAF.get(),
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(meatForBeshbarmakPilafAndSorpa)
                .addIngredient(Rice)
                .addIngredient(Onion)
                .addIngredient(Items.CARROT)
                .unlockedBy("has_carrot", has(Items.CARROT))
                .unlockedBy("has_rice", has(Rice))
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT))
                .unlockedBy("has_kazy", has(NomadsDelightItems.RAW_KAZY))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);


        Ingredient meatForKuurdak = Ingredient.of(
                Items.MUTTON,
                MuttonChops
        );

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.KUURDAK.get(),
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(meatForKuurdak)
                .addIngredient(meatForKuurdak)
                .addIngredient(Items.POTATO)
                .addIngredient(Items.POTATO)
                .addIngredient(Onion)
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_potato", has(Items.POTATO))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_mutton_chops", has(MuttonChops))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.MANTI,
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(BeefPatty)
                .addIngredient(Dough)
                .addIngredient(Onion)
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_beef_patty", has(BeefPatty))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.KHANUM,
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(BeefPatty)
                .addIngredient(Dough)
                .addIngredient(Onion)
                .addIngredient(PumpkinSlice)
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_beef_patty", has(BeefPatty))
                .unlockedBy("has_pumpkin_slice", has(PumpkinSlice))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        Ingredient meatForDimlama = Ingredient.of(
                Items.MUTTON,
                Items.BEEF,
                NomadsDelightItems.RAW_HORSE_MEAT
        );

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.DIMLAMA,
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(meatForDimlama)
                .addIngredient(Tomato)
                .addIngredient(Onion)
                .addIngredient(CabbageLeaf)
                .addIngredient(Items.CARROT)
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_tomato", has(Tomato))
                .unlockedBy("has_cabbage_leaf", has(CabbageLeaf))
                .unlockedBy("has_carrot", has(Items.CARROT))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        Ingredient meatForLagmanAndKazanKebab = Ingredient.of(
                Items.BEEF,
                Items.MUTTON
        );
        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.LAGHMAN,
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(meatForLagmanAndKazanKebab)
                .addIngredient(Tomato)
                .addIngredient(Items.CARROT)
                .addIngredient(RawPasta)
                .unlockedBy("has_tomato", has(Tomato))
                .unlockedBy("has_carrot", has(Items.CARROT))
                .unlockedBy("has_raw_pasta", has(RawPasta))
                .unlockedBy("has_beef", has(Items.BEEF))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.KAZAN_KEBAB,
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(meatForLagmanAndKazanKebab)
                .addIngredient(Onion)
                .addIngredient(Items.POTATO)
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_potato", has(Items.POTATO))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.ASIP,
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(meatForSamsaAsipAndKespeKozhe)
                .addIngredient(Rice)
                .addIngredient(NomadsDelightItems.HORSE_INTESTINES)
                .unlockedBy("has_rice", has(Rice))
                .unlockedBy("has_horse_intestines", has(NomadsDelightItems.HORSE_INTESTINES))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);


        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.SORPA,
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(meatForBeshbarmakPilafAndSorpa)
                .addIngredient(Items.POTATO)
                .addIngredient(Items.WATER_BUCKET)
                .addIngredient(Items.CARROT)
                .addIngredient(Onion)
                .unlockedBy("has_potato", has(Items.POTATO))
                .unlockedBy("has_water", has(Items.WATER_BUCKET))
                .unlockedBy("has_carrot", has(Items.CARROT))
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT))
                .unlockedBy("has_kazy", has(NomadsDelightItems.RAW_KAZY))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.KESPE_KOZHE,
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(meatForSamsaAsipAndKespeKozhe)
                .addIngredient(Items.POTATO)
                .addIngredient(Items.WATER_BUCKET)
                .addIngredient(RawPasta)
                .addIngredient(Onion)
                .unlockedBy("has_potato", has(Items.POTATO))
                .unlockedBy("has_water", has(Items.WATER_BUCKET))
                .unlockedBy("has_raw_pasta", has(RawPasta))
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        // Рецепты для доски
        CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(Dough),
                        Ingredient.of(NomadsDelightItems.ROLLING_PIN),
                        NomadsDelightItems.ROLLED_DOUGH,
                        1
                )
                .addSound(ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get())
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_rolling_pin", has(NomadsDelightItems.ROLLING_PIN))
                .save(recipeOutput);

        CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(NomadsDelightItems.ROLLED_DOUGH),
                        Ingredient.of(NomadsDelightItems.ROLLING_PIN),
                        NomadsDelightItems.ZHAYMA,
                        1
                )
                .addSound(ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get())
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_rolling_pin", has(NomadsDelightItems.ROLLING_PIN))
                .save(recipeOutput);

        //Выпечка
        registerCookingRecipes(Items.WHEAT, NomadsDelightItems.ROASTED_MILLET, 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_HORSE_MEAT, NomadsDelightItems.COOKED_HORSE_MEAT, 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_SAMSA, NomadsDelightItems.SAMSA, 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_CHICKEN_SAMSA, NomadsDelightItems.CHICKEN_SAMSA, 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_PUMPKIN_SAMSA, NomadsDelightItems.PUMPKIN_SAMSA, 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_PEREMECH, NomadsDelightItems.PEREMECH, 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_TANDOOR_BREAD, NomadsDelightItems.TANDOOR_BREAD, 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_KATTAMA, NomadsDelightItems.KATTAMA, 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_FLATBREAD, NomadsDelightItems.FLATBREAD, 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_BAURSAKS, NomadsDelightItems.BAURSAKS, 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.HORSE_INTESTINES, NomadsDelightItems.QARTA, 0.35f, recipeOutput);
        registerCookingRecipes(Items.MILK_BUCKET, NomadsDelightItems.QATYQ_BUCKET, 0.35f, recipeOutput);
    }

    public static void registerCookingRecipes(ItemLike input, ItemLike output, float experience, RecipeOutput recipeOutput) {
        String inputName = BuiltInRegistries.ITEM.getKey(input.asItem()).getPath();

        SimpleCookingRecipeBuilder.smelting(
                        Ingredient.of(input),
                        RecipeCategory.FOOD,
                        output,
                        experience,
                        200)
                .unlockedBy("has_" + inputName, has(input))
                .save(recipeOutput, BuiltInRegistries.ITEM.getKey(output.asItem()).withSuffix("_from_smelting"));

        SimpleCookingRecipeBuilder.smoking(
                        Ingredient.of(input),
                        RecipeCategory.FOOD,
                        output,
                        experience,
                        100)
                .unlockedBy("has_" + inputName, has(input))
                .save(recipeOutput, BuiltInRegistries.ITEM.getKey(output.asItem()).withSuffix("_from_smoking"));
    }
}
