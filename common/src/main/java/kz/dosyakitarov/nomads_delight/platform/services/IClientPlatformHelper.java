package kz.dosyakitarov.nomads_delight.platform.services;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;

/**
 * Client-only loader hooks. Loaded through {@code ClientServices}, never from common
 * server code, so the implementations may reference client classes freely.
 */
public interface IClientPlatformHelper {

    /**
     * Returns a baked model that is not tied to a block state or item, registered by the
     * loader's client entry point (NeoForge {@code ModelEvent.RegisterAdditional}, Fabric
     * {@code ModelLoadingPlugin}). Returns the missing model if it was never registered.
     *
     * @param id the model file id, e.g. {@code nomads_delight:block/churn_plunger}
     */
    BakedModel getExtraModel(ResourceLocation id);
}
