package kz.dosyakitarov.nomads_delight.data;

import kz.dosyakitarov.nomads_delight.NomadsDelight;
import kz.dosyakitarov.nomads_delight.loot.NomadsDelightLootInjections;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;

import java.util.concurrent.CompletableFuture;

/**
 * Generates the NeoForge Global Loot Modifier JSONs from the shared injection
 * definitions in {@link NomadsDelightLootInjections}. The Fabric module applies the
 * same definitions at runtime instead, since Fabric has no GLM system.
 */
public class NomadsDelightGLMProvider extends GlobalLootModifierProvider {

    public NomadsDelightGLMProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, NomadsDelight.MODID);
    }

    @Override
    protected void start() {
        for (NomadsDelightLootInjections.Injection injection : NomadsDelightLootInjections.horseDrops()) {
            add(injection.name(), new AddTableLootModifier(
                    injection.conditions().toArray(new LootItemCondition[0]),
                    injection.table()
            ));
        }
    }
}
