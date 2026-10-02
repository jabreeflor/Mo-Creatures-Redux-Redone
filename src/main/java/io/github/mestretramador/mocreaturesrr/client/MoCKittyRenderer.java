package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCKittyEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

public class MoCKittyRenderer extends MoCMobRenderer<MoCKittyEntity, MoCKittyModel> {

    public static final ModelLayerLocation MOC_KITTY_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_kitty"), "main");

    public MoCKittyRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCKittyModel(context.bakeLayer(MOC_KITTY_LAYER)), 0.3F);
    }

    @Override
    public Identifier getTextureLocation(MoCKittyEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_kitty/" + entity.getVariant().getTextureName() + ".png");
    }

    /**
     * The bed's food bowl sits on its side, not its front (and the litter box is longer along that axis).
     * A passenger's body is drawn with its vehicle's rotation, so the quarter turn that lines the kitty up
     * with the bowl has to happen here, not on the entity.
     */
    private static final float VEHICLE_FACING_OFFSET = 90.0F;

    @Override
    protected void setupRotations(MoCKittyEntity kitty, PoseStack poseStack, float bob, float yBodyRot,
                                  float partialTick, float scale) {
        if (kitty.getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCKittyBedEntity
                || kitty.getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCLitterBoxEntity) {
            yBodyRot += VEHICLE_FACING_OFFSET;
        }
        super.setupRotations(kitty, poseStack, bob, yBodyRot, partialTick, scale);
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }

    @Override
    protected void submitNameDisplay(MoCRenderState state, PoseStack poseStack, SubmitNodeCollector collector,
                                     CameraRenderState camera) {
        // 1.21.1 drew the emoticon from renderNameTag, i.e. only while the name tag was shown.
        if (state.nameTag != null) {
            MoCKittyEmoticonRenderer.submit(state, poseStack, collector, camera);
        }
        super.submitNameDisplay(state, poseStack, collector, camera);
    }

    @Override
    public void submit(MoCRenderState state, PoseStack poseStack, SubmitNodeCollector collector,
                       CameraRenderState camera) {
        MoCKittyEntity entity = state.entity();
        float partialTick = state.partialTick;
        net.minecraft.world.entity.player.Player holder = entity.getHolder();
        if (entity.isHeld() && holder != null) {
            net.minecraft.world.phys.Vec3 holderEye = holder.getEyePosition(partialTick);
            net.minecraft.world.phys.Vec3 targetPos = entity.isBaby()
                    ? holderEye.add(0.0D, 0.2D, 0.0D)
                    : holderEye.add(0.0D, 0.2D, 0.0D);
            net.minecraft.world.phys.Vec3 renderedPos = entity.getPosition(partialTick);

            poseStack.pushPose();
            poseStack.translate(targetPos.x - renderedPos.x, targetPos.y - renderedPos.y, targetPos.z - renderedPos.z);
            super.submit(state, poseStack, collector, camera);
            poseStack.popPose();
            return;
        }
        super.submit(state, poseStack, collector, camera);
    }
}
