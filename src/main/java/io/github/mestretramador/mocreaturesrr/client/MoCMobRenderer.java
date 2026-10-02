package io.github.mestretramador.mocreaturesrr.client;

import org.jspecify.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Mob;

/**
 * Base renderer for every Mo'Creatures mob, bridging the 1.21.1 entity-based renderer hooks to the 26.x
 * render-state renderers.
 *
 * <p>Subclasses keep overriding the entity-based hooks they had in 1.21.1:
 * {@link #getTextureLocation(Mob)}, {@link #scale(Mob, PoseStack, float)},
 * {@link #setupRotations(Mob, PoseStack, float, float, float, float)} and
 * {@link #getRenderType(Mob, boolean, boolean, boolean)}. The entity is stored in the
 * {@link MoCRenderState} so models and layers can keep reading it.
 */
public abstract class MoCMobRenderer<T extends Mob, M extends MoCEntityModel<? super T>>
        extends MobRenderer<T, MoCRenderState, M> {

    /** State currently being submitted; lets the entity hooks fall back to the vanilla behaviour. */
    private MoCRenderState activeState;

    protected MoCMobRenderer(EntityRendererProvider.Context context, M model, float shadowRadius) {
        super(context, model, shadowRadius);
    }

    @Override
    public MoCRenderState createRenderState() {
        return new MoCRenderState();
    }

    @Override
    public void extractRenderState(T entity, MoCRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.entity = entity;
        state.limbSwing = state.isBaby ? state.walkAnimationPos * 3.0F : state.walkAnimationPos;
        state.limbSwingAmount = Math.min(state.walkAnimationSpeed, 1.0F);
    }

    // ---- texture ----

    @Override
    @SuppressWarnings("unchecked")
    public final Identifier getTextureLocation(MoCRenderState state) {
        return this.getTextureLocation((T) state.entity);
    }

    public abstract Identifier getTextureLocation(T entity);

    // ---- scale ----

    @Override
    @SuppressWarnings("unchecked")
    protected final void scale(MoCRenderState state, PoseStack poseStack) {
        this.scale((T) state.entity, poseStack, state.partialTick);
    }

    protected void scale(T entity, PoseStack poseStack, float partialTick) {
    }

    // ---- rotations ----

    @Override
    @SuppressWarnings("unchecked")
    protected final void setupRotations(MoCRenderState state, PoseStack poseStack, float bodyRot, float entityScale) {
        this.activeState = state;
        try {
            this.setupRotations((T) state.entity, poseStack, state.ageInTicks, bodyRot, state.partialTick, entityScale);
        } finally {
            this.activeState = null;
        }
    }

    /** 1.21.1 signature; the default applies the vanilla rotations. */
    protected void setupRotations(T entity, PoseStack poseStack, float ageInTicks, float bodyRot, float partialTick,
                                  float entityScale) {
        super.setupRotations(this.activeState, poseStack, bodyRot, entityScale);
    }

    // ---- render type ----

    @Override
    @SuppressWarnings("unchecked")
    protected final @Nullable RenderType getRenderType(MoCRenderState state, boolean bodyVisible, boolean translucent,
                                                       boolean glowing) {
        return this.getRenderType((T) state.entity, bodyVisible, translucent, glowing);
    }

    /** 1.21.1 signature; the default mirrors the vanilla behaviour. */
    protected @Nullable RenderType getRenderType(T entity, boolean bodyVisible, boolean translucent, boolean glowing) {
        Identifier texture = this.getTextureLocation(entity);
        if (translucent) {
            return RenderTypes.entityTranslucentCull(texture);
        } else if (bodyVisible) {
            return this.model.renderType(texture);
        } else {
            return glowing ? RenderTypes.outline(texture) : null;
        }
    }

    // ---- name tag + tamed health bar ----

    /** Whether to draw the tamed-pet health bar above the name tag (and hide both beyond 8 blocks). */
    protected boolean showsHealthBar() {
        return false;
    }

    @Override
    protected void submitNameDisplay(MoCRenderState state, PoseStack poseStack, SubmitNodeCollector collector,
                                     CameraRenderState camera) {
        if (this.showsHealthBar()) {
            if (state.distanceToCameraSq > TameableOverlayRenderer.NAME_AND_HEALTH_SHOW_DISTANCE_SQR) {
                return;
            }
            TameableOverlayRenderer.submitHealthBar(state, poseStack, collector, camera);
        }
        super.submitNameDisplay(state, poseStack, collector, camera);
    }
}
