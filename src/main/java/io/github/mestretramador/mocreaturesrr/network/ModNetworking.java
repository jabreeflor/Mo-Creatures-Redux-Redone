package io.github.mestretramador.mocreaturesrr.network;


import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.LivingEntity;
import org.jspecify.annotations.Nullable;

import java.util.UUID;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class ModNetworking {

    /** Owners are stored as EntityReference since 1.21.5; this unwraps the owner's UUID (or null). */
    private static @Nullable UUID ownerUUID(@Nullable EntityReference<LivingEntity> owner) {
        return owner == null ? null : owner.getUUID();
    }

    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
                OpenNamingScreenPayload.TYPE,
                OpenNamingScreenPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() ->
                        io.github.mestretramador.mocreaturesrr.client.ClientPayloadHandlers.openNamingScreen(payload.entityId())));

        registrar.playToServer(
                SetPetNamePayload.TYPE,
                SetPetNamePayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(() -> {
                    if (payload.name().isBlank() || payload.name().length() > 32) {
                        return;
                    }
                    Entity entity = context.player().level().getEntity(payload.entityId());
                    // Only the entity's own owner can (re)name it — checked
                    // generically via TamableAnimal/AbstractHorse, so this
                    // works for any future tameable creature too.
                    boolean isOwner = (entity instanceof net.minecraft.world.entity.TamableAnimal tamable
                                    && context.player().getUUID().equals(ownerUUID(tamable.getOwnerReference())))
                            || (entity instanceof net.minecraft.world.entity.animal.equine.AbstractHorse horse
                                    && context.player().getUUID().equals(ownerUUID(horse.getOwnerReference())));
                    // A tamed-but-ownerless mob (Scroll of Sale / Reset Owner) is
                    // adopted by whoever successfully renames it first.
                    boolean isAdoptableOwnerless = (entity instanceof net.minecraft.world.entity.TamableAnimal tamable2
                                    && tamable2.isTame() && ownerUUID(tamable2.getOwnerReference()) == null)
                            || (entity instanceof net.minecraft.world.entity.animal.equine.AbstractHorse horse2
                                    && horse2.isTamed() && ownerUUID(horse2.getOwnerReference()) == null);
                    if (entity != null && (isOwner || isAdoptableOwnerless)) {
                        if (isAdoptableOwnerless) {
                            if (entity instanceof net.minecraft.world.entity.TamableAnimal tamable3) {
                                tamable3.setOwnerReference(EntityReference.of(context.player().getUUID()));
                            } else if (entity instanceof net.minecraft.world.entity.animal.equine.AbstractHorse horse3) {
                                horse3.setOwner(context.player());
                            }
                        }
                        entity.setCustomName(Component.literal(payload.name()));
                        entity.setCustomNameVisible(true);
                    }
                }));

        registrar.playToClient(OpenPlayerInventoryPayload.TYPE, OpenPlayerInventoryPayload.STREAM_CODEC,
                (payload, context) -> context.enqueueWork(
                        io.github.mestretramador.mocreaturesrr.client.ClientPayloadHandlers::openPlayerInventory));
                
        registrar.playToServer(
            DescendInputPayload.TYPE,
            DescendInputPayload.STREAM_CODEC,
            (payload, context) -> context.enqueueWork(() -> {
                if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCHorseEntity horse) {
                    horse.setDescendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCWyvernEntity wyvern) {
                    wyvern.setDescendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCBigCatEntity bigCat) {
                    bigCat.setDescendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCManticoreEntity manticore) {
                    manticore.setDescendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCKomodoDragonEntity komodo) {
                    komodo.setDescendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCDolphinEntity dolphin) {
                    dolphin.setDescendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCMantaRayEntity ray) {
                    ray.setDescendHeld(payload.pressed());
                }
            }));

        registrar.playToServer(
            AscendInputPayload.TYPE,
            AscendInputPayload.STREAM_CODEC,
            (payload, context) -> context.enqueueWork(() -> {
                if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCHorseEntity horse) {
                    horse.setAscendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCWyvernEntity wyvern) {
                    wyvern.setAscendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCBigCatEntity bigCat) {
                    bigCat.setAscendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCManticoreEntity manticore) {
                    manticore.setAscendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCOstrichEntity ostrich) {
                    ostrich.setAscendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCKomodoDragonEntity komodo) {
                    komodo.setAscendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCDolphinEntity dolphin) {
                    dolphin.setAscendHeld(payload.pressed());
                } else if (context.player().getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCMantaRayEntity ray) {
                    ray.setAscendHeld(payload.pressed());
                }
            }));
    }
}