package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.MoCreaturesReduxRedone;
import io.github.mestretramador.mocreaturesrr.entity.MoCHorseEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ARGB;
import net.minecraft.world.item.ItemStack;

public class MoCHorseRenderer extends MoCMobRenderer<MoCHorseEntity, MoCHorseModel> {

    public static final ModelLayerLocation MOC_HORSE_LAYER =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "moc_horse"), "main");

    public MoCHorseRenderer(EntityRendererProvider.Context context) {
        super(context, new MoCHorseModel(context.bakeLayer(MOC_HORSE_LAYER)), 0.75F);
        this.addLayer(new MoCExtraPassLayer<>(this, this::submitButterflyWings));
    }

    @Override
    protected void scale(MoCHorseEntity entity, PoseStack poseStack, float partialTick) {
        float scale;
        if (entity.isBaby()) {
            int age = entity.getSyncedAge(); // NOT getAge() — that's server-only
            float progress = (age + 24000F) / 24000F;
            progress = Math.max(0F, Math.min(1F, progress));
            scale = 0.5F + 0.5F * progress;
        } else {
            scale = 1.0F;
        }
        poseStack.scale(scale, scale, scale);
    }

    @Override
    protected RenderType getRenderType(MoCHorseEntity entity, boolean bodyVisible, boolean translucent, boolean showOutline) {
        boolean isGhost = entity.getSpecies() == io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species.GHOST
                || entity.getSpecies() == io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species.GHOST_WINGED;
        return super.getRenderType(entity, bodyVisible, translucent || isGhost || entity.isVanishing(), showOutline);
    }

    @Override
    public net.minecraft.world.phys.Vec3 getRenderOffset(MoCRenderState state) {
        net.minecraft.world.phys.Vec3 offset = super.getRenderOffset(state);
        if (state.entity instanceof MoCHorseEntity entity
                && (entity.getSpecies() == io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species.GHOST
                || entity.getSpecies() == io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species.GHOST_WINGED)) {
            offset = offset.add(0D, 0.3D, 0D);
        }
        return offset;
    }

    /** Original: ghosts (0.6 / winged 0.35) and vanishing horses were drawn through an alpha-replacing
     *  vertex consumer; the alpha now goes through the model tint. */
    @Override
    protected int getModelTint(MoCRenderState state) {
        if (!(state.entity instanceof MoCHorseEntity entity)) {
            return -1;
        }
        boolean isGhost = entity.getSpecies() == io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species.GHOST
                || entity.getSpecies() == io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species.GHOST_WINGED;
        if (!isGhost && !entity.isVanishing()) {
            return -1;
        }

        float alphaF = isGhost
                ? (entity.getSpecies() == io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species.GHOST_WINGED ? 0.35F : 0.6F)
                : entity.getVanishAlpha();
        int alphaInt = (int) (alphaF * 255F);
        return ARGB.color(alphaInt, 0xFFFFFF);
    }

    /**
     * Second half of the original model's renderToBuffer: the fairy / winged-ghost butterfly wings drawn
     * a second time, alone, into the body's buffer through {@link FullBrightVertexConsumer} (which forces
     * an upward normal so neither face of the flat wings gets shaded). A model draw can no longer be
     * wrapped by a custom vertex consumer, so this is submitted as custom geometry instead, re-posing
     * the model for pass 1 right before drawing it (exactly what a model submit does).
     */
    private void submitButterflyWings(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords,
                                      MoCRenderState state) {
        MoCHorseEntity horse = state.entity();
        var species = horse.getSpecies();
        if (species != io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species.FAIRY_HORSE
                && species != io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species.GHOST_WINGED) {
            return;
        }
        RenderType renderType = this.getRenderType(horse, true, false, state.appearsGlowing());
        if (renderType == null) {
            return;
        }
        int overlay = LivingEntityRenderer.getOverlayCoords(state, 0.0F);
        int color = this.getModelTint(state);
        MoCHorseModel horseModel = this.model;
        collector.submitCustomGeometry(poseStack, renderType, (pose, buffer) -> {
            horseModel.setupAnimForPass(state, 1);
            PoseStack wingPose = new PoseStack();
            wingPose.last().set(pose);
            horseModel.root().render(wingPose, new FullBrightVertexConsumer(buffer), lightCoords, overlay, color);
        });
    }
    @Override
    protected boolean showsHealthBar() {
        return true;
    }

    @Override
    public Identifier getTextureLocation(MoCHorseEntity entity) {
        if (entity.isUndeadTransforming()) {
            int ticksLeft = entity.getUndeadTransformTicks();
            int interval = Math.max(1, ticksLeft / 8);
            boolean showUndead = (entity.tickCount / interval) % 2 == 0;
            if (showUndead) {
                String flickerFile = switch (entity.getSpecies()) {
                    case UNICORN -> "horseundeadunicorn01";
                    case PEGASUS, DARK_PEGASUS -> "horseundeadpegasus01";
                    default -> "horseundead01";
                };
                return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_horse/" + flickerFile + ".png");
            }
            return textureFor(entity.getSpecies(), entity);
        }
        if (entity.isUndead()) {
            String fileName;
            if (entity.isSkeletonStage()) {
                fileName = switch (entity.getSpecies()) {
                    case UNICORN -> "horseunicornskeleton";
                    case PEGASUS, DARK_PEGASUS -> "horsepegasusskeleton";
                    default -> "horseskeleton";
                };
            } else {
                int stage = entity.getUndeadStage() - MoCHorseEntity.UNDEAD_STAGE_0; // 0-3
                String prefix;
                int frameCount;
                switch (entity.getSpecies()) {
                    case UNICORN -> { prefix = "horseundeadunicorn"; frameCount = 6; }
                    case PEGASUS, DARK_PEGASUS -> { prefix = "horseundeadpegasus"; frameCount = 7; }
                    default -> { prefix = "horseundead"; frameCount = 7; }
                }
                int frame = 1 + (entity.tickCount / 20) % frameCount;
                fileName = prefix + stage + frame;
            }
            return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_horse/" + fileName + ".png");
        }
        if (entity.isTransforming()) {
            int ticksLeft = entity.getTransformTicks();
            int interval = Math.max(1, ticksLeft / 8);
            boolean showTarget = (entity.tickCount / interval) % 2 == 0;
            var species = showTarget ? entity.getTransformTarget() : entity.getSpecies();
            return textureFor(species, entity);
        }
        if (entity.isColorTransforming()) {
            int ticksLeft = entity.getColorTransformTicks();
            int interval = Math.max(1, ticksLeft / 8);
            boolean showTarget = (entity.tickCount / interval) % 2 == 0;
            var targetColor = entity.getColorTransformTarget();
            String fileName = "horsefairy" + (showTarget ? targetColor.name().toLowerCase() : entity.getFairyColor().name().toLowerCase());
            return Identifier.fromNamespaceAndPath(MoCreaturesReduxRedone.MODID, "textures/entity/moc_horse/" + fileName + ".png");
        }
        return textureFor(entity.getSpecies(), entity);
    }
        
    private Identifier textureFor(io.github.mestretramador.mocreaturesrr.breeding.MoCHorseGenetics.Species species, MoCHorseEntity entity) {
        String fileName = switch (species) {
            case ZEBRA -> "horsezebra" + armorSuffix(entity);
            case DONKEY -> "horsedonkey";
            case MULE -> "horsemule";
            case ZONKY -> "horsezonky";
            case ZORSE -> "horsezorse" + armorSuffix(entity);
            case BATHORSE -> "horsebat" + crystalSuffix(entity);
            case NIGHTMARE -> {
                int frame = 1 + (entity.tickCount / 5) % 5;
                yield "horsenightmare" + frame;
            }
            case UNICORN -> "horseunicorn" + crystalSuffix(entity);
            case PEGASUS -> "horsepegasus" + crystalSuffix(entity);
            case DARK_PEGASUS -> "horsedarkpegasus" + crystalSuffix(entity);
            case GHOST -> "horseghostb" + crystalSuffix(entity);
            case GHOST_WINGED -> "horseghost" + crystalSuffix(entity);
            case HORSE_BUG -> "horsebug";
            case FAIRY_HORSE -> "horsefairy" + entity.getFairyColor().name().toLowerCase() + crystalSuffix(entity);
            case HORSE -> "horse" + entity.getCoat().name().toLowerCase() + armorSuffix(entity);
        };
        return Identifier.fromNamespaceAndPath(
                MoCreaturesReduxRedone.MODID, "textures/entity/moc_horse/" + fileName + ".png");
    }
    private static String armorSuffix(MoCHorseEntity entity) {
        ItemStack armorItem = entity.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.BODY);
        if (armorItem.is(net.minecraft.world.item.Items.IRON_HORSE_ARMOR)) return "metal";
        if (armorItem.is(net.minecraft.world.item.Items.GOLDEN_HORSE_ARMOR)) return "gold";
        if (armorItem.is(net.minecraft.world.item.Items.DIAMOND_HORSE_ARMOR)) return "diamond";
        return "";
    }

    private static String crystalSuffix(MoCHorseEntity entity) {
        ItemStack armorItem = entity.getItemBySlot(net.minecraft.world.entity.EquipmentSlot.BODY);
        return armorItem.is(io.github.mestretramador.mocreaturesrr.init.ModItems.HORSE_ARMOR_CRYSTAL.get()) ? "crystaline" : "";
    }
}