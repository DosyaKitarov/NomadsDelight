package kz.dosyakitarov.nomads_delight.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * Food that applies mob effects when eaten and lists them in its tooltip.
 * Replaces the anonymous {@code Item} subclasses of the original NeoForge-only mod.
 *
 * <p>Effects are supplied lazily ({@code Supplier<Holder<MobEffect>>}) because the
 * Farmer's Delight effects are resolved from the registry at gameplay time.</p>
 */
public class EffectFoodItem extends Item {

    /** An effect applied on consumption. {@code chance} of 1 always applies. */
    public record TimedEffect(Supplier<Holder<MobEffect>> effect, int durationTicks, float chance) {
        public static TimedEffect of(Supplier<Holder<MobEffect>> effect, int durationTicks) {
            return new TimedEffect(effect, durationTicks, 1.0F);
        }
    }

    /**
     * A tooltip line advertising an effect. Kept separate from {@link TimedEffect} because
     * the original mod does not tooltip every applied effect (e.g. the hidden nausea chance
     * on qumyz/shubat) and in one case (qurt) advertises a different effect than it applies.
     */
    public record EffectTooltip(Supplier<Holder<MobEffect>> effect, int seconds) {
    }

    private final List<TimedEffect> effects;
    private final List<EffectTooltip> tooltips;

    public EffectFoodItem(Properties properties, List<TimedEffect> effects, List<EffectTooltip> tooltips) {
        super(properties);
        this.effects = List.copyOf(effects);
        this.tooltips = List.copyOf(tooltips);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (!level.isClientSide) {
            applyEffects(entity);
        }

        return result;
    }

    protected void applyEffects(LivingEntity entity) {
        for (TimedEffect effect : effects) {
            if (effect.chance() >= 1.0F || ThreadLocalRandom.current().nextFloat() < effect.chance()) {
                entity.addEffect(new MobEffectInstance(effect.effect().get(), effect.durationTicks(), 0));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
        for (EffectTooltip line : tooltips) {
            tooltip.add(effectLine(line.effect().get(), line.seconds()).withStyle(ChatFormatting.BLUE));
        }

        super.appendHoverText(stack, context, tooltip, flag);
    }

    /** Formats "Effect Name (m:ss)" from the mod's own translation keys. */
    public static MutableComponent effectLine(Holder<MobEffect> effect, int durationSeconds) {
        String[] split = effect.getRegisteredName().split(":");
        String effectId = split[split.length - 1];
        int minutes = durationSeconds / 60;
        int seconds = durationSeconds % 60;
        return Component.translatable("tooltip.nomads_delight.effect_format",
                Component.translatable("tooltip.nomads_delight.effect." + effectId),
                String.format("%d:%02d", minutes, seconds));
    }
}
