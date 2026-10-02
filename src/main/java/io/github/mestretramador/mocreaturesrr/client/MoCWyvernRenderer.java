package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCWyvernEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;

public class MoCWyvernRenderer extends MoCMobRenderer<MoCWyvernEntity, MoCWyvernModel> {

    public static final ModelLayerLocation MOC_WYVERN_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "wyvern"), "main");

    public MoCWyvernRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCWyvernModel(context.bakeLayer(MOC_WYVERN_LAYER)), 0.9F);
    }

    /**
     * Visual size only — completely separate from the hitbox math in
     * MoCWyvernEntity#tickGrowth() (which is deliberately capped at 1.0 so
     * it never multiplies the EntityType's own already-tier-sized hitbox
     * again). getVisualScale() is the one that actually reaches 1.3/1.5/2.0
     * for a grown tier 2/mother/mother-tamed.
     */
    @Override
    protected void scale(MoCWyvernEntity entity, PoseStack poseStack, float partialTick) {
        float scale = entity.getVisualScale();
        poseStack.scale(scale, scale, scale);
    }

    @Override
    public Identifier getTextureLocation(MoCWyvernEntity entity) {
        var variant = entity.getVariant();
        if (entity.isTransforming()) {
            int ticksLeft = entity.getTransformTicks();
            int interval = Math.max(1, ticksLeft / 8);
            boolean showTarget = (entity.tickCount / interval) % 2 == 0;
            variant = showTarget ? entity.getTransformTarget() : variant;
        }
        String textureName = entity.isGhost() ? variant.getGhostTextureName() : variant.getTextureName();
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/wyvern/" + textureName + ".png");
    }

    @Override
    protected net.minecraft.client.renderer.rendertype.RenderType getRenderType(MoCWyvernEntity entity, boolean bodyVisible, boolean translucent, boolean showOutline) {
        return super.getRenderType(entity, bodyVisible, translucent || entity.isGhost(), showOutline);
    }

    /** Original: ghost wyverns were drawn at 0.35 alpha (through an alpha-replacing vertex consumer). */
    @Override
    protected int getModelTint(MoCRenderState state) {
        if (!(state.entity instanceof MoCWyvernEntity entity) || !entity.isGhost()) {
            return -1;
        }
        int alphaInt = (int) (0.35F * 255F);
        return ARGB.color(alphaInt, 0xFFFFFF);
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}