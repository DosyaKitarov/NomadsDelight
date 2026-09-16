package kz.dosyakitarov.nomads_delight.registry;

import kz.dosyakitarov.nomads_delight.NomadsDelight;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.camel.Camel;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loader-independent gameplay handlers. Each loader module hooks these into its own
 * event system (NeoForge events, Fabric callbacks); the drunk-advancement check is
 * invoked directly from {@code NomadsDrinkItem} since Fabric has no finish-using-item event.
 */
public class NomadsDelightInteractions {

    private static final Logger log = LoggerFactory.getLogger(NomadsDelightInteractions.class);

    /**
     * Milking horses and camels with an empty bucket.
     *
     * @return the result to cancel the vanilla interaction with, or {@code null} if
     * this interaction is not ours and vanilla behavior should proceed.
     */
    @Nullable
    public static InteractionResult milkAnimal(Player player, Level level, InteractionHand hand, Entity target) {
        boolean isHorse = target instanceof Horse;
        boolean isCamel = target instanceof Camel;
        if (!isHorse && !isCamel) {
            return null;
        }

        ItemStack itemInHand = player.getItemInHand(hand);
        if (!itemInHand.is(Items.BUCKET)) {
            return null;
        }

        if (!level.isClientSide()) {
            ItemStack milkBucket = new ItemStack(isHorse
                    ? NomadsDelightItems.HORSE_MILK_BUCKET.get()
                    : NomadsDelightItems.CAMEL_MILK_BUCKET.get());

            ItemStack filledResult = ItemUtils.createFilledResult(itemInHand, player, milkBucket);
            player.setItemInHand(hand, filledResult);

            player.level().playSound(null, target.blockPosition(), SoundEvents.COW_MILK, SoundSource.NEUTRAL, 1.0F, 1.0F);
        }

        return InteractionResult.SUCCESS;
    }

    /** Awards the "bonk" advancement for kills with the rolling pin. */
    public static void onLivingDeath(LivingEntity entity, DamageSource source) {
        try {
            if (entity.level().isClientSide()) {
                return;
            }

            if (!(source.getEntity() instanceof ServerPlayer player)) {
                return;
            }

            ItemStack weapon = source.getWeaponItem();

            if (weapon == null || weapon.isEmpty() || !weapon.is(NomadsDelightItems.ROLLING_PIN.get())) {
                return;
            }

            awardAdvancement(player, NomadsDelightAdvancements.BONK_ID, NomadsDelightAdvancements.BONK_CRITERION);
        } catch (Exception e) {
            log.error("Error occurred while awarding advancement", e);
        }
    }

    /**
     * Awards "get_drunk" after drinking qumyz/shubat while nauseated.
     * Called from {@code NomadsDrinkItem#finishUsingItem} on the server side.
     */
    public static void checkDrunkAdvancement(LivingEntity entity, ItemStack item) {
        if (!(entity instanceof ServerPlayer player)) {
            return;
        }

        if (!item.is(NomadsDelightItems.QUMYZ_BUCKET.get())
                && !item.is(NomadsDelightItems.SHUBAT_BUCKET.get())) {
            return;
        }

        if (!player.hasEffect(MobEffects.CONFUSION)) {
            return;
        }

        awardAdvancement(player, NomadsDelightAdvancements.DRUNK_ID, NomadsDelightAdvancements.DRUNK_CRITERION);
    }

    private static void awardAdvancement(ServerPlayer player, String advancementId, String criterion) {
        MinecraftServer server = player.getServer();
        if (server == null) {
            return;
        }

        AdvancementHolder advancement = server.getAdvancements().get(
                ResourceLocation.fromNamespaceAndPath(NomadsDelight.MODID, advancementId)
        );

        if (advancement != null) {
            player.getAdvancements().award(advancement, criterion);
        }
    }
}
