package io.github.mestretramador.mocreaturesrr.client;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.Entity;

/**
 * Client-only helpers called from common entity code (always behind a {@code level().isClientSide()} check).
 * Keeping the client classes behind these static calls stops a dedicated server from loading them when
 * it verifies the entity classes.
 */
public final class ClientHooks {

    private ClientHooks() {
    }

    /** Whether the local player exists and is within {@code distanceSqr} of the entity. */
    public static boolean isLocalPlayerWithin(Entity entity, double distanceSqr) {
        Entity player = Minecraft.getInstance().player;
        return player != null && entity.distanceToSqr(player) < distanceSqr;
    }

    public static boolean isLocalPlayer(Entity entity) {
        return entity != null && entity == Minecraft.getInstance().player;
    }

    public static boolean isJumpKeyDown() {
        return Minecraft.getInstance().options.keyJump.isDown();
    }

    public static boolean isDescendKeyDown() {
        return ModKeyMappings.DESCEND.isDown();
    }

    public static void playLocalSound(Entity entity, SoundEvent sound, float volume, float pitch) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level != null) {
            mc.level.playLocalSound(entity.getX(), entity.getY(), entity.getZ(), sound, entity.getSoundSource(),
                    volume, pitch, false);
        }
    }
}
