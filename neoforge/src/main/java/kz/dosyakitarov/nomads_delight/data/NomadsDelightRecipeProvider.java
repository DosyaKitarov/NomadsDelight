package kz.dosyakitarov.nomads_delight.data;

import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.crafting.CompoundIngredient;
import vectorwing.farmersdelight.client.recipebook.CookingPotRecipeBookTab;
import vectorwing.farmersdelight.common.registry.ModItems;
import vectorwing.farmersdelight.common.registry.ModSounds;
import vectorwing.farmersdelight.common.tag.ModTags;
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
        Item Dough = ModItems.WHEAT_DOUGH.get();
        Item PumpkinSlice = ModItems.PUMPKIN_SLICE.get();
        Item Tomato = ModItems.TOMATO.get();
        Item Onion = ModItems.ONION.get();
        Item CabbageLeaf = ModItems.CABBAGE_LEAF.get();
        Item Rice = ModItems.RICE.get();
        Item MuttonChops = ModItems.MUTTON_CHOPS.get();
        Item MincedBeef = ModItems.MINCED_BEEF.get();
        Item RawPasta = ModItems.RAW_PASTA.get();

        Ingredient meatForSamsaAndKespeKozhe = Ingredient.of(
                Items.MUTTON,
                Items.BEEF,
                NomadsDelightItems.RAW_HORSE_MEAT.get()
        );

        Ingredient woolForCurdBag = CompoundIngredient.of(
                Ingredient.of(ItemTags.WOOL_CARPETS),
                Ingredient.of(ModItems.STRAW.get())
        );

        //Крафты на верстаке
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NomadsDelightItems.ROLLING_PIN.get(), 1)
                .pattern("S")
                .pattern("P")
                .pattern("S")
                .define('P', ItemTags.PLANKS)
                .define('S', Items.STICK)
                .unlockedBy("has_plank", has(ItemTags.PLANKS))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NomadsDelightBlocks.CURD_BAG.get(), 1)
                .pattern(" S ")
                .pattern("W W")
                .pattern("WWW")
                .define('W', woolForCurdBag)
                .define('S', Items.STRING)
                .unlockedBy("has_carpets", has(ItemTags.WOOL_CARPETS))
                .unlockedBy("has_string", has(Items.STRING))
                .unlockedBy("has_straw", has(ModItems.STRAW.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, NomadsDelightBlocks.BUTTER_CHURN.get(), 1)
                .pattern("PIP")
                .pattern("PSP")
                .pattern("PPP")
                .define('P', ItemTags.PLANKS)
                .define('I', Items.STICK)
                .define('S', Items.WOODEN_SHOVEL)
                .unlockedBy("has_plank", has(ItemTags.PLANKS))
                .unlockedBy("has_stick", has(Items.STICK))
                .unlockedBy("has_shovel", has(Items.WOODEN_SHOVEL))
                .save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.RAW_SAMSA.get(), 3)
                .requires(meatForSamsaAndKespeKozhe)
                .requires(Items.POTATO)
                .requires(Dough)
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_potato", has(Items.POTATO))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.RAW_CHICKEN_SAMSA.get(), 3)
                .requires(Items.CHICKEN)
                .requires(Items.POTATO)
                .requires(Dough)
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_chicken", has(Items.CHICKEN))
                .unlockedBy("has_potato", has(Items.POTATO))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.RAW_PUMPKIN_SAMSA.get(), 3)
                .requires(PumpkinSlice)
                .requires(Items.POTATO)
                .requires(Dough)
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_pumpkin_slice", has(PumpkinSlice))
                .unlockedBy("has_potato", has(Items.POTATO))
                .save(recipeOutput);

        Ingredient meatForPeremechAndAsip = Ingredient.of(
                Items.MUTTON,
                Items.BEEF
        );

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NomadsDelightItems.RAW_PEREMECH.get(), 2)
                .pattern("DMD")
                .define('D', Dough)
                .define('M', meatForPeremechAndAsip)
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_meat", has(Items.MUTTON))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NomadsDelightItems.RAW_TANDOOR_BREAD.get(), 2)
                .pattern("DD")
                .pattern("DD")
                .define('D', Dough)
                .unlockedBy("has_dough", has(Dough))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NomadsDelightItems.RAW_KATTAMA.get(), 2)
                .pattern("RR")
                .pattern("RR")
                .define('R', NomadsDelightItems.ROLLED_DOUGH.get())
                .unlockedBy("has_rolled_dough", has(NomadsDelightItems.ROLLED_DOUGH.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NomadsDelightItems.RAW_FLATBREAD.get(), 2)
                .pattern("RR")
                .define('R', NomadsDelightItems.ROLLED_DOUGH.get())
                .unlockedBy("has_rolled_dough", has(NomadsDelightItems.ROLLED_DOUGH.get()))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, NomadsDelightItems.RAW_BAURSAKS.get())
                .pattern("R")
                .define('R', NomadsDelightItems.ROLLED_DOUGH.get())
                .unlockedBy("has_rolled_dough", has(NomadsDelightItems.ROLLED_DOUGH.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.HALVA.get(), 2)
                .requires(Items.WHEAT)
                .requires(Items.SUGAR)
                .requires(Items.MILK_BUCKET)
                .requires(NomadsDelightItems.BUTTER.get())
                .unlockedBy("has_butter", has(NomadsDelightItems.BUTTER.get()))
                .unlockedBy("has_wheat", has(Items.WHEAT))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .unlockedBy("has_milk", has(Items.MILK_BUCKET))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.MAYSOK.get())
                .requires(NomadsDelightItems.ROASTED_MILLET.get())
                .requires(Items.SUGAR)
                .requires(NomadsDelightItems.BUTTER.get())
                .unlockedBy("has_butter", has(NomadsDelightItems.BUTTER.get()))
                .unlockedBy("has_roasted_millet", has(NomadsDelightItems.ROASTED_MILLET.get()))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.TALKAN.get())
                .requires(NomadsDelightItems.ROASTED_MILLET.get())
                .unlockedBy("has_roasted_millet", has(NomadsDelightItems.ROASTED_MILLET.get()))
                .save(recipeOutput);

        Ingredient sweetForTalkan = Ingredient.of(
                Items.SUGAR,
                Items.HONEY_BOTTLE
        );

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.READY_MADE_TALKAN.get())
                .requires(NomadsDelightItems.TALKAN.get())
                .requires(sweetForTalkan)
                .requires(Items.MILK_BUCKET)
                .requires(Items.BOWL)
                .unlockedBy("has_talkan", has(NomadsDelightItems.TALKAN.get()))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .unlockedBy("has_milk", has(Items.MILK_BUCKET))
                .unlockedBy("has_bowl", has(Items.BOWL))
                .unlockedBy("has_honey", has(Items.HONEY_BOTTLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.ZHENT.get())
                .requires(NomadsDelightItems.TALKAN.get())
                .requires(sweetForTalkan)
                .requires(NomadsDelightItems.BUTTER.get())
                .unlockedBy("has_butter", has(NomadsDelightItems.BUTTER.get()))
                .unlockedBy("has_talkan", has(NomadsDelightItems.TALKAN.get()))
                .unlockedBy("has_sugar", has(Items.SUGAR))
                .unlockedBy("has_honey", has(Items.HONEY_BOTTLE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.QURT.get())
                .requires(NomadsDelightItems.CURD.get())
                .unlockedBy("has_curd", has(NomadsDelightItems.CURD.get()))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.AYRAN_BUCKET.get())
                .requires(NomadsDelightItems.QATYQ_BUCKET.get())
                .requires(Items.WATER_BUCKET)
                .unlockedBy("has_qatyq_bucket", has(NomadsDelightItems.QATYQ_BUCKET.get()))
                .unlockedBy("has_water", has(Items.WATER_BUCKET))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.ZHARMA_BUCKET.get())
                .requires(NomadsDelightItems.TALKAN.get())
                .requires(Items.WHEAT)
                .requires(Items.WATER_BUCKET)
                .unlockedBy("has_talkan", has(NomadsDelightItems.TALKAN.get()))
                .unlockedBy("has_wheat", has(Items.WHEAT))
                .unlockedBy("has_water", has(Items.WATER_BUCKET))
                .save(recipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.ACHUCHUK_SALAD.get())
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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.MEAT_SALAD.get())
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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, NomadsDelightItems.MORKOVCHA_SALAD.get())
                .requires(Items.CARROT)
                .requires(Items.CARROT)
                .requires(Onion)
                .requires(Items.BOWL)
                .unlockedBy("has_carrot", has(Items.CARROT))
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_bowl", has(Items.BOWL))
                .save(recipeOutput);


        // Крафты в кастрюле
        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.BAURSAKS.get(),
                        1,
                        200,
                        1.0F
                )
                .addIngredient(NomadsDelightItems.RAW_BAURSAKS.get())
                .unlockedBy("has_raw_baursaks", has(NomadsDelightItems.RAW_BAURSAKS.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);


        Ingredient meatForBeshbarmakPilafAndSorpa = Ingredient.of(
                Items.MUTTON,
                Items.BEEF,
                NomadsDelightItems.RAW_HORSE_MEAT.get(),
                NomadsDelightItems.KAZY.get()
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
                .addIngredient(NomadsDelightItems.ZHAYMA.get())
                .unlockedBy("has_zhayma", has(NomadsDelightItems.ZHAYMA.get()))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT.get()))
                .unlockedBy("has_kazy", has(NomadsDelightItems.KAZY.get()))
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
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT.get()))
                .unlockedBy("has_kazy", has(NomadsDelightItems.KAZY.get()))
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
                        NomadsDelightItems.MANTI.get(),
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(MincedBeef)
                .addIngredient(NomadsDelightItems.ROLLED_DOUGH.get())
                .addIngredient(Onion)
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_rolled_dough", has(NomadsDelightItems.ROLLED_DOUGH.get()))
                .unlockedBy("has_beef_patty", has(MincedBeef))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.KHANUM.get(),
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(MincedBeef)
                .addIngredient(NomadsDelightItems.ROLLED_DOUGH.get())
                .addIngredient(Onion)
                .addIngredient(PumpkinSlice)
                .unlockedBy("has_onion", has(Onion))
                .unlockedBy("has_rolled_dough", has(NomadsDelightItems.ROLLED_DOUGH.get()))
                .unlockedBy("has_beef_patty", has(MincedBeef))
                .unlockedBy("has_pumpkin_slice", has(PumpkinSlice))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        Ingredient meatForDimlama = Ingredient.of(
                Items.MUTTON,
                Items.BEEF,
                NomadsDelightItems.RAW_HORSE_MEAT.get()
        );

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.DIMLAMA.get(),
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
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        Ingredient meatForLagmanAndKazanKebab = Ingredient.of(
                Items.BEEF,
                Items.MUTTON
        );
        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.LAGHMAN.get(),
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
                        NomadsDelightItems.KAZAN_KEBAB.get(),
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
                        NomadsDelightItems.ASIP.get(),
                        1,
                        200,
                        1.0F
                )
                .addIngredient(meatForPeremechAndAsip)
                .addIngredient(Rice)
                .addIngredient(NomadsDelightItems.HORSE_INTESTINES.get())
                .unlockedBy("has_rice", has(Rice))
                .unlockedBy("has_horse_intestines", has(NomadsDelightItems.HORSE_INTESTINES.get()))
                .unlockedBy("has_mutton", has(Items.MUTTON))
                .unlockedBy("has_beef", has(Items.BEEF))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.QARTA.get(),
                        1,
                        200,
                        1.0F
                )
                .addIngredient(Onion)
                .addIngredient(NomadsDelightItems.HORSE_INTESTINES.get())
                .unlockedBy("has_rice", has(Rice))
                .unlockedBy("has_horse_intestines", has(NomadsDelightItems.HORSE_INTESTINES.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.KAZY.get(),
                        1,
                        200,
                        1.0F
                )
                .addIngredient(NomadsDelightItems.RAW_HORSE_MEAT.get())
                .addIngredient(NomadsDelightItems.HORSE_INTESTINES.get())
                .unlockedBy("has_raw_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT.get()))
                .unlockedBy("has_horse_intestines", has(NomadsDelightItems.HORSE_INTESTINES.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);


        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.SORPA.get(),
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
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT.get()))
                .unlockedBy("has_kazy", has(NomadsDelightItems.KAZY.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        CookingPotRecipeBuilder.cookingPotRecipe(
                        NomadsDelightItems.KESPE_KOZHE.get(),
                        1,
                        200,
                        1.0F,
                        Items.BOWL
                )
                .addIngredient(meatForSamsaAndKespeKozhe)
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
                .unlockedBy("has_horse_meat", has(NomadsDelightItems.RAW_HORSE_MEAT.get()))
                .setRecipeBookTab(CookingPotRecipeBookTab.MEALS)
                .save(recipeOutput);

        // Рецепты для доски
        CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(Dough),
                        Ingredient.of(NomadsDelightItems.ROLLING_PIN.get()),
                        NomadsDelightItems.ROLLED_DOUGH.get(),
                        1
                )
                .addSound(ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get())
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_rolling_pin", has(NomadsDelightItems.ROLLING_PIN.get()))
                .save(recipeOutput);

        CuttingBoardRecipeBuilder.cuttingRecipe(
                        Ingredient.of(NomadsDelightItems.ROLLED_DOUGH.get()),
                        Ingredient.of(ModTags.KNIVES),
                        NomadsDelightItems.ZHAYMA.get(),
                        1
                )
                .addSound(ModSounds.BLOCK_CUTTING_BOARD_KNIFE.get())
                .unlockedBy("has_dough", has(Dough))
                .unlockedBy("has_rolling_pin", has(NomadsDelightItems.ROLLING_PIN.get()))
                .save(recipeOutput);

        //Выпечка
        registerCookingRecipes(Items.WHEAT, NomadsDelightItems.ROASTED_MILLET.get(), 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_HORSE_MEAT.get(), NomadsDelightItems.COOKED_HORSE_MEAT.get(), 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_SAMSA.get(), NomadsDelightItems.SAMSA.get(), 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_CHICKEN_SAMSA.get(), NomadsDelightItems.CHICKEN_SAMSA.get(), 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_PUMPKIN_SAMSA.get(), NomadsDelightItems.PUMPKIN_SAMSA.get(), 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_PEREMECH.get(), NomadsDelightItems.PEREMECH.get(), 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_TANDOOR_BREAD.get(), NomadsDelightItems.TANDOOR_BREAD.get(), 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_KATTAMA.get(), NomadsDelightItems.KATTAMA.get(), 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_FLATBREAD.get(), NomadsDelightItems.FLATBREAD.get(), 0.35f, recipeOutput);
        registerCookingRecipes(NomadsDelightItems.RAW_BAURSAKS.get(), NomadsDelightItems.BAURSAKS.get(), 0.35f, recipeOutput);
        registerCookingRecipes(Items.MILK_BUCKET, NomadsDelightItems.QATYQ_BUCKET.get(), 0.35f, recipeOutput);
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
