package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCMiniGolemEntity;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.Identifier;

/** Port of the Mini Golem's renderer registration (shadow 0.5, single texture). */
public class MoCMiniGolemRenderer extends MoCMobRenderer<MoCMiniGolemEntity, MoCMiniGolemModel<MoCMiniGolemEntity>> {

    public static final ModelLayerLocation MOC_MINI_GOLEM_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_mini_golem"), "main");

    private static final float SHADOW_RADIUS = 0.5F;
    private static final Identifier TEXTURE =
            Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_golem/mini_golem.png");

    private final BlockModelResolver blockModelResolver;

    public MoCMiniGolemRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCMiniGolemModel<>(context.bakeLayer(MOC_MINI_GOLEM_LAYER)), SHADOW_RADIUS);
        this.blockModelResolver = context.getBlockModelResolver();
        this.addLayer(new MoCMiniGolemHeldRockLayer(this));
    }

    @Override
    public Identifier getTextureLocation(MoCMiniGolemEntity entity) {
        return TEXTURE;
    }

    @Override
    public MoCRenderState createRenderState() {
        return new MoCMiniGolemHeldRockLayer.HeldRockRenderState();
    }

    @Override
    public void extractRenderState(MoCMiniGolemEntity golem, MoCRenderState state, float partialTicks) {
        super.extractRenderState(golem, state, partialTicks);
        if (state instanceof MoCMiniGolemHeldRockLayer.HeldRockRenderState rockState) {
            MoCMiniGolemHeldRockLayer.extractHeldRock(golem, rockState, this.blockModelResolver);
        }
    }
}
