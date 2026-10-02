package io.github.mestretramador.mocreaturesrr.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.Entity;

/**
 * Shared render state for every Mo'Creatures mob.
 *
 * <p>Since 1.21.2 vanilla models animate from an extracted render state instead of the entity. The
 * Mo'Creatures models read a lot of per-entity data, so instead of mirroring every field we keep a
 * reference to the entity being drawn. Models and layers run on the render thread during the same
 * frame the state was extracted in, so reading from the entity there is safe.
 */
public class MoCRenderState extends LivingEntityRenderState {

    /** The entity this state was extracted from. */
    public Entity entity;

    /** Legacy limb swing (walk animation position, tripled for babies like 1.21.1 did). */
    public float limbSwing;

    /** Legacy limb swing amount (walk animation speed, clamped to 1 like 1.21.1 did). */
    public float limbSwingAmount;

    @SuppressWarnings("unchecked")
    public <T extends Entity> T entity() {
        return (T) this.entity;
    }
}
