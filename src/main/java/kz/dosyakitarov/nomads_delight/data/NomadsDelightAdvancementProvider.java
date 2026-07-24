package kz.dosyakitarov.nomads_delight.data;

import kz.dosyakitarov.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightBlocks;
import kz.dosyakitarov.nomads_delight.registry.NomadsDelightItems;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.EffectsChangedTrigger;
import net.minecraft.advancements.critereon.ImpossibleTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.MobEffectsPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.Optional;
import java.util.function.Consumer;

public class NomadsDelightAdvancementProvider implements AdvancementProvider.AdvancementGenerator {

    private static final String MODID = Nomads_delight.MODID;
    private static final ResourceLocation ROOT_BACKGROUND =
            ResourceLocation.fromNamespaceAndPath("minecraft", "textures/block/dirt.png");

    public static final String BONK_CRITERION = "killed_with_rolling_pin";
    public static final String DRUNK_CRITERION = "get_drunk";


    public static String titleKey(String id) {
        return "advancements." + MODID + "." + id + ".title";
    }

    public static String descKey(String id) {
        return "advancements." + MODID + "." + id + ".description";
    }

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {

        AdvancementHolder root = adv(saver, null, "root",
                NomadsDelightItems.BESHBARMAK.get(), AdvancementType.TASK, false, false, false, ROOT_BACKGROUND,
                AdvancementRequirements.Strategy.AND,
                b -> {
                    b.addCriterion("auto_unlock", net.minecraft.advancements.CriteriaTriggers.TICK.createCriterion(
                            new net.minecraft.advancements.critereon.PlayerTrigger.TriggerInstance(
                                    java.util.Optional.empty()
                            )
                    ));
                }
        );

        adv(saver, root, "eat_horse_on_horse",
                NomadsDelightItems.KAZY.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.OR,
                b -> {
                    net.minecraft.advancements.critereon.ContextAwarePredicate playerPredicate =
                            net.minecraft.advancements.critereon.EntityPredicate.wrap(
                                    net.minecraft.advancements.critereon.EntityPredicate.Builder.entity()
                                            .vehicle(net.minecraft.advancements.critereon.EntityPredicate.Builder.entity()
                                                    .of(net.minecraft.world.entity.EntityType.HORSE))
                            );
                    b.addCriterion("eat_kazy", net.minecraft.advancements.CriteriaTriggers.CONSUME_ITEM.createCriterion(
                            new net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance(
                                    Optional.of(playerPredicate),
                                    Optional.of(net.minecraft.advancements.critereon.ItemPredicate.Builder.item()
                                            .of(NomadsDelightItems.KAZY.get()).build())
                            )
                    ));

                    b.addCriterion("eat_qarta", net.minecraft.advancements.CriteriaTriggers.CONSUME_ITEM.createCriterion(
                            new net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance(
                                    Optional.of(playerPredicate),
                                    Optional.of(net.minecraft.advancements.critereon.ItemPredicate.Builder.item()
                                            .of(NomadsDelightItems.QARTA.get()).build())
                            )
                    ));

                    b.addCriterion("eat_horse_meat", net.minecraft.advancements.CriteriaTriggers.CONSUME_ITEM.createCriterion(
                            new net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance(
                                    Optional.of(playerPredicate),
                                    Optional.of(net.minecraft.advancements.critereon.ItemPredicate.Builder.item()
                                            .of(NomadsDelightItems.COOKED_HORSE_MEAT.get()).build())
                            )
                    ));
                    b.addCriterion("eat_raw_horse_meat", net.minecraft.advancements.CriteriaTriggers.CONSUME_ITEM.createCriterion(
                            new net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance(
                                    Optional.of(playerPredicate),
                                    Optional.of(net.minecraft.advancements.critereon.ItemPredicate.Builder.item()
                                            .of(NomadsDelightItems.RAW_HORSE_MEAT.get()).build())
                            )
                    ));
                }
        );

        AdvancementHolder makeCurdBag = adv(saver, root, "make_curd_bag",
                NomadsDelightBlocks.CURD_BAG.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion("has_curd_bag", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightBlocks.CURD_BAG.get()))
        );

        adv(saver, makeCurdBag, "make_curd",
                NomadsDelightItems.CURD.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion("has_curd", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.CURD.get()))
        );

        AdvancementHolder makeChurn = adv(saver, root, "make_butter_churn",
                NomadsDelightBlocks.BUTTER_CHURN.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion("has_butter_churn", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightBlocks.BUTTER_CHURN.get()))
        );

        AdvancementHolder makeShubatOrQymyz = adv(saver, makeChurn, "make_shubat_or_qymyz",
                NomadsDelightItems.QUMYZ_BUCKET.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.OR,
                b -> {
                    b.addCriterion("has_qymyz", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.QUMYZ_BUCKET.get()));
                    b.addCriterion("has_shubat", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.SHUBAT_BUCKET.get()));
                }
        );

        adv(saver, makeShubatOrQymyz, "get_drunk",
                NomadsDelightItems.QUMYZ_BUCKET.get(), AdvancementType.GOAL, true, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion(DRUNK_CRITERION, CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()))
        );

        adv(saver, makeChurn, "make_butter",
                NomadsDelightItems.BUTTER.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion("has_butter", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.BUTTER.get()))
        );

        AdvancementHolder makeRollingPin = adv(saver, root, "make_rolling_pin",
                NomadsDelightItems.ROLLING_PIN.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion("has_rolling_pin", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.ROLLING_PIN.get()))
        );
        adv(
                saver, makeRollingPin, "bonk",
                NomadsDelightItems.ROLLING_PIN.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion(BONK_CRITERION, CriteriaTriggers.IMPOSSIBLE.createCriterion(new ImpossibleTrigger.TriggerInstance()
                ))
        );

        adv(saver, makeRollingPin, "eat_any_bread",
                NomadsDelightItems.FLATBREAD.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.OR,
                b -> {
                    b.addCriterion("consume_flatbread", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.FLATBREAD.get()));
                    b.addCriterion("consume_tandoor_bread", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.TANDOOR_BREAD.get()));
                    b.addCriterion("consume_kattama", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.KATTAMA.get()));
                    b.addCriterion("consume_baursaks", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.BAURSAKS.get()));
                }
        );
        AdvancementHolder makeZhayma = adv(saver, makeRollingPin, "make_zhayma",
                NomadsDelightItems.ZHAYMA.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion("has_zhayma", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.ZHAYMA.get()))
        );

        adv(saver, makeZhayma, "make_beshbarmak",
                NomadsDelightItems.BESHBARMAK.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion("has_beshbarmak", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.BESHBARMAK.get()))
        );

        adv(saver, root, "milk_horse",
                NomadsDelightItems.HORSE_MILK_BUCKET.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion("has_horse_milk", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.HORSE_MILK_BUCKET.get()))
        );

        adv(saver, root, "milk_camel",
                NomadsDelightItems.CAMEL_MILK_BUCKET.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> b.addCriterion("has_camel_milk", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.CAMEL_MILK_BUCKET.get()))
        );

        adv(saver, root, "make_any_salad",
                NomadsDelightItems.ACHUCHUK_SALAD.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.OR,
                b -> {
                    b.addCriterion("has_achuchuk_salad", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.ACHUCHUK_SALAD.get()));
                    b.addCriterion("has_meat_salad", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.MEAT_SALAD.get()));
                    b.addCriterion("has_morkovcha", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.MORKOVCHA_SALAD.get()));
                }
        );

        adv(saver, root, "make_any_meat_dish",
                NomadsDelightItems.KAZAN_KEBAB.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.OR,
                b -> {
                    b.addCriterion("has_cooked_horse_meat", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.COOKED_HORSE_MEAT.get()));
                    b.addCriterion("has_kuurdak", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.KUURDAK.get()));
                    b.addCriterion("has_dimlama", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.DIMLAMA.get()));
                    b.addCriterion("has_kazan_kebab", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.KAZAN_KEBAB.get()));
                    b.addCriterion("has_sorpa", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.SORPA.get()));
                    b.addCriterion("has_asip", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.ASIP.get()));
                    b.addCriterion("has_cooked_kazy", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.KAZY.get()));
                    b.addCriterion("has_qarta", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.QARTA.get()));
                }
        );

        adv(saver, root, "make_any_snack",
                NomadsDelightItems.BAURSAKS.get(), AdvancementType.TASK, false, null,
                AdvancementRequirements.Strategy.OR,
                b -> {
                    b.addCriterion("has_baursaks", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.BAURSAKS.get()));
                    b.addCriterion("has_halva", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.HALVA.get()));
                    b.addCriterion("has_maysok", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.MAYSOK.get()));
                    b.addCriterion("has_zhent", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.ZHENT.get()));
                    b.addCriterion("has_qurt", InventoryChangeTrigger.TriggerInstance.hasItems(NomadsDelightItems.QURT.get()));
                }
        );

        adv(saver, root, "eat_everything",
                NomadsDelightItems.QUMYZ_BUCKET.get(), AdvancementType.CHALLENGE, false, null,
                AdvancementRequirements.Strategy.AND,
                b -> {
                    b.addCriterion("consume_horse_milk", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.HORSE_MILK_BUCKET.get()));
                    b.addCriterion("consume_qumyz", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.QUMYZ_BUCKET.get()));
                    b.addCriterion("consume_camel_milk", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.CAMEL_MILK_BUCKET.get()));
                    b.addCriterion("consume_shubat", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.SHUBAT_BUCKET.get()));
                    b.addCriterion("consume_sorpa", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.SORPA.get()));
                    b.addCriterion("consume_kespe_kozhe", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.KESPE_KOZHE.get()));
                    b.addCriterion("consume_qatyq", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.QATYQ_BUCKET.get()));
                    b.addCriterion("consume_ayran", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.AYRAN_BUCKET.get()));
                    b.addCriterion("consume_zharma", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.ZHARMA_BUCKET.get()));

                    b.addCriterion("consume_beshbarmak", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.BESHBARMAK.get()));
                    b.addCriterion("consume_pilaf", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.PILAF.get()));
                    b.addCriterion("consume_kuurdak", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.KUURDAK.get()));
                    b.addCriterion("consume_manti", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.MANTI.get()));
                    b.addCriterion("consume_khanum", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.KHANUM.get()));
                    b.addCriterion("consume_dimlama", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.DIMLAMA.get()));
                    b.addCriterion("consume_laghman", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.LAGHMAN.get()));
                    b.addCriterion("consume_kazan_kebab", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.KAZAN_KEBAB.get()));
                    b.addCriterion("consume_asip", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.ASIP.get()));

                    b.addCriterion("consume_cooked_horse_meat", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.COOKED_HORSE_MEAT.get()));
                    b.addCriterion("consume_kazy", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.KAZY.get()));
                    b.addCriterion("consume_qarta", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.QARTA.get()));

                    b.addCriterion("consume_samsa", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.SAMSA.get()));
                    b.addCriterion("consume_chicken_samsa", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.CHICKEN_SAMSA.get()));
                    b.addCriterion("consume_pumpkin_samsa", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.PUMPKIN_SAMSA.get()));
                    b.addCriterion("consume_peremech", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.PEREMECH.get()));
                    b.addCriterion("consume_tandoor_bread", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.TANDOOR_BREAD.get()));
                    b.addCriterion("consume_kattama", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.KATTAMA.get()));
                    b.addCriterion("consume_flatbread", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.FLATBREAD.get()));
                    b.addCriterion("consume_baursaks", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.BAURSAKS.get()));

                    b.addCriterion("consume_butter", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.BUTTER.get()));
                    b.addCriterion("consume_curd", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.CURD.get()));
                    b.addCriterion("consume_qurt", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.QURT.get()));
                    b.addCriterion("consume_achuchuk_salad", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.ACHUCHUK_SALAD.get()));
                    b.addCriterion("consume_meat_salad", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.MEAT_SALAD.get()));
                    b.addCriterion("consume_morkovcha", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.MORKOVCHA_SALAD.get()));

                    b.addCriterion("consume_ready_made_talkan", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.READY_MADE_TALKAN.get()));
                    b.addCriterion("consume_halva", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.HALVA.get()));
                    b.addCriterion("consume_maysok", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.MAYSOK.get()));
                    b.addCriterion("consume_zhent", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.ZHENT.get()));

                    b.addCriterion("consume_raw_horse_meat", net.minecraft.advancements.critereon.ConsumeItemTrigger.TriggerInstance.usedItem(NomadsDelightItems.RAW_HORSE_MEAT.get()));
                }
        );
    }

    private static AdvancementHolder adv(Consumer<AdvancementHolder> saver,
                                         AdvancementHolder parent,
                                         String id,
                                         ItemLike icon,
                                         AdvancementType type,
                                         boolean hidden,
                                         ResourceLocation background,
                                         AdvancementRequirements.Strategy strategy,
                                         Consumer<Advancement.Builder> criteria) {

        Advancement.Builder builder = Advancement.Builder.advancement();
        if (parent != null) {
            builder.parent(parent);
        }

        builder.display(new DisplayInfo(
                new ItemStack(icon.asItem()),
                Component.translatable(titleKey(id)),
                Component.translatable(descKey(id)),
                Optional.ofNullable(background),
                type,
                true,
                true,
                hidden
        ));

        criteria.accept(builder);
        builder.requirements(strategy);

        return builder.save(saver, MODID + ":" + id);
    }

    private static AdvancementHolder adv(Consumer<AdvancementHolder> saver,
                                         AdvancementHolder parent,
                                         String id,
                                         ItemLike icon,
                                         AdvancementType type,
                                         boolean hidden,
                                         boolean showToast,
                                         boolean announceChat,
                                         ResourceLocation background,
                                         AdvancementRequirements.Strategy strategy,
                                         Consumer<Advancement.Builder> criteria) {

        Advancement.Builder builder = Advancement.Builder.advancement();
        if (parent != null) {
            builder.parent(parent);
        }

        builder.display(new DisplayInfo(
                new ItemStack(icon.asItem()),
                Component.translatable(titleKey(id)),
                Component.translatable(descKey(id)),
                Optional.ofNullable(background),
                type,
                showToast,
                announceChat,
                hidden
        ));

        criteria.accept(builder);
        builder.requirements(strategy);

        return builder.save(saver, MODID + ":" + id);
    }
}