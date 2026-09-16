package kz.dosyakitarov.nomads_delight.registry;

import kz.dosyakitarov.nomads_delight.NomadsDelight;

/**
 * Advancement ids and criterion names shared between gameplay code (which awards them)
 * and the NeoForge datagen provider (which generates the advancement JSONs).
 */
public final class NomadsDelightAdvancements {

    public static final String BONK_ID = "bonk";
    public static final String BONK_CRITERION = "killed_with_rolling_pin";

    public static final String DRUNK_ID = "get_drunk";
    public static final String DRUNK_CRITERION = "get_drunk";

    private NomadsDelightAdvancements() {
    }

    public static String titleKey(String id) {
        return "advancements." + NomadsDelight.MODID + "." + id + ".title";
    }

    public static String descKey(String id) {
        return "advancements." + NomadsDelight.MODID + "." + id + ".description";
    }
}
