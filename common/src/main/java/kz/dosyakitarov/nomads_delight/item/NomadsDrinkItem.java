package kz.dosyakitarov.nomads_delight.item;

import kz.dosyakitarov.nomads_delight.registry.NomadsDelightInteractions;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import java.util.List;

/**
 * A drinkable {@link EffectFoodItem}: drink animation/sounds, and either a set of
 * timed effects or "clears all effects" behavior (fermented milks vs. fresh milks).
 */
public class NomadsDrinkItem extends EffectFoodItem {

    private final SoundEvent drinkSound;
    private final boolean clearsEffects;

    /** Drink that applies effects on consumption. */
    public NomadsDrinkItem(Properties properties, SoundEvent drinkSound,
                           List<TimedEffect> effects, List<EffectTooltip> tooltips) {
        super(properties, effects, tooltips);
        this.drinkSound = drinkSound;
        this.clearsEffects = false;
    }

    /** Drink that removes all active effects on consumption (milk-like). */
    public NomadsDrinkItem(Properties properties, SoundEvent drinkSound) {
        super(properties, List.of(), List.of());
        this.drinkSound = drinkSound;
        this.clearsEffects = true;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide) {
            if (clearsEffects) {
                entity.removeAllEffects();
            }
            // Replaces the original LivingEntityUseItemEvent.Finish handler; Fabric has no
            // equivalent event, and the item's own hook behaves identically on every loader.
            NomadsDelightInteractions.checkDrunkAdvancement(entity, stack);
        }

        return result;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return drinkSound;
    }

    @Override
    public SoundEvent getEatingSound() {
        return getDrinkingSound();
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        if (clearsEffects) {
            tooltip.add(Component.translatable("tooltip.nomads_delight.removes_effects").withStyle(ChatFormatting.BLUE));
        }

        super.appendHoverText(stack, context, tooltip, flag);
    }
}
