package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCSmallFishEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/**
 * Port of the small fish's renderer registration ({@code MoCRenderMoC} with
 * {@code MoCModelSmallFish}, shadow 0.1). Shared by all 8 variants; only the texture differs.
 */
public class MoCSmallFishRenderer extends MoCMobRenderer<MoCSmallFishEntity, MoCSmallFishModel> {

    public static final ModelLayerLocation MOC_SMALL_FISH_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_small_fish"), "main");

    private static final float SHADOW_RADIUS = 0.1F;

    public MoCSmallFishRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCSmallFishModel(context.bakeLayer(MOC_SMALL_FISH_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(MoCSmallFishEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_small_fish/" + entity.getVariant().getTextureName() + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}