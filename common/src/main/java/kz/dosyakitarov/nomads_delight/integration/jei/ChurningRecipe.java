package kz.dosyakitarov.nomads_delight.integration.jei;

import net.minecraft.world.item.ItemStack;


public class ChurningRecipe {
    private final ItemStack input;
    private final ItemStack output;
    private final ItemStack extractionTool;
    private final int churnTimeTicks;

    public ChurningRecipe(ItemStack input, ItemStack output, int churnTimeTicks) {
        this(input, output, ItemStack.EMPTY, churnTimeTicks);
    }

    public ChurningRecipe(ItemStack input, ItemStack output, ItemStack extractionTool, int churnTimeTicks) {
        this.input = input;
        this.output = output;
        this.extractionTool = extractionTool;
        this.churnTimeTicks = churnTimeTicks;
    }

    public ItemStack getInput() {
        return input;
    }

    public ItemStack getOutput() {
        return output;
    }

    public ItemStack getExtractionTool() {
        return extractionTool;
    }

    public int getChurnTimeTicks() {
        return churnTimeTicks;
    }
}
