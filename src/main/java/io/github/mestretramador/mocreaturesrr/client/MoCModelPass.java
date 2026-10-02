package io.github.mestretramador.mocreaturesrr.client;

import java.util.function.Function;

import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;

/**
 * An extra draw pass over a {@link MoCEntityModel}, sharing its parts.
 *
 * <p>26.x replays {@code setupAnim} right before each submitted model is drawn, so a pass re-runs the full
 * animation and then lets {@link MoCEntityModel#setupPass} keep only the parts of that pass. Submit it from
 * a renderer with {@code collector.submitModel(pass, state, poseStack, renderType, light, overlay, color,
 * null, state.outlineColor)}.
 */
public final class MoCModelPass extends Model<MoCRenderState> {

    private final MoCEntityModel<?> model;
    private final int pass;

    public MoCModelPass(MoCEntityModel<?> model, int pass) {
        this(model, pass, model.renderType());
    }

    public MoCModelPass(MoCEntityModel<?> model, int pass, Function<Identifier, RenderType> renderType) {
        super(model.root(), renderType);
        this.model = model;
        this.pass = pass;
    }

    @Override
    public void setupAnim(MoCRenderState state) {
        this.model.setupAnimForPass(state, this.pass);
    }
}
