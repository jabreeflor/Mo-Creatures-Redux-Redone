package io.github.mestretramador.mocreaturesrr.client;

import java.util.EnumMap;
import java.util.Map;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCBigGolemEntity;
import io.github.mestretramador.mocreaturesrr.entity.golem.GolemState;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;

/** Port of {@code MoCRenderGolem.LayerMoCGolem}: a scrolling energy glow whose colour shows the golem's state. */
public class MoCBigGolemEffectLayer extends RenderLayer<MoCRenderState, MoCBigGolemModel<MoCBigGolemEntity>> {

    /** Original: rendered at half brightness (0.5 on every colour channel). */
    private static final int EFFECT_COLOR = 0xFF808080;
    private static final float SCROLL_SPEED = 0.01F;
    /** Dying flickers between red and orange every few ticks — "red = about to explode", as on the wiki. */
    private static final int DYING_FLICKER_TICKS = 3;
    private static final Map<GolemState, Identifier> TEXTURES = new EnumMap<>(GolemState.class);

    static {
        for (GolemState state : GolemState.values()) {
            String name = state.getEffectTextureName();
            if (name != null) {
                TEXTURES.put(state, Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID,
                        "textures/entity/moc_golem/" + name + ".png"));
            }
        }
    }

    public MoCBigGolemEffectLayer(RenderLayerParent<MoCRenderState, MoCBigGolemModel<MoCBigGolemEntity>> parent) {
        super(parent);
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight, MoCRenderState renderState,
                       float yRot, float xRot) {
        MoCBigGolemEntity golem = renderState.entity();
        float partialTick = renderState.partialTick;
        GolemState state = golem.getGolemState();
        if (state == GolemState.DYING && (golem.tickCount / DYING_FLICKER_TICKS) % 2 == 0) {
            state = GolemState.ENRAGED;
        }
        Identifier texture = TEXTURES.get(state);
        if (texture == null) {
            return;
        }
        float scroll = ((golem.tickCount + partialTick) * SCROLL_SPEED) % 1.0F;
        RenderType glow = RenderTypes.energySwirl(texture, scroll, scroll);
        MoCBigGolemModel<MoCBigGolemEntity> model = this.getParentModel();
        // Head + chest core (main pass), then the body cubes with their tile UV shift, like renderToBuffer did.
        collector.submitModel(model, renderState, poseStack, glow, packedLight, OverlayTexture.NO_OVERLAY, EFFECT_COLOR,
                null, renderState.outlineColor);
        model.submitCubes(golem, renderState, poseStack, collector, glow, packedLight, OverlayTexture.NO_OVERLAY,
                EFFECT_COLOR);
    }
}