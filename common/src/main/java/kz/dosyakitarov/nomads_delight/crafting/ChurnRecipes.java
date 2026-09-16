package kz.dosyakitarov.nomads_delight.crafting;

import kz.dosyakitarov.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * The butter churn's conversions. One in-code table shared by the block entity (gameplay)
 * and the JEI plugin (display) so timings and tools cannot drift apart.
 * Items are held as suppliers because this class may be initialised before registration
 * has run on NeoForge.
 * <p>
 * Datapack-driven recipes with a serializer are a planned follow-up.
 */
public final class ChurnRecipes {

    /** Ticks a churn needs to finish on passive progress alone (5 minutes). */
    public static final int DEFAULT_CHURN_TICKS = 3000;

    /**
     * @param input          the item that goes into the churn (a milk bucket)
     * @param extractionTool the item consumed to take the result out (bucket or bowl)
     * @param result         the item handed to the player on extraction
     * @param churnTicks     passive progress needed to finish
     * @param extractSound   sound played on extraction
     */
    public record ChurnRecipe(Supplier<Item> input, Supplier<Item> extractionTool, Supplier<Item> result,
                              int churnTicks, SoundEvent extractSound) {

        public boolean matchesInput(ItemStack stack) {
            return !stack.isEmpty() && stack.is(input.get());
        }

        public boolean matchesTool(ItemStack stack) {
            return !stack.isEmpty() && stack.is(extractionTool.get());
        }

        public ItemStack createResult() {
            return new ItemStack(result.get());
        }
    }

    private static final List<ChurnRecipe> RECIPES = List.of(
            new ChurnRecipe(NomadsDelightItems.HORSE_MILK_BUCKET, () -> Items.BUCKET, NomadsDelightItems.QUMYZ_BUCKET,
                    DEFAULT_CHURN_TICKS, SoundEvents.BUCKET_FILL),
            new ChurnRecipe(NomadsDelightItems.CAMEL_MILK_BUCKET, () -> Items.BUCKET, NomadsDelightItems.SHUBAT_BUCKET,
                    DEFAULT_CHURN_TICKS, SoundEvents.BUCKET_FILL),
            new ChurnRecipe(() -> Items.MILK_BUCKET, () -> Items.BOWL, NomadsDelightItems.BUTTER,
                    DEFAULT_CHURN_TICKS, SoundEvents.SLIME_BLOCK_BREAK)
    );

    private ChurnRecipes() {
    }

    public static List<ChurnRecipe> all() {
        return RECIPES;
    }

    /** Finds the recipe whose input matches the given stack. */
    public static Optional<ChurnRecipe> forInput(ItemStack stack) {
        for (ChurnRecipe recipe : RECIPES) {
            if (recipe.matchesInput(stack)) {
                return Optional.of(recipe);
            }
        }
        return Optional.empty();
    }
}
