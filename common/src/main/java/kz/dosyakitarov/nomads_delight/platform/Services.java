package kz.dosyakitarov.nomads_delight.platform;

import kz.dosyakitarov.nomads_delight.NomadsDelight;
import kz.dosyakitarov.nomads_delight.platform.services.IPlatformHelper;
import kz.dosyakitarov.nomads_delight.platform.services.IRegistrar;

import java.util.ServiceLoader;

/**
 * ServiceLoader bridge to loader-specific implementations: each loader module
 * ships a META-INF/services file pointing at its implementation.
 */
public class Services {

    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    public static final IRegistrar REGISTRAR = load(IRegistrar.class);

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        NomadsDelight.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
