package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCAntEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Ant's renderer registration (the generic renderer with shadow size 0). */
public class MoCAntRenderer extends MoCMobRenderer<MoCAntEntity, MoCAntModel<MoCAntEntity>> {

    public static final ModelLayerLocation LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_ant"), "main");

    public MoCAntRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCAntModel<>(context.bakeLayer(LAYER)), 0.0F);
    }

    @Override
    public Identifier getTextureLocation(MoCAntEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_ant/" + "ant" + ".png");
    }
}
