package kz.dosyakitarov.nomads_delight.platform;

import kz.dosyakitarov.nomads_delight.platform.services.IClientPlatformHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;

public class NeoForgeClientPlatformHelper implements IClientPlatformHelper {

    @Override
    public BakedModel getExtraModel(ResourceLocation id) {
        // Registered via ModelEvent.RegisterAdditional in NomadsDelightNeoForgeClient.
        return Minecraft.getInstance().getModelManager().getModel(ModelResourceLocation.standalone(id));
    }
}
