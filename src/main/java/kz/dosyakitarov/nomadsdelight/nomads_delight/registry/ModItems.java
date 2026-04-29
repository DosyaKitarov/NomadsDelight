package kz.dosyakitarov.nomadsdelight.nomads_delight.registry;

import kz.dosyakitarov.nomadsdelight.nomads_delight.Nomads_delight;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Nomads_delight.MODID);

    public static final DeferredItem<Item> RAW_HORSE_MEAT = ITEMS.register("raw_horse_meat",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(2)
                    .saturationModifier(0.8f)
                    .build())));

    public static final DeferredItem<Item> KYMYZ_BUCKET = ITEMS.register("kymyz_bucket",
            ()-> new Item(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                    .alwaysEdible()
                    .usingConvertsTo(Items.BUCKET)
                    .build()
            )){
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
            ()-> new Item(new Item.Properties()
                    .stacksTo(1)
                    .food(new FoodProperties.Builder()
                    .alwaysEdible()
                    .usingConvertsTo(Items.GLASS_BOTTLE)
                    .build()
            )){
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

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}