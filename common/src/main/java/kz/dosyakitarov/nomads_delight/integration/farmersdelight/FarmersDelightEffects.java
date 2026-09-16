package kz.dosyakitarov.nomads_delight.integration.farmersdelight;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;

/**
 * Farmer's Delight effects resolved through the vanilla registry instead of FD's
 * {@code ModEffects} class. This keeps common free of any compile-time FD dependency:
 * on NeoForge the field type there is a NeoForge {@code DeferredHolder}, on Fabric
 * (Farmer's Delight Refabricated) it is a plain {@code Holder} - but both mods register
 * the effects under the same ids, so a registry lookup behaves identically on every loader.
 */
public final class FarmersDelightEffects {

    public static final String FARMERSDELIGHT_ID = "farmersdelight";

    private static final ResourceLocation COMFORT_ID = ResourceLocation.fromNamespaceAndPath(FARMERSDELIGHT_ID, "comfort");
    private static final ResourceLocation NOURISHMENT_ID = ResourceLocation.fromNamespaceAndPath(FARMERSDELIGHT_ID, "nourishment");

    private FarmersDelightEffects() {
    }

    public static Holder<MobEffect> comfort() {
        return holder(COMFORT_ID);
    }

    public static Holder<MobEffect> nourishment() {
        return holder(NOURISHMENT_ID);
    }

    private static Holder<MobEffect> holder(ResourceLocation id) {
        // Only called at gameplay time (eating, tooltips), long after FD - a required
        // dependency on every loader - has registered its effects.
        return BuiltInRegistries.MOB_EFFECT.getHolder(id)
                .orElseThrow(() -> new IllegalStateException("Farmer's Delight effect is not registered: " + id));
    }
}
