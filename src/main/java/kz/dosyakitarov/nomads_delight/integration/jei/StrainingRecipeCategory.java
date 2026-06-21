package kz.dosyakitarov.nomads_delight.integration.jei;

import kz.dosyakitarov.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlocks;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.ITooltipBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;

public class StrainingRecipeCategory implements IRecipeCategory<StrainingRecipe> {

    public static final RecipeType<StrainingRecipe> STRAINING_TYPE =
            RecipeType.create(Nomads_delight.MODID, "straining", StrainingRecipe.class);

    private static final int WIDTH = 120;
    private static final int HEIGHT = 38;

    private static final int CURD_BAG_ICON_X = 51;
    private static final int CURD_BAG_Y = 10;
    private static final int CURD_BAG_SIZE = 16;

    private final IDrawable background;
    private final IDrawable icon;

    public StrainingRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createBlankDrawable(WIDTH, HEIGHT);
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(NomadsDelightBlocks.CURD_BAG.get()));
    }

    @Override
    public RecipeType<StrainingRecipe> getRecipeType() {
        return STRAINING_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("jei.nomads_delight.category.straining");
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, StrainingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 5, 10)
                .addItemStack(recipe.getInput());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 97, 10)
                .addItemStack(recipe.getOutput());
    }

    @Override
    public void draw(StrainingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        background.draw(guiGraphics);

        var font = Minecraft.getInstance().font;
        guiGraphics.drawCenteredString(font, "->", 35, 16, 0xFFFFFFFF);
        guiGraphics.drawCenteredString(font, "->", 84, 16, 0xFFFFFFFF);

        float centerX = CURD_BAG_ICON_X + CURD_BAG_SIZE / 2f;
        float centerY = CURD_BAG_Y + CURD_BAG_SIZE / 2f;

        guiGraphics.pose().pushPose();
        guiGraphics.pose().translate(centerX, centerY, 0);
        guiGraphics.pose().translate(-CURD_BAG_SIZE / 2f, -CURD_BAG_SIZE / 2f, 0);
        icon.draw(guiGraphics, 0, 0);
        guiGraphics.pose().popPose();
    }

    @Override
    public void getTooltip(ITooltipBuilder tooltip, StrainingRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        boolean overChurnIcon = mouseX >= CURD_BAG_ICON_X && mouseX <= CURD_BAG_ICON_X + CURD_BAG_SIZE
                && mouseY >= CURD_BAG_Y && mouseY <= CURD_BAG_Y + CURD_BAG_SIZE;
        if (overChurnIcon) {
            int seconds = recipe.getChurnTimeTicks() / 20;
            tooltip.add(Component.translatable("jei.nomads_delight.category.straining.time", seconds));
        }
    }
}