package kz.dosyakitarov.nomads_delight.registry;

import kz.dosyakitarov.nomads_delight.integration.farmersdelight.FarmersDelightEffects;
import kz.dosyakitarov.nomads_delight.item.EffectFoodItem;
import kz.dosyakitarov.nomads_delight.item.EffectFoodItem.EffectTooltip;
import kz.dosyakitarov.nomads_delight.item.EffectFoodItem.TimedEffect;
import kz.dosyakitarov.nomads_delight.item.NomadsDrinkItem;
import kz.dosyakitarov.nomads_delight.platform.Services;
import kz.dosyakitarov.nomads_delight.util.JsonReader;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;

import java.util.List;
import java.util.function.Supplier;

public class NomadsDelightItems {

    private static final JsonReader FOODS_JSON =
            new JsonReader("data/nomads_delight/food_properties/food_properties.json");

    private static final JsonReader ITEMS_JSON =
            new JsonReader("data/nomads_delight/item_properties/item_properties.json");

    // Effect suppliers: FD effects are looked up from the registry at gameplay time,
    // vanilla effects are already Holders.
    private static final Supplier<Holder<MobEffect>> COMFORT = FarmersDelightEffects::comfort;
    private static final Supplier<Holder<MobEffect>> NOURISHMENT = FarmersDelightEffects::nourishment;
    private static final Supplier<Holder<MobEffect>> NAUSEA = () -> MobEffects.CONFUSION;
    private static final Supplier<Holder<MobEffect>> REGENERATION = () -> MobEffects.REGENERATION;

    public static final Supplier<Item> BUTTER = register("butter",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(FOODS_JSON.getNutrition("butter"))
                    .saturationModifier(FOODS_JSON.getSaturation("butter"))
                    .build())));

    public static final Supplier<Item> RAW_HORSE_MEAT = register("raw_horse_meat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(FOODS_JSON.getNutrition("raw_horse_meat"))
                    .saturationModifier(FOODS_JSON.getSaturation("raw_horse_meat"))
                    .build())));

    public static final Supplier<Item> COOKED_HORSE_MEAT = register("cooked_horse_meat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(FOODS_JSON.getNutrition("cooked_horse_meat"))
                    .saturationModifier(FOODS_JSON.getSaturation("cooked_horse_meat"))
                    .build())));

    public static final Supplier<Item> HORSE_MILK_BUCKET = register("horse_milk_bucket",
            () -> new NomadsDrinkItem(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .usingConvertsTo(Items.BUCKET)
                            .build()),
                    SoundEvents.GENERIC_DRINK));

    public static final Supplier<Item> QUMYZ_BUCKET = register("qumyz_bucket",
            () -> new NomadsDrinkItem(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .usingConvertsTo(Items.BUCKET)
                            .build()),
                    SoundEvents.GENERIC_DRINK,
                    List.of(TimedEffect.of(COMFORT, 3600), new TimedEffect(NAUSEA, 600, 0.25F)),
                    List.of(new EffectTooltip(COMFORT, 180))));

    public static final Supplier<Item> CAMEL_MILK_BUCKET = register("camel_milk_bucket",
            () -> new NomadsDrinkItem(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .usingConvertsTo(Items.BUCKET)
                            .build()),
                    SoundEvents.GENERIC_DRINK));

    public static final Supplier<Item> SHUBAT_BUCKET = register("shubat_bucket",
            () -> new NomadsDrinkItem(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .usingConvertsTo(Items.BUCKET)
                            .build()),
                    SoundEvents.GENERIC_DRINK,
                    List.of(TimedEffect.of(COMFORT, 3600), new TimedEffect(NAUSEA, 900, 0.20F)),
                    List.of(new EffectTooltip(COMFORT, 180))));

    public static final Supplier<SwordItem> ROLLING_PIN = register("rolling_pin",
            () -> new SwordItem(
                    Tiers.WOOD,
                    new Item.Properties()
                            .durability(ITEMS_JSON.getInt("rolling_pin", "durability"))
                            .attributes(
                                    // Vanilla only has the (Tier, int, float) overload; the float
                                    // variant the original used is a NeoForge patch.
                                    SwordItem.createAttributes(
                                            Tiers.WOOD,
                                            ITEMS_JSON.getInt("rolling_pin", "damage"),
                                            ITEMS_JSON.getFloat("rolling_pin", "attack_speed")
                                    )
                            )
            )
    );

    public static final Supplier<Item> ROLLED_DOUGH = register("rolled_dough",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> ZHAYMA = register("zhayma",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> ROASTED_MILLET = register("roasted_millet",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> HORSE_INTESTINES = register("horse_intestines",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> TALKAN = register("talkan",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> READY_MADE_TALKAN = register("ready_made_talkan",
            () -> new EffectFoodItem(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("ready_made_talkan", "stacksTo"))
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("ready_made_talkan"))
                            .saturationModifier(FOODS_JSON.getSaturation("ready_made_talkan"))
                            .usingConvertsTo(Items.BOWL)
                            .build()),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> BESHBARMAK = register("beshbarmak",
            () -> foodWithEffect("beshbarmak", NOURISHMENT, 6000));

    public static final Supplier<Item> PILAF = register("pilaf",
            () -> foodWithEffect("pilaf", NOURISHMENT, 3600));

    public static final Supplier<Item> KUURDAK = register("kuurdak",
            () -> foodWithEffect("kuurdak", NOURISHMENT, 6000));

    public static final Supplier<Item> MANTI = register("manti",
            () -> foodWithEffect("manti", NOURISHMENT, 3600));

    public static final Supplier<Item> KHANUM = register("khanum",
            () -> foodWithEffect("khanum", NOURISHMENT, 3600));

    public static final Supplier<Item> DIMLAMA = register("dimlama",
            () -> foodWithEffect("dimlama", NOURISHMENT, 6000));

    public static final Supplier<Item> LAGHMAN = register("laghman",
            () -> foodWithEffect("laghman", NOURISHMENT, 3600));

    public static final Supplier<Item> KAZAN_KEBAB = register("kazan_kebab",
            () -> foodWithEffect("kazan_kebab", NOURISHMENT, 3600));

    public static final Supplier<Item> ASIP = register("asip",
            () -> foodWithEffect("asip", NOURISHMENT, 3600));

    public static final Supplier<Item> KAZY = register("kazy",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("kazy")),
                    List.of(TimedEffect.of(NOURISHMENT, 1200)),
                    List.of(new EffectTooltip(NOURISHMENT, 60))));

    public static final Supplier<Item> QARTA = register("qarta",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("qarta")),
                    List.of(TimedEffect.of(NOURISHMENT, 3600)),
                    List.of(new EffectTooltip(NOURISHMENT, 180))));

    public static final Supplier<Item> SORPA = register("sorpa",
            () -> foodWithEffect("sorpa", COMFORT, 3600));

    public static final Supplier<Item> KESPE_KOZHE = register("kespe_kozhe",
            () -> foodWithEffect("kespe_kozhe", COMFORT, 3600));

    public static final Supplier<Item> RAW_SAMSA = register("raw_samsa",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> SAMSA = register("samsa",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("samsa")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> RAW_CHICKEN_SAMSA = register("raw_chicken_samsa",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> CHICKEN_SAMSA = register("chicken_samsa",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("chicken_samsa")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> RAW_PUMPKIN_SAMSA = register("raw_pumpkin_samsa",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> PUMPKIN_SAMSA = register("pumpkin_samsa",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("pumpkin_samsa")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> RAW_PEREMECH = register("raw_peremech",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> PEREMECH = register("peremech",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("peremech")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> RAW_TANDOOR_BREAD = register("raw_tandoor_bread",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> TANDOOR_BREAD = register("tandoor_bread",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("tandoor_bread")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> RAW_KATTAMA = register("raw_kattama",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> KATTAMA = register("kattama",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("kattama")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> RAW_FLATBREAD = register("raw_flatbread",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> FLATBREAD = register("flatbread",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("flatbread")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> RAW_BAURSAKS = register("raw_baursaks",
            () -> new Item(new Item.Properties()));

    public static final Supplier<Item> BAURSAKS = register("baursaks",
            () -> new EffectFoodItem(new Item.Properties()
                    .stacksTo(FOODS_JSON.getInt("baursaks", "stacksTo"))
                    .food(foodOf("baursaks")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> HALVA = register("halva",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("halva")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> MAYSOK = register("maysok",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("maysok")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> ZHENT = register("zhent",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("zhent")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> QATYQ_BUCKET = register("qatyq_bucket",
            () -> new NomadsDrinkItem(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .usingConvertsTo(Items.BUCKET)
                            .nutrition(FOODS_JSON.getNutrition("qatyq_bucket"))
                            .saturationModifier(FOODS_JSON.getSaturation("qatyq_bucket"))
                            .build()),
                    SoundEvents.HONEY_DRINK));

    // Note: applies Nourishment 0:30 but advertises Comfort 1:00 - preserved verbatim
    // from the original mod; see the migration notes.
    public static final Supplier<Item> QURT = register("qurt",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("qurt")),
                    List.of(TimedEffect.of(NOURISHMENT, 600)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> AYRAN_BUCKET = register("ayran_bucket",
            () -> new NomadsDrinkItem(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .alwaysEdible()
                            .nutrition(FOODS_JSON.getNutrition("ayran_bucket"))
                            .saturationModifier(FOODS_JSON.getSaturation("ayran_bucket"))
                            .usingConvertsTo(Items.BUCKET)
                            .build()),
                    SoundEvents.GENERIC_DRINK,
                    List.of(TimedEffect.of(COMFORT, 2400)),
                    List.of(new EffectTooltip(COMFORT, 120))));

    public static final Supplier<Item> CURD = register("curd",
            () -> new EffectFoodItem(new Item.Properties()
                    .food(foodOf("curd")),
                    List.of(TimedEffect.of(COMFORT, 1200)),
                    List.of(new EffectTooltip(COMFORT, 60))));

    public static final Supplier<Item> ZHARMA_BUCKET = register("zharma_bucket",
            () -> new NomadsDrinkItem(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                            .nutrition(FOODS_JSON.getNutrition("zharma_bucket"))
                            .saturationModifier(FOODS_JSON.getSaturation("zharma_bucket"))
                            .usingConvertsTo(Items.BUCKET)
                            .build()),
                    SoundEvents.GENERIC_DRINK,
                    List.of(TimedEffect.of(NOURISHMENT, 1200)),
                    List.of(new EffectTooltip(NOURISHMENT, 60))));

    public static final Supplier<Item> ACHUCHUK_SALAD = register("achuchuk_salad",
            () -> salad("achuchuk_salad"));

    public static final Supplier<Item> MEAT_SALAD = register("meat_salad",
            () -> salad("meat_salad"));

    public static final Supplier<Item> MORKOVCHA_SALAD = register("morkovcha_salad",
            () -> salad("morkovcha_salad"));

    private static <T extends Item> Supplier<T> register(String name, Supplier<T> factory) {
        return Services.REGISTRAR.registerItem(name, factory);
    }

    private static FoodProperties foodOf(String name) {
        return new FoodProperties.Builder()
                .nutrition(FOODS_JSON.getNutrition(name))
                .saturationModifier(FOODS_JSON.getSaturation(name))
                .build();
    }

    /** Stackable meal that grants a single effect; tooltip mirrors the effect at ticks/20 seconds. */
    private static EffectFoodItem foodWithEffect(String name, Supplier<Holder<MobEffect>> effect, int durationTicks) {
        return new EffectFoodItem(new Item.Properties()
                .stacksTo(FOODS_JSON.getInt(name, "stacksTo"))
                .food(foodOf(name)),
                List.of(TimedEffect.of(effect, durationTicks)),
                List.of(new EffectTooltip(effect, durationTicks / 20)));
    }

    /** Bowl food granting Comfort 1:00 and Regeneration 0:05. */
    private static EffectFoodItem salad(String name) {
        return new EffectFoodItem(new Item.Properties()
                .stacksTo(16)
                .food(new FoodProperties.Builder()
                        .nutrition(FOODS_JSON.getNutrition(name))
                        .saturationModifier(FOODS_JSON.getSaturation(name))
                        .usingConvertsTo(Items.BOWL)
                        .build()),
                List.of(TimedEffect.of(COMFORT, 1200), TimedEffect.of(REGENERATION, 100)),
                List.of(new EffectTooltip(COMFORT, 60), new EffectTooltip(REGENERATION, 5)));
    }

    /** Triggers static registration; called once from {@code NomadsDelight.init()}. */
    public static void init() {
    }
}
