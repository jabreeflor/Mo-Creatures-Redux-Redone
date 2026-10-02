package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCScorpionEntity;
import io.github.mestretramador.mocreaturesrr.entity.scorpion.ScorpionVariant;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.level.CameraRenderState;


public class MoCScorpionRenderer extends MoCMobRenderer<MoCScorpionEntity, MoCScorpionModel> {

    public static final ModelLayerLocation MOC_SCORPION_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_scorpion"), "main");

    public MoCScorpionRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCScorpionModel(context.bakeLayer(MOC_SCORPION_LAYER)), 0.5F);
    }

    @Override
    public void submit(MoCRenderState state, PoseStack poseStack, SubmitNodeCollector collector,
                       CameraRenderState camera) {
        MoCScorpionEntity entity = state.entity();
        float partialTicks = state.partialTick;
        net.minecraft.world.entity.player.Player holder = entity.isHeld() ? entity.getHolder() : null;
        if (holder != null) {
            net.minecraft.world.phys.Vec3 scorpionPos = entity.getPosition(partialTicks);
            net.minecraft.world.phys.Vec3 look = holder.getViewVector(partialTicks);
            net.minecraft.world.phys.Vec3 handPos = holder.getEyePosition(partialTicks)
                    .add(look.scale(0.6D))
                    .add(0.0D, -0.35D, 0.0D);
            net.minecraft.world.phys.Vec3 delta = handPos.subtract(scorpionPos);

            poseStack.pushPose();
            poseStack.translate(delta.x, delta.y + 0.15D, delta.z);
            poseStack.rotateDegrees(Axis.XP, 90F);
            super.submit(state, poseStack, collector, camera);
            poseStack.popPose();
            return;
        }
        super.submit(state, poseStack, collector, camera);
    }

    @Override
    public Identifier getTextureLocation(MoCScorpionEntity entity) {
        if (entity.isTransforming()) {
            int ticksLeft = entity.getTransformTicks();
            int interval = Math.max(1, ticksLeft / 8);
            boolean showUndead = (entity.tickCount / interval) % 2 == 0;
            ScorpionVariant shown = showUndead ? ScorpionVariant.UNDEAD : entity.getVariant();
            return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                    "textures/entity/moc_scorpion/" + shown.getTextureName() + ".png");
        }
        // Saddle is texture-only, no separate 3D model — just swap to the "_saddled" file.
        String suffix = (entity.isSaddled() && !entity.isBaby()) ? "_saddled" : "";
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_scorpion/" + entity.getVariant().getTextureName() + suffix + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}