package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species;
import io.github.mestretramador.mocreaturesrr.entity.MoCHorseEntity;
import io.github.mestretramador.mocreaturesrr.entity.MoCManticoreEntity;
import io.github.mestretramador.mocreaturesrr.entity.manticore.ManticoreVariant;
import io.github.mestretramador.mocreaturesrr.entity.MoCScorpionEntity;
import io.github.mestretramador.mocreaturesrr.entity.scorpion.ScorpionVariant;

import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ExtractBlockScreenEffectEvent;

public class NightmareOverlayHandler {

    @SubscribeEvent
    public static void onRenderBlockOverlay(ExtractBlockScreenEffectEvent event) {
        if (event.getOverlayType() != ExtractBlockScreenEffectEvent.OverlayType.FIRE) {
            return;
        }
        var player = Minecraft.getInstance().player;
        if (player == null) {
            return;
        }
        if (player.getVehicle() instanceof MoCHorseEntity horse
                && (horse.getSpecies() == Species.NIGHTMARE || horse.getSpecies() == Species.DARK_PEGASUS)) {
            event.setCanceled(true);
            return;
        }
        if (player.getVehicle() instanceof MoCManticoreEntity manticore && manticore.getVariant() == ManticoreVariant.FIRE) {
            event.setCanceled(true);
        }
        if (player.getVehicle() instanceof MoCScorpionEntity scorpion && scorpion.getVariant() == ScorpionVariant.NETHER) {
            event.setCanceled(true);
        }
        if (player.getVehicle() instanceof io.github.mestretramador.mocreaturesrr.entity.MoCOstrichEntity ostrich
                && ostrich.getEssence() == io.github.mestretramador.mocreaturesrr.entity.MoCOstrichEntity.ESSENCE_FIRE) {
            event.setCanceled(true);
        }
    }
}