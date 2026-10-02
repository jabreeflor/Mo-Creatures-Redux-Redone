package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCWerewolfEntity;
import io.github.mestretramador.mocreaturesrr.entity.werewolf.WerewolfVariant;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

/** Port of {@code MoCRenderWerewolf}: the wolf model always drives the base render pass, with a
 *  layer that draws vanilla's plain humanoid mesh — and only that — while in human form. */
public class MoCWerewolfRenderer extends MoCMobRenderer<MoCWerewolfEntity, MoCWerewolfModel<MoCWerewolfEntity>> {

    public static final ModelLayerLocation MOC_WEREWOLF_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_werewolf"), "main");
    public static final ModelLayerLocation MOC_WEREHUMAN_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_werehuman"), "main");

    private static final float SHADOW_RADIUS = 0.7F;

    public MoCWerewolfRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCWerewolfModel<>(context.bakeLayer(MOC_WEREWOLF_LAYER)), SHADOW_RADIUS);
        this.addLayer(new WerehumanLayer(this, new HumanoidModel<>(context.bakeLayer(MOC_WEREHUMAN_LAYER))));
    }

    /** Fully hides the wolf mesh in human form. 1.21.1 drew it through an alpha-0 vertex consumer
     *  because the parts' {@code visible} flags were not reliable there; skipping the body draw
     *  altogether is the 26.x equivalent (the human layer is still drawn). */
    @Override
    protected RenderType getRenderType(MoCWerewolfEntity entity, boolean bodyVisible, boolean translucent,
                                       boolean glowing) {
        if (entity.isHumanForm()) {
            return null;
        }
        return super.getRenderType(entity, bodyVisible, translucent, glowing);
    }

    @Override
    public MoCRenderState createRenderState() {
        return new WerewolfRenderState();
    }

    @Override
    public void extractRenderState(MoCWerewolfEntity entity, MoCRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        if (state instanceof WerewolfRenderState werewolfState && entity.isHumanForm()) {
            // Was humanModel.setupAnim(entity, ...) + copyPropertiesTo in 1.21.1: the human model now
            // animates from its own humanoid render state, filled from the same entity.
            HumanoidRenderState human = werewolfState.human;
            HumanoidMobRenderer.extractHumanoidRenderState(entity, human, partialTicks, this.itemModelResolver);
            human.ageInTicks = state.ageInTicks;
            human.walkAnimationPos = state.limbSwing;
            human.walkAnimationSpeed = state.limbSwingAmount;
            human.yRot = state.yRot;
            human.xRot = state.xRot;
            human.isBaby = state.isBaby;
            human.ageScale = state.ageScale;
            human.lightCoords = state.lightCoords;
            human.outlineColor = state.outlineColor;
        }
    }

    /** Render state carrying the humanoid state the were-human layer animates from. */
    private static final class WerewolfRenderState extends MoCRenderState {
        final HumanoidRenderState human = new HumanoidRenderState();
    }

    @Override
    public Identifier getTextureLocation(MoCWerewolfEntity entity) {
        return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                "textures/entity/moc_werewolf/" + wolfTextureFor(entity) + ".png");
    }

    /** Fire cycles through 3 frames continuously (1-2-3-1-...), same cadence as the Nightmare horse. */
    private static String wolfTextureFor(MoCWerewolfEntity entity) {
        if (entity.getVariant() == WerewolfVariant.FIRE) {
            int frame = 1 + (entity.tickCount / 5) % 3;
            return "werewolf_fire" + frame;
        }
        return entity.getVariant().getTextureName();
    }

    /** Port of the original's inner LayerMoCWereHuman: draws the plain human mesh, only visible in
     *  human form, textured by whichever wolf colour this individual last rolled — matching the
     *  original's own switch exactly, missing case included (WHITE falls through to "oldie", same
     *  as any other unlisted case, because the original's switch never had a case 3). */
    private static final class WerehumanLayer extends RenderLayer<MoCRenderState, MoCWerewolfModel<MoCWerewolfEntity>> {

        private final HumanoidModel<HumanoidRenderState> humanModel;

        WerehumanLayer(MoCWerewolfRenderer renderer, HumanoidModel<HumanoidRenderState> humanModel) {
            super(renderer);
            this.humanModel = humanModel;
        }

        @Override
        public void submit(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords, MoCRenderState state,
                           float yRot, float xRot) {
            if (!(state.entity instanceof MoCWerewolfEntity entity) || !entity.isHumanForm()
                    || !(state instanceof WerewolfRenderState werewolfState)) {
                return;
            }

            Identifier texture = Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                    "textures/entity/moc_werewolf/" + humanTextureFor(entity.getVariant()) + ".png");
            collector.submitModel(this.humanModel, werewolfState.human, poseStack, RenderTypes.entityCutout(texture),
                    lightCoords, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor);
        }

        private static String humanTextureFor(WerewolfVariant variant) {
            return switch (variant) {
                case BLACK -> "werehuman_dude";
                case BROWN -> "werehuman_classic";
                case FIRE -> "werehuman_woman";
                default -> "werehuman_oldie"; // WHITE, and anything else
            };
        }
    }
}