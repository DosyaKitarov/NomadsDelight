package kz.dosyakitarov.nomads_delight.client;

import kz.dosyakitarov.nomads_delight.client.renderer.ButterChurnRenderer;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlockEntityTypes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

/** Client entry point: block entity renderers and standalone models. */
public class NomadsDelightFabricClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRenderers.register(NomadsDelightBlockEntityTypes.BUTTER_CHURN.get(), ButterChurnRenderer::new);
        ModelLoadingPlugin.register(context -> context.addModels(ButterChurnRenderer.PLUNGER_MODEL));
    }
}
