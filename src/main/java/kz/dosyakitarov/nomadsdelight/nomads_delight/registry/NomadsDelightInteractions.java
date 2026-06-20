package kz.dosyakitarov.nomadsdelight.nomads_delight.registry;

import kz.dosyakitarov.nomadsdelight.nomads_delight.Nomads_delight;
import kz.dosyakitarov.nomadsdelight.nomads_delight.data.NomadsDelightAdvancementProvider;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = "nomads_delight")
public class NomadsDelightInteractions {

    @SubscribeEvent
    public static void milkHorse(PlayerInteractEvent.EntityInteract event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getTarget() instanceof Horse horse) {
                Player player = event.getEntity();
                ItemStack itemInHand = player.getItemInHand(event.getHand());
                if (itemInHand.is(Items.BUCKET)) {
                    ItemStack bucketOfKymyz = new ItemStack(NomadsDelightItems.HORSE_MILK_BUCKET.get());

                    ItemStack filledResult = ItemUtils.createFilledResult(itemInHand, player, bucketOfKymyz);
                    player.setItemInHand(event.getHand(), filledResult);

                    player.level().playSound(null, horse.blockPosition(), SoundEvents.COW_MILK, SoundSource.NEUTRAL, 1.0F, 1.0F);

                    event.setCanceled(true);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
            }

        }
    }

    @SubscribeEvent
    public static void milkCamel(PlayerInteractEvent.EntityInteract event) {
        if (!event.getLevel().isClientSide()) {
            if (event.getTarget() instanceof Camel camel) {
                Player player = event.getEntity();
                ItemStack itemInHand = player.getItemInHand(event.getHand());
                if (itemInHand.is(Items.BUCKET)) {
                    ItemStack bucketOfKymyz = new ItemStack(NomadsDelightItems.CAMEL_MILK_BUCKET.get());

                    ItemStack filledResult = ItemUtils.createFilledResult(itemInHand, player, bucketOfKymyz);
                    player.setItemInHand(event.getHand(), filledResult);

                    player.level().playSound(null, camel.blockPosition(), SoundEvents.COW_MILK, SoundSource.NEUTRAL, 1.0F, 1.0F);

                    event.setCanceled(true);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
            }

        }
    }

    @SubscribeEvent
    public static void onBonk(LivingDeathEvent event) {
        if (event.getEntity().level().isClientSide()) {
            return;
        }

        if (event.getSource().getEntity() instanceof ServerPlayer player) {
            ItemStack weapon = player.getMainHandItem();
            if (weapon.is(NomadsDelightItems.ROLLING_PIN.get())) {
                var server = player.getServer();
                if (server == null) {
                    return;
                }

                AdvancementHolder bonk = server.getAdvancements()
                        .get(ResourceLocation.fromNamespaceAndPath(Nomads_delight.MODID, "bonk"));

                if (bonk != null) {
                    player.getAdvancements().award(bonk, NomadsDelightAdvancementProvider.BONK_CRITERION);
                }
            }
        }
    }
}
