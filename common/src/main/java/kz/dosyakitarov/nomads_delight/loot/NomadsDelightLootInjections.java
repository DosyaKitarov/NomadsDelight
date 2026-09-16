package kz.dosyakitarov.nomads_delight.loot;

import kz.dosyakitarov.nomads_delight.NomadsDelight;
import net.minecraft.advancements.critereon.EntityEquipmentPredicate;
import net.minecraft.advancements.critereon.EntityFlagsPredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;

import java.util.List;

/**
 * Single source of truth for the horse-drop loot injections. The same definitions feed
 * two very different mechanisms:
 * <ul>
 *   <li>NeoForge: the datagen provider turns them into Global Loot Modifier JSONs;</li>
 *   <li>Fabric: the mod initializer appends equivalent pools to the horse loot table
 *       via {@code LootTableEvents.MODIFY} (Fabric has no GLM system).</li>
 * </ul>
 */
public final class NomadsDelightLootInjections {

    /**
     * Knife tag defined by both the original Farmer's Delight and Refabricated under
     * the same id, referenced as a plain TagKey so common needs no FD classes.
     */
    public static final TagKey<Item> FD_KNIVES = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath("farmersdelight", "tools/knives"));

    public static final ResourceKey<LootTable> RAW_HORSE_MEAT = injectTable("raw_horse_meat");
    public static final ResourceKey<LootTable> COOKED_HORSE_MEAT = injectTable("cooked_horse_meat");
    public static final ResourceKey<LootTable> HORSE_INTESTINES = injectTable("horse_intestines");

    /** One loot injection: roll {@code table} when all {@code conditions} hold. */
    public record Injection(String name, ResourceKey<LootTable> table, List<LootItemCondition> conditions) {
    }

    private NomadsDelightLootInjections() {
    }

    /**
     * The horse drops, with names and condition order matching the originally generated
     * GLM files so datagen output stays byte-identical.
     */
    public static List<Injection> horseDrops() {
        LootItemCondition killedByPlayer = LootItemKilledByPlayerCondition.killedByPlayer().build();
        LootItemCondition isHorse = LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.THIS,
                EntityPredicate.Builder.entity().entityType(EntityTypePredicate.of(EntityType.HORSE))
        ).build();

        LootItemCondition isOnFire = LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.THIS,
                EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(true))
        ).build();

        LootItemCondition isNotOnFire = LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.THIS,
                EntityPredicate.Builder.entity().flags(EntityFlagsPredicate.Builder.flags().setOnFire(false))
        ).build();

        LootItemCondition needsKnife = LootItemEntityPropertyCondition.hasProperties(
                LootContext.EntityTarget.ATTACKER,
                EntityPredicate.Builder.entity().equipment(
                        EntityEquipmentPredicate.Builder.equipment()
                                .mainhand(ItemPredicate.Builder.item().of(FD_KNIVES))
                                .build()
                )
        ).build();

        return List.of(
                new Injection("add_raw_horse_meat_from_horses", RAW_HORSE_MEAT, List.of(isNotOnFire, isHorse)),
                new Injection("add_cooked_horse_meat_from_horses", COOKED_HORSE_MEAT, List.of(isOnFire, isHorse)),
                new Injection("add_horse_intestines_from_horses", HORSE_INTESTINES, List.of(killedByPlayer, isHorse, needsKnife))
        );
    }

    private static ResourceKey<LootTable> injectTable(String path) {
        return ResourceKey.create(Registries.LOOT_TABLE,
                ResourceLocation.fromNamespaceAndPath(NomadsDelight.MODID, "inject/" + path));
    }
}
