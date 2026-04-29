package kz.dosyakitarov.nomadsdelight.nomads_delight.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = "nomads_delight")
public class ModInteractions {

    @SubscribeEvent
    public static void milkHorse(PlayerInteractEvent.EntityInteract event) {
        if (!event.getLevel().isClientSide()) {
            if(event.getTarget() instanceof Horse horse){
                Player player = event.getEntity();
                ItemStack itemInHand = player.getItemInHand(event.getHand());
                if (itemInHand.is(Items.GLASS_BOTTLE)){
                    ItemStack bottleOfKymyz = new ItemStack(ModItems.KYMYZ_BOTTLE.get());

                    ItemStack filledResult = ItemUtils.createFilledResult(itemInHand, player, bottleOfKymyz);
                    player.setItemInHand(event.getHand(),filledResult);

                    player.level().playSound(null, horse.blockPosition(), SoundEvents.BOTTLE_FILL, SoundSource.NEUTRAL, 1.0F, 1.0F);

                    event.setCanceled(true);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
                if (itemInHand.is(Items.BUCKET)){
                    ItemStack bucketOfKymyz = new ItemStack(ModItems.KYMYZ_BUCKET.get());

                    ItemStack filledResult = ItemUtils.createFilledResult(itemInHand, player, bucketOfKymyz);
                    player.setItemInHand(event.getHand(),filledResult);

                    player.level().playSound(null, horse.blockPosition(), SoundEvents.COW_MILK, SoundSource.NEUTRAL, 1.0F, 1.0F);

                    event.setCanceled(true);
                    event.setCancellationResult(InteractionResult.SUCCESS);
                }
            }

        }
    }
}
