package kz.dosyakitarov.nomads_delight.platform;

import kz.dosyakitarov.nomads_delight.platform.services.IClientPlatformHelper;
import net.fabricmc.fabric.api.client.model.loading.v1.FabricBakedModelManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;

public class FabricClientPlatformHelper implements IClientPlatformHelper {

    @Override
    public BakedModel getExtraModel(ResourceLocation id) {
        // Registered via ModelLoadingPlugin in NomadsDelightFabricClient; Fabric API injects
        // FabricBakedModelManager into ModelManager, the cast just makes that explicit.
        return ((FabricBakedModelManager) Minecraft.getInstance().getModelManager()).getModel(id);
    }
}
