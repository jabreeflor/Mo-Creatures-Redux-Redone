package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCCrabEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/** Port of the crab's renderer registration ({@code MoCRenderMoC} with {@code MoCModelCrab}, shadow 0.3). */
public class MoCCrabRenderer extends MoCMobRenderer<MoCCrabEntity, MoCCrabModel> {

    public static final ModelLayerLocation MOC_CRAB_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_crab"), "main");

    private static final float SHADOW_RADIUS = 0.3F;

    public MoCCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCCrabModel(context.bakeLayer(MOC_CRAB_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCCrabEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_crab/" + entity.getVariant().getTextureName() + ".png");
    }

    @Override
    protected void scale(MoCCrabEntity entity, PoseStack poseStack, float partialTick) {
        float scale = entity.getCrabScale();
        poseStack.scale(scale, scale, scale);
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}