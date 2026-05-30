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

import java.io.IOException;
import java.nio.file.Path;

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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}