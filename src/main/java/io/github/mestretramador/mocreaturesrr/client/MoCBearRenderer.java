package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCBearEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

public class MoCBearRenderer extends MoCMobRenderer<MoCBearEntity, MoCBearModel> {

    public static final ModelLayerLocation MOC_BEAR_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_bear"), "main");

    public MoCBearRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCBearModel(context.bakeLayer(MOC_BEAR_LAYER)), 0.7F);
    }

    @Override
    public Identifier getTextureLocation(MoCBearEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_bear/" + entity.getVariant().getTextureName() + ".png");
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }
}