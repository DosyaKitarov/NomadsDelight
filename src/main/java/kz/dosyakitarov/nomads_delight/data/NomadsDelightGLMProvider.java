package kz.dosyakitarov.nomads_delight.data;

import kz.dosyakitarov.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import vectorwing.farmersdelight.common.tag.ModTags;

import java.util.concurrent.CompletableFuture;

public class NomadsDelightGLMProvider extends GlobalLootModifierProvider {

    public NomadsDelightGLMProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Nomads_delight.MODID);
    }

    @Override
    protected void start() {
        LootItemCondition killedByPlayer = LootItemKilledByPlayerCondition.killedByPlayer().build();
        LootItemCondition isHorse = LootItemEntityPropertyCondition.hasProperties(

                LootContext.EntityTarget.THIS,
                EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(EntityType.HORSE))
        ).build();

        LootItemCondition needsKnife = LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.ATTACKER,
                EntityPredicate.Builder.entity().equipment(
                        EntityEquipmentPredicate.Builder.equipment()
                                .mainhand(ItemPredicate.Builder.item().of(ModTags.KNIVES))
                                .build()
                )
        ).build();
        addInjectLoot("add_raw_horse_meat_from_horses", NomadsDelightItems.RAW_HORSE_MEAT.get(), killedByPlayer, isHorse);
        addInjectLoot("add_horse_intestines_from_horses", NomadsDelightItems.HORSE_INTESTINES.get(), killedByPlayer, isHorse, needsKnife);
    }

    private void addInjectLoot(String modifierName, Item item, LootItemCondition... conditions) {
        String path = BuiltInRegistries.ITEM.getKey(item).getPath();

        ResourceKey<LootTable> tableKey = ResourceKey.create(
                Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(Nomads_delight.MODID, "inject/" + path)
        );

        add(modifierName, new AddTableLootModifier(conditions, tableKey));
    }
}