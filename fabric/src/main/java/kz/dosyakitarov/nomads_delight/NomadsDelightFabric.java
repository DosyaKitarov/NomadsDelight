package kz.dosyakitarov.nomads_delight;

import kz.dosyakitarov.nomads_delight.loot.NomadsDelightLootInjections;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightInteractions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;

public class NomadsDelightFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        NomadsDelight.init();
        registerEventHandlers();
        registerLootInjections();
    }

    /** Fabric callbacks bridging to the common interaction handlers. */
    private static void registerEventHandlers() {
        UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
            InteractionResult result = NomadsDelightInteractions.milkAnimal(player, level, hand, entity);
            return result != null ? result : InteractionResult.PASS;
        });

        ServerLivingEntityEvents.AFTER_DEATH.register(NomadsDelightInteractions::onLivingDeath);
    }

    /**
     * Fabric counterpart of the NeoForge Global Loot Modifiers: appends the shared
     * inject tables (with the same conditions) to the horse loot table.
     */
    private static void registerLootInjections() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!source.isBuiltin() || !EntityType.HORSE.getDefaultLootTable().equals(key)) {
                return;
            }

            for (NomadsDelightLootInjections.Injection injection : NomadsDelightLootInjections.horseDrops()) {
                LootPool.Builder pool = LootPool.lootPool()
                        .add(NestedLootTable.lootTableReference(injection.table()));
                injection.conditions().forEach(condition -> pool.when(() -> condition));
                tableBuilder.withPool(pool);
            }
        });
    }
}
