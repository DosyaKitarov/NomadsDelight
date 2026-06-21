package kz.dosyakitarov.nomads_delight.data;

import kz.dosyakitarov.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class NomadsDelightLootTableProvider extends LootTableProvider {

    public NomadsDelightLootTableProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(
                output,
                Set.of(),
                List.of(
                        new LootTableProvider.SubProviderEntry(InjectLootTables::new, LootContextParamSets.ENTITY)
                ),
                registries
        );
    }

    public static class InjectLootTables implements LootTableSubProvider {
        public InjectLootTables(HolderLookup.Provider provider) {
        }

        @Override
        public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder) {
            registerInject(builder, NomadsDelightItems.RAW_HORSE_MEAT.get(), 1.0F, 3.0F);
            registerInject(builder, NomadsDelightItems.HORSE_INTESTINES.get(), 1.0F, 1.0F);

        }

        private void registerInject(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> builder, Item item, float minDrops, float maxDrops) {
            String path = BuiltInRegistries.ITEM.getKey(item).getPath();

            ResourceKey<LootTable> tableKey = ResourceKey.create(
                    Registries.LOOT_TABLE,
                    ResourceLocation.fromNamespaceAndPath(Nomads_delight.MODID, "inject/" + path)
            );

            builder.accept(tableKey, LootTable.lootTable()
                    .withPool(LootPool.lootPool()
                            .setRolls(UniformGenerator.between(1.0F, 1.0F))
                            .add(LootItem.lootTableItem(item)
                                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                            )
                    )
            );
        }
    }
}