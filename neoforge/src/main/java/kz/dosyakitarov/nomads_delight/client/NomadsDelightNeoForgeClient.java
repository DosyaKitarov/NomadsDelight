package kz.dosyakitarov.nomads_delight.client;

import kz.dosyakitarov.nomads_delight.NomadsDelight;
import kz.dosyakitarov.nomads_delight.client.renderer.ButterChurnRenderer;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlockEntityTypes;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

/** Client-only mod-bus hooks: block entity renderers and standalone models. */
@EventBusSubscriber(modid = NomadsDelight.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class NomadsDelightNeoForgeClient {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(NomadsDelightBlockEntityTypes.BUTTER_CHURN.get(), ButterChurnRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterAdditionalModels(ModelEvent.RegisterAdditional event) {
        event.register(ModelResourceLocation.standalone(ButterChurnRenderer.PLUNGER_MODEL));
    }
}
