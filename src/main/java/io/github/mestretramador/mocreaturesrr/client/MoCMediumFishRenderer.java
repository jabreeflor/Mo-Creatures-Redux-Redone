package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCMediumFishEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

/**
 * Shared renderer for cod, salmon and bass ({@code MoCRenderMoC} with {@code MoCModelMediumFish} in
 * the original, shadow 0.3), since only the texture differs between the three.
 */
public class MoCMediumFishRenderer<T extends MoCMediumFishEntity> extends MoCMobRenderer<T, MoCMediumFishModel<T>> {

    public static final ModelLayerLocation MOC_MEDIUM_FISH_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_medium_fish"), "main");

    private static final float SHADOW_RADIUS = 0.3F;

    public MoCMediumFishRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCMediumFishModel<>(context.bakeLayer(MOC_MEDIUM_FISH_LAYER)), SHADOW_RADIUS);
    }

    @Override
    public Identifier getTextureLocation(T entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_medium_fish/" + entity.getTextureName() + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}