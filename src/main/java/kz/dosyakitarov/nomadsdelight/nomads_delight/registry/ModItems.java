package kz.dosyakitarov.nomadsdelight.nomads_delight.registry;

import kz.dosyakitarov.nomadsdelight.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomadsdelight.nomads_delight.util.JsonReader;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItems {

    private static final JsonReader FOODS_JSON =
            new JsonReader("data/nomads_delight/food_properties/food_properties.json");

    private static final JsonReader ITEMS_JSON =
            new JsonReader("data/nomads_delight/item_properties/item_properties.json");

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Nomads_delight.MODID);

    public static final DeferredItem<Item> RAW_HORSE_MEAT = ITEMS.register("raw_horse_meat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(FOODS_JSON.getNutrition("raw_horse_meat"))
                    .saturationModifier(FOODS_JSON.getSaturation("raw_horse_meat"))
                    .build())));

    public static final DeferredItem<Item> COOKED_HORSE_MEAT = ITEMS.register("cooked_horse_meat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(FOODS_JSON.getNutrition("cooked_horse_meat"))
                    .saturationModifier(FOODS_JSON.getSaturation("cooked_horse_meat"))
                    .build())));


    public static final DeferredItem<Item> KYMYZ_BUCKET = ITEMS.register("kymyz_bucket",
            () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .usingConvertsTo(Items.BUCKET)
                            .build()
                    )) {
                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }

                @Override
                public SoundEvent getDrinkingSound() {
                    return SoundEvents.GENERIC_DRINK;
                }

                @Override
                public SoundEvent getEatingSound() {
                    return getDrinkingSound();
                }
            }

    );
    public static final DeferredItem<Item> KYMYZ_BOTTLE = ITEMS.register("kymyz_bottle",
            () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .usingConvertsTo(Items.GLASS_BOTTLE)
                            .build()
                    )) {
                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }

                @Override
                public SoundEvent getDrinkingSound() {
                    return SoundEvents.GENERIC_DRINK;
                }

                @Override
                public SoundEvent getEatingSound() {
                    return getDrinkingSound();
                }
            }
    );

    public static final DeferredItem<Item> ROLLING_PIN = ITEMS.register("rolling_pin",
            () -> new SwordItem(
                    Tiers.WOOD,
                    new Item.Properties()
                            .durability(ITEMS_JSON.getInt("rolling_pin", "durability"))
                            .attributes(
                                    SwordItem.createAttributes(
                                            Tiers.WOOD,
                                            ITEMS_JSON.getInt("rolling_pin", "damage"),
                                            ITEMS_JSON.getFloat("rolling_pin", "attack_speed")
                                    )
                            )
            )
    );

    public static final DeferredItem<Item> ROLLED_DOUGH = ITEMS.register("rolled_dough",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> ZHAYMA = ITEMS.register("zhayma",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> ROASTED_MILLET = ITEMS.register("roasted_millet",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> HORSE_INTESTINES = ITEMS.register("horse_intestines",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> TALKAN = ITEMS.register("talkan",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> TALKAN_BOWL = ITEMS.register("talkan_bowl",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("talkan_bowl", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("talkan_bowl"))
                            .saturationModifier(FOODS_JSON.getSaturation("talkan_bowl"))
                            .usingConvertsTo(Items.BOWL)
                            .build()
                    )) {
            }
    );

    public static final DeferredItem<Item> BESHBARMAK = ITEMS.register("beshbarmak",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("beshbarmak", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("beshbarmak"))
                            .saturationModifier(FOODS_JSON.getSaturation("beshbarmak"))
                            .build())
            ));

    public static final DeferredItem<Item> PILAF = ITEMS.register("pilaf",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("pilaf", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("pilaf"))
                            .saturationModifier(FOODS_JSON.getSaturation("pilaf"))
                            .build())
            ));

    public static final DeferredItem<Item> KUURDAK = ITEMS.register("kuurdak",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("kuurdak", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("kuurdak"))
                            .saturationModifier(FOODS_JSON.getSaturation("kuurdak"))
                            .build())
            ));


    public static final DeferredItem<Item> MANTI = ITEMS.register("manti",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("manti", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("manti"))
                            .saturationModifier(FOODS_JSON.getSaturation("manti"))
                            .build())
            ));

    public static final DeferredItem<Item> KHANUM = ITEMS.register("khanum",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("khanum", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("khanum"))
                            .saturationModifier(FOODS_JSON.getSaturation("khanum"))
                            .build())
            ));

    public static final DeferredItem<Item> DIMLAMA = ITEMS.register("dimlama",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("dimlama", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("dimlama"))
                            .saturationModifier(FOODS_JSON.getSaturation("dimlama"))
                            .build())
            ));

    public static final DeferredItem<Item> LAGHMAN = ITEMS.register("laghman",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("laghman", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("laghman"))
                            .saturationModifier(FOODS_JSON.getSaturation("laghman"))
                            .build())
            ));

    public static final DeferredItem<Item> KAZAN_KEBAB = ITEMS.register("kazan_kebab",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("kazan_kebab", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("kazan_kebab"))
                            .saturationModifier(FOODS_JSON.getSaturation("kazan_kebab"))
                            .build())
            ));

    public static final DeferredItem<Item> ASIP = ITEMS.register("asip",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("asip", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("asip"))
                            .saturationModifier(FOODS_JSON.getSaturation("asip"))
                            .build())
            ));

    public static final DeferredItem<Item> RAW_KAZY = ITEMS.register("raw_kazy",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("raw_kazy"))
                            .saturationModifier(FOODS_JSON.getSaturation("raw_kazy"))
                            .build())
            ));

    public static final DeferredItem<Item> COOKED_KAZY = ITEMS.register("cooked_kazy",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("cooked_kazy"))
                            .saturationModifier(FOODS_JSON.getSaturation("cooked_kazy"))
                            .build())
            ));

    public static final DeferredItem<Item> QARTA = ITEMS.register("qarta",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("qarta"))
                            .saturationModifier(FOODS_JSON.getSaturation("qarta"))
                            .build())
            ));

    public static final DeferredItem<Item> SORPA = ITEMS.register("sorpa",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("sorpa", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("sorpa"))
                            .saturationModifier(FOODS_JSON.getSaturation("sorpa"))
                            .build())
            ));
    public static final DeferredItem<Item> KESPE_KOZHE = ITEMS.register("kespe_kozhe",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("kespe_kozhe", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("kespe_kozhe"))
                            .saturationModifier(FOODS_JSON.getSaturation("kespe_kozhe"))
                            .build())
            ));
    public static final DeferredItem<Item> RAW_SAMSA = ITEMS.register("raw_samsa",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> SAMSA = ITEMS.register("samsa",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("samsa"))
                            .saturationModifier(FOODS_JSON.getSaturation("samsa"))
                            .build())
            ));
    public static final DeferredItem<Item> RAW_CHICKEN_SAMSA = ITEMS.register("raw_chicken_samsa",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> CHICKEN_SAMSA = ITEMS.register("chicken_samsa",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("chicken_samsa"))
                            .saturationModifier(FOODS_JSON.getSaturation("chicken_samsa"))
                            .build())
            ));

    public static final DeferredItem<Item> RAW_PUMPKIN_SAMSA = ITEMS.register("raw_pumpkin_samsa",
            () -> new Item(new Item.Properties()
            ));


    public static final DeferredItem<Item> PUMPKIN_SAMSA = ITEMS.register("pumpkin_samsa",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("pumpkin_samsa"))
                            .saturationModifier(FOODS_JSON.getSaturation("pumpkin_samsa"))
                            .build())
            ));


    public static final DeferredItem<Item> RAW_PEREMECH = ITEMS.register("raw_peremech",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> PEREMECH = ITEMS.register("peremech",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("peremech"))
                            .saturationModifier(FOODS_JSON.getSaturation("peremech"))
                            .build())
            ));

    public static final DeferredItem<Item> RAW_TANDOOR_BREAD = ITEMS.register("raw_tandoor_bread",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> TANDOOR_BREAD = ITEMS.register("tandoor_bread",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("tandoor_bread"))
                            .saturationModifier(FOODS_JSON.getSaturation("tandoor_bread"))
                            .build())
            ));

    public static final DeferredItem<Item> RAW_KATTAMA = ITEMS.register("raw_kattama",
            () -> new Item(new Item.Properties()
            ));


    public static final DeferredItem<Item> KATTAMA = ITEMS.register("kattama",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("kattama"))
                            .saturationModifier(FOODS_JSON.getSaturation("kattama"))
                            .build())
            ));

    public static final DeferredItem<Item> RAW_FLATBREAD = ITEMS.register("raw_flatbread",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> FLATBREAD = ITEMS.register("flatbread",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("flatbread"))
                            .saturationModifier(FOODS_JSON.getSaturation("flatbread"))
                            .build())
            ));

    public static final DeferredItem<Item> RAW_BAURSAKS = ITEMS.register("raw_baursaks",
            () -> new Item(new Item.Properties()
            ));

    public static final DeferredItem<Item> BAURSAKS = ITEMS.register("baursaks",
            () -> new Item(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("baursaks", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("baursaks"))
                            .saturationModifier(FOODS_JSON.getSaturation("baursaks"))
                            .build())
            ));

    public static final DeferredItem<Item> HALVA = ITEMS.register("halva",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("halva"))
                            .saturationModifier(FOODS_JSON.getSaturation("halva"))
                            .build())
            ));

    public static final DeferredItem<Item> MAYSOK = ITEMS.register("maysok",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("maysok"))
                            .saturationModifier(FOODS_JSON.getSaturation("maysok"))
                            .build())
            ));

    public static final DeferredItem<Item> ZHENT = ITEMS.register("zhent",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("zhent"))
                            .saturationModifier(FOODS_JSON.getSaturation("zhent"))
                            .build())
            ));

    public static final DeferredItem<Item> QATYQ_BUCKET = ITEMS.register("qatyq_bucket",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .usingConvertsTo(Items.BUCKET)
                            .nutrition(FOODS_JSON.getNutrition("qatyq_bucket"))
                            .saturationModifier(FOODS_JSON.getSaturation("qatyq_bucket"))
                            .build())

            ) {
                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }

                @Override
                public SoundEvent getDrinkingSound() {
                    return SoundEvents.HONEY_DRINK;
                }
            }

    );

    public static final DeferredItem<Item> QURT = ITEMS.register("qurt",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("qurt"))
                            .saturationModifier(FOODS_JSON.getSaturation("qurt"))
                            .build())
            ));

    public static final DeferredItem<Item> AYRAN_BUCKET = ITEMS.register("ayran_bucket",
            () -> new Item(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("ayran_bucket"))
                            .saturationModifier(FOODS_JSON.getSaturation("ayran_bucket"))
                            .usingConvertsTo(Items.BUCKET)
                            .build()
                    )) {
                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }

                @Override
                public SoundEvent getDrinkingSound() {
                    return SoundEvents.HONEY_DRINK;
                }
            }
    );

    public static final DeferredItem<Item> MAIMYZHYK = ITEMS.register("maimyzhyk",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("maimyzhyk"))
                            .saturationModifier(FOODS_JSON.getSaturation("maimyzhyk"))
                            .build())
            ));

    public static final DeferredItem<Item> CURD = ITEMS.register("curd",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("curd"))
                            .saturationModifier(FOODS_JSON.getSaturation("curd"))
                            .build())
            ));

    public static final DeferredItem<Item> ZHARMA_BUCKET = ITEMS.register("zharma_bucket",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("zharma_bucket"))
                            .saturationModifier(FOODS_JSON.getSaturation("zharma_bucket"))
                            .build())
            ) {
                @Override
                public UseAnim getUseAnimation(ItemStack stack) {
                    return UseAnim.DRINK;
                }

                @Override
                public SoundEvent getDrinkingSound() {
                    return SoundEvents.HONEY_DRINK;
                }
            }
    );

    public static final DeferredItem<Item> ACHUCHUK_SALAD = ITEMS.register("achuchuk_salad",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("achuchuk_salad"))
                            .saturationModifier(FOODS_JSON.getSaturation("achuchuk_salad"))
                            .build())
            ));

    public static final DeferredItem<Item> MEAT_SALAD = ITEMS.register("meat_salad",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("meat_salad"))
                            .saturationModifier(FOODS_JSON.getSaturation("meat_salad"))
                            .build())
            ));

    public static final DeferredItem<Item> MORKOVCHA = ITEMS.register("morkovcha",
            () -> new Item(new Item.Properties()
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("morkovcha"))
                            .saturationModifier(FOODS_JSON.getSaturation("morkovcha"))
                            .build())
            ));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}