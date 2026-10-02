package io.github.mestretramador.mocreaturesrr.client;

import io.github.mestretramador.mocreaturesrr.entity.MoCDragonflyEntity;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

/** Port of {@code MoCModelDragonfly}: four wings, always drawn 60% opaque, and — like the source —
 *  no head tracking at all. */
public class MoCDragonflyModel<T extends MoCDragonflyEntity> extends MoCInsectModel<T> {
    private final ModelPart head;
    private final ModelPart rAntenna;
    private final ModelPart lAntenna;
    private final ModelPart mouth;
    private final ModelPart thorax;
    private final ModelPart abdomen;
    private final ModelPart frontLegs;
    private final ModelPart midLegs;
    private final ModelPart rearLegs;
    private final ModelPart wingFrontRight;
    private final ModelPart wingFrontLeft;
    private final ModelPart wingRearRight;
    private final ModelPart wingRearLeft;
    private boolean flying;

    public MoCDragonflyModel(ModelPart root) {
        super(root);
        this.head = root.getChild("Head");
        this.rAntenna = root.getChild("RAntenna");
        this.lAntenna = root.getChild("LAntenna");
        this.mouth = root.getChild("Mouth");
        this.thorax = root.getChild("Thorax");
        this.abdomen = root.getChild("Abdomen");
        this.frontLegs = root.getChild("FrontLegs");
        this.midLegs = root.getChild("MidLegs");
        this.rearLegs = root.getChild("RearLegs");
        this.wingFrontRight = root.getChild("WingFrontRight");
        this.wingFrontLeft = root.getChild("WingFrontLeft");
        this.wingRearRight = root.getChild("WingRearRight");
        this.wingRearLeft = root.getChild("WingRearLeft");
    }

    /** The texture layout is 32x32 (the PNG itself is a 2x-resolution version). */
    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("Head",
                CubeListBuilder.create().texOffs(0, 4).addBox(-1F, 0F, -1F, 2, 1, 2),
                PartPose.offsetAndRotation(0F, 21F, -2F, -2.171231F, 0F, 0F));
        root.addOrReplaceChild("RAntenna",
                CubeListBuilder.create().texOffs(0, 7).addBox(-0.5F, 0F, -1F, 1, 0, 1),
                PartPose.offsetAndRotation(-0.5F, 19.7F, -2.3F, -1.041001F, 0.7853982F, 0F));
        root.addOrReplaceChild("LAntenna",
                CubeListBuilder.create().texOffs(4, 7).addBox(-0.5F, 0F, -1F, 1, 0, 1),
                PartPose.offsetAndRotation(0.5F, 19.7F, -2.3F, -1.041001F, -0.7853982F, 0F));
        root.addOrReplaceChild("Mouth",
                CubeListBuilder.create().texOffs(0, 11).addBox(-0.5F, 0F, 0F, 1, 1, 1),
                PartPose.offsetAndRotation(0F, 21.1F, -2.3F, -2.171231F, 0F, 0F));
        root.addOrReplaceChild("Thorax",
                CubeListBuilder.create().texOffs(0, 0).addBox(-1F, 0F, -1F, 2, 2, 2),
                PartPose.offset(0F, 20F, -1F));
        root.addOrReplaceChild("Abdomen",
                CubeListBuilder.create().texOffs(8, 0).addBox(-0.5F, 0F, -1F, 1, 7, 1),
                PartPose.offsetAndRotation(0F, 20.5F, 0F, 1.427659F, 0F, 0F));
        root.addOrReplaceChild("FrontLegs",
                CubeListBuilder.create().texOffs(0, 8).addBox(-1F, 0F, 0F, 2, 3, 0),
                PartPose.offsetAndRotation(0F, 21.5F, -1.8F, 0.1487144F, 0F, 0F));
        root.addOrReplaceChild("MidLegs",
                CubeListBuilder.create().texOffs(4, 8).addBox(-1F, 0F, 0F, 2, 3, 0),
                PartPose.offsetAndRotation(0F, 22F, -1.2F, 0.5948578F, 0F, 0F));
        root.addOrReplaceChild("RearLegs",
                CubeListBuilder.create().texOffs(8, 8).addBox(-1F, 0F, 0F, 2, 3, 0),
                PartPose.offsetAndRotation(0F, 22F, -0.4F, 1.070744F, 0F, 0F));
        root.addOrReplaceChild("WingFrontRight",
                CubeListBuilder.create().texOffs(0, 28).addBox(-7F, 0F, -1F, 7, 0, 2),
                PartPose.offsetAndRotation(-1F, 20F, -1F, 0F, -0.1396263F, 0.0872665F));
        root.addOrReplaceChild("WingFrontLeft",
                CubeListBuilder.create().texOffs(0, 30).addBox(0F, 0F, -1F, 7, 0, 2),
                PartPose.offsetAndRotation(1F, 20F, -1F, 0F, 0.1396263F, -0.0872665F));
        root.addOrReplaceChild("WingRearRight",
                CubeListBuilder.create().texOffs(0, 24).addBox(-7F, 0F, -1F, 7, 0, 2),
                PartPose.offsetAndRotation(-1F, 20F, -1F, 0F, 0.3490659F, -0.0872665F));
        root.addOrReplaceChild("WingRearLeft",
                CubeListBuilder.create().texOffs(0, 26).addBox(0F, 0F, -1F, 7, 0, 2),
                PartPose.offsetAndRotation(1F, 20F, -1F, 0F, -0.3490659F, 0.0872665F));

        return LayerDefinition.create(mesh, 32, 32);
    }

    @Override
    public void setupAnim(T dragonfly, float limbSwing, float limbSwingAmount, float ageInTicks,
                          float netHeadYaw, float headPitch) {
        this.flying = dragonfly.isFlying() || dragonfly.getDeltaMovement().y < -0.1D;

        float wingRot = 0.0F;
        float legMov;
        float legMovB;
        if (this.flying) {
            wingRot = Mth.cos(ageInTicks * 2.0F) * 0.5F;
            legMov = limbSwingAmount * 1.5F;
            legMovB = legMov;
        } else {
            legMov = Mth.cos(limbSwing * 1.5F + Mth.PI) * 2.0F * limbSwingAmount;
            legMovB = Mth.cos(limbSwing * 1.5F) * 2.0F * limbSwingAmount;
        }
        this.wingFrontRight.zRot = wingRot;
        this.wingRearLeft.zRot = wingRot;
        this.wingFrontLeft.zRot = -wingRot;
        this.wingRearRight.zRot = -wingRot;
        this.frontLegs.xRot = 0.1487144F + legMov;
        this.midLegs.xRot = 0.5948578F + legMovB;
        this.rearLegs.xRot = 1.070744F + legMov;
    }

    /** The four wings are always drawn 60% opaque. */
    @Override
    protected ModelPart[] translucentWings() {
        return new ModelPart[] {this.wingRearRight, this.wingFrontRight, this.wingFrontLeft, this.wingRearLeft};
    }
}
