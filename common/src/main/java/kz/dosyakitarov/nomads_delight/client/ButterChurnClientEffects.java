package kz.dosyakitarov.nomads_delight.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

/**
 * Client-only visual effects for the butter churn. Kept out of the block entity so the
 * block entity class never references client classes (it is loaded on dedicated servers).
 */
public final class ButterChurnClientEffects {

    private ButterChurnClientEffects() {
    }

    /**
     * Spawns white milk droplets at the plunger impact point. Spit particles are plain white
     * and fall under gravity, but vanilla sizes them like explosion smoke (0.1 to 0.7 blocks),
     * so each one is scaled down after creation.
     */
    public static void spawnImpactDroplets(Level level, double x, double y, double z, int count, float scale) {
        ParticleEngine engine = Minecraft.getInstance().particleEngine;
        RandomSource random = level.random;
        for (int i = 0; i < count; i++) {
            Particle particle = engine.createParticle(ParticleTypes.SPIT,
                    x + (random.nextDouble() - 0.5D) * 0.3D, y, z + (random.nextDouble() - 0.5D) * 0.3D,
                    (random.nextDouble() - 0.5D) * 0.08D, 0.08D + random.nextDouble() * 0.06D,
                    (random.nextDouble() - 0.5D) * 0.08D);
            if (particle != null) {
                particle.scale(scale);
            }
        }
    }
}
