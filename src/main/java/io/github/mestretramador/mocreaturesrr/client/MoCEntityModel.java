package io.github.mestretramador.mocreaturesrr.client;

import java.util.function.Function;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

/**
 * Base class for the Mo'Creatures entity models, bridging the 1.21.1 entity-driven animation code to
 * the 26.x render-state models.
 *
 * <p>Subclasses implement {@link #setupAnim(Entity, float, float, float, float, float)} exactly like the
 * old {@code HierarchicalModel#setupAnim}. Before every call the pose is reset and every part is made
 * visible again, so a model only has to hide what it does not want drawn.
 *
 * <p>{@code renderToBuffer} is final in 26.x: anything a model used to do there must now happen in
 * {@code setupAnim} (visibility, {@link ModelPart#skipDraw}, part offsets / scales), or be drawn as an
 * extra pass by the renderer through {@link MoCModelPass}.
 */
public abstract class MoCEntityModel<T extends Entity> extends EntityModel<MoCRenderState> {

    /** Equivalent of the 1.21.1 {@code EntityModel#young} flag. */
    protected boolean young;

    /** Equivalent of the 1.21.1 {@code EntityModel#riding} flag. */
    protected boolean riding;

    /** Equivalent of the 1.21.1 {@code EntityModel#attackTime}. */
    protected float attackTime;

    /** Partial tick of the frame currently being animated. */
    protected float partialTick;

    protected MoCEntityModel(ModelPart root) {
        // HierarchicalModel used entityCutoutNoCull by default, which is entityCutout in 26.x.
        this(root, RenderTypes::entityCutout);
    }

    protected MoCEntityModel(ModelPart root, Function<Identifier, RenderType> renderType) {
        super(root, renderType);
    }

    @Override
    public final void setupAnim(MoCRenderState state) {
        this.setupAnimForPass(state, 0);
    }

    /** Animates the model for the given draw pass. Pass 0 is the main body draw. */
    @SuppressWarnings("unchecked")
    public final void setupAnimForPass(MoCRenderState state, int pass) {
        super.setupAnim(state);
        for (ModelPart part : this.allParts()) {
            part.visible = true;
            part.skipDraw = false;
        }

        T entity = (T) state.entity;
        this.partialTick = state.partialTick;
        this.young = state.isBaby;
        this.riding = entity != null && entity.isPassenger();
        this.attackTime = entity instanceof LivingEntity living ? living.getSwingAnimation(state.partialTick) : 0.0F;

        this.setupAnim(entity, state.limbSwing, state.limbSwingAmount, state.ageInTicks, state.yRot, state.xRot);
        this.setupPass(entity, state, pass);
    }

    public abstract void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks,
                                   float netHeadYaw, float headPitch);

    /**
     * Hook for models that are drawn in several passes (e.g. translucent wings). Called after
     * {@link #setupAnim(Entity, float, float, float, float, float)}; hide the parts that do not belong to
     * {@code pass} (for pass 0, hide the parts drawn by the extra passes).
     */
    protected void setupPass(T entity, MoCRenderState state, int pass) {
    }
}
