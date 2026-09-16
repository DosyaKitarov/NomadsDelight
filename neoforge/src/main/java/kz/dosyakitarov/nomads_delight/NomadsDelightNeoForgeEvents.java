package kz.dosyakitarov.nomads_delight;

import kz.dosyakitarov.nomads_delight.registry.NomadsDelightInteractions;
import net.minecraft.world.InteractionResult;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

/** Bridges NeoForge game-bus events to the common interaction handlers. */
@EventBusSubscriber(modid = NomadsDelight.MODID)
public class NomadsDelightNeoForgeEvents {

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        InteractionResult result = NomadsDelightInteractions.milkAnimal(
                event.getEntity(), event.getLevel(), event.getHand(), event.getTarget());
        if (result != null) {
            event.setCanceled(true);
            event.setCancellationResult(result);
        }
    }

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        NomadsDelightInteractions.onLivingDeath(event.getEntity(), event.getSource());
    }
}
