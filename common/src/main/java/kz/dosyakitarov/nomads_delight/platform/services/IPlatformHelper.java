package kz.dosyakitarov.nomads_delight.platform.services;

public interface IPlatformHelper {

    /**
     * Gets the name of the current platform.
     */
    String getPlatformName();

    /**
     * Checks if a mod with the given id is loaded.
     */
    boolean isModLoaded(String modId);

    /**
     * Check if the game is currently in a development environment.
     */
    boolean isDevelopmentEnvironment();
}
