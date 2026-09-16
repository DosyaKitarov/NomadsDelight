package kz.dosyakitarov.nomads_delight.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import kz.dosyakitarov.nomads_delight.NomadsDelight;
import kz.dosyakitarov.nomads_delight.block.entity.ButterChurnBlockEntity;
import kz.dosyakitarov.nomads_delight.client.ClientServices;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;

import static kz.dosyakitarov.nomads_delight.block.entity.ButterChurnBlockEntity.DOWN_STROKE_TICKS;
import static kz.dosyakitarov.nomads_delight.block.entity.ButterChurnBlockEntity.IDLE_BOB_DEPTH;
import static kz.dosyakitarov.nomads_delight.block.entity.ButterChurnBlockEntity.IDLE_BOB_PERIOD_TICKS;
import static kz.dosyakitarov.nomads_delight.block.entity.ButterChurnBlockEntity.PLUNGE_TICKS;
import static kz.dosyakitarov.nomads_delight.block.entity.ButterChurnBlockEntity.STROKE_DEPTH;
import static kz.dosyakitarov.nomads_delight.block.entity.ButterChurnBlockEntity.UP_STROKE_TICKS;

/**
 * Draws the churn's plunger. The block entity sits in the lower (opaque) half, so the
 * model is shifted up one block and lit from the upper block's position.
 * The plunger geometry is {@code models/block/churn_plunger.json}, registered as a
 * standalone model by each loader's client entry point.
 */
public class ButterChurnRenderer implements BlockEntityRenderer<ButterChurnBlockEntity> {

    public static final ResourceLocation PLUNGER_MODEL =
            ResourceLocation.fromNamespaceAndPath(NomadsDelight.MODID, "block/churn_plunger");

    public ButterChurnRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(ButterChurnBlockEntity churn, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        Level level = churn.getLevel();
        if (level == null) {
            return;
        }
        BakedModel model = ClientServices.CLIENT.getExtraModel(PLUNGER_MODEL);
        int light = LevelRenderer.getLightColor(level, churn.getBlockPos().above());

        poseStack.pushPose();
        poseStack.translate(0.0D, 1.0D + plungerOffset(churn, level, partialTick), 0.0D);
        renderShaded(model, level, poseStack.last(), bufferSource.getBuffer(RenderType.cutout()), light, packedOverlay);
        poseStack.popPose();
    }

    /**
     * Draws the model's quads with the same per-face directional shade the chunk renderer
     * gives static block faces (top 1.0, north/south 0.8, east/west 0.6, bottom 0.5).
     * {@code ModelBlockRenderer#renderModel} skips that shade, which made the plunger
     * noticeably brighter than the rest of the churn.
     */
    private static void renderShaded(BakedModel model, Level level, PoseStack.Pose pose, VertexConsumer buffer,
                                     int light, int packedOverlay) {
        RandomSource random = RandomSource.create(42L);
        for (Direction direction : QUAD_SIDES) {
            for (BakedQuad quad : model.getQuads(null, direction, random)) {
                float shade = level.getShade(quad.getDirection(), quad.isShade());
                buffer.putBulkData(pose, quad, shade, shade, shade, 1.0F, light, packedOverlay);
            }
        }
    }

    /** The six culled sides plus the unculled (null) bucket, as the model baker groups quads. */
    private static final Direction[] QUAD_SIDES = {
            Direction.DOWN, Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST, null
    };

    /** Vertical offset of the plunger from rest, in blocks; negative is down. */
    private static float plungerOffset(ButterChurnBlockEntity churn, Level level, float partialTick) {
        int plungeTicks = churn.getPlungeTicks();
        if (plungeTicks > 0) {
            // plungeTicks counts down from PLUNGE_TICKS; elapsed grows from 0 to PLUNGE_TICKS.
            float elapsed = (PLUNGE_TICKS - plungeTicks) + partialTick;
            if (elapsed < DOWN_STROKE_TICKS) {
                // Ease-in: slow wind-up, fast impact (cubic, as ChestLidController shapes the lid).
                float t = elapsed / DOWN_STROKE_TICKS;
                return -STROKE_DEPTH * t * t * t;
            }
            // Ease-out on the return: fast off the bottom, settles softly at rest.
            float t = Mth.clamp((elapsed - DOWN_STROKE_TICKS) / UP_STROKE_TICKS, 0.0F, 1.0F);
            float remaining = 1.0F - t;
            return -STROKE_DEPTH * remaining * remaining * remaining;
        }
        if (churn.isChurning()) {
            float phase = (level.getGameTime() + partialTick) / IDLE_BOB_PERIOD_TICKS;
            return -IDLE_BOB_DEPTH * (0.5F - 0.5F * Mth.cos(phase * Mth.TWO_PI));
        }
        return 0.0F;
    }
}
