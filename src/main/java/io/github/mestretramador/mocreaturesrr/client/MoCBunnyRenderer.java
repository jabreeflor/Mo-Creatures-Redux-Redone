package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCBunnyEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.phys.Vec3;

public class MoCBunnyRenderer extends MoCMobRenderer<MoCBunnyEntity, MoCBunnyModel> {

    public static final ModelLayerLocation MOC_BUNNY_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_bunny"), "main");

    public MoCBunnyRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCBunnyModel(context.bakeLayer(MOC_BUNNY_LAYER)), 0.3F);
    }

    @Override
    public Identifier getTextureLocation(MoCBunnyEntity entity) {
        // Per the user's instruction: the original's "_detailed" texture
        // pair (a legacy-vs-new art style config toggle in the real mod) is
        // repurposed here as the adult/baby distinction instead.
        String suffix = entity.isBaby() ? "" : "_detailed";
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_bunny/" + entity.getVariant().getTextureName() + suffix + ".png");
    }

    @Override
    protected void scale(MoCBunnyEntity entity, PoseStack poseStack, float partialTick) {
        // Original's getAdjustedYOffset(): a small fixed visual nudge, unrelated to growth scale.
        poseStack.translate(0.0, 0.2, 0.0);

        // Original's rotBunny(): tilts the whole model to face the direction
        // it's currently falling/rising, while airborne and not being carried.
        if (!entity.onGround() && entity.getVehicle() == null) {
            Vec3 motion = entity.getDeltaMovement();
            float angle;
            if (motion.y > 0.5D) {
                angle = 35F;
            } else if (motion.y < -0.5D) {
                angle = -35F;
            } else {
                angle = (float) (motion.y * 70.0D);
            }
            poseStack.rotateDegrees(Axis.XP, angle);
        }
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}