package net.tme.saws.render.block.entity;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.block.DoubleBlockProperties;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.BedPart;
import net.minecraft.client.model.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.LightmapCoordinatesRetriever;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.World;
import net.tme.saws.Saws;
import net.tme.saws.entity.SleepingBagBlockEntity;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.tme.saws.render.SawsEntityModelLayers;
import net.minecraft.block.entity.BedBlockEntity;
import net.minecraft.util.math.Direction;

public class SleepingBagBlockEntityRenderer implements BlockEntityRenderer<SleepingBagBlockEntity> {
    private final String type;
    protected ModelPart headPiece;
    protected ModelPart footPiece;

    public SleepingBagBlockEntityRenderer(BlockEntityRendererFactory.Context ctx, String type) {
        ctx.getLayerModelPart(SawsEntityModelLayers.SLEEPING_BAG_HEAD);
        ctx.getLayerModelPart(SawsEntityModelLayers.SLEEPING_BAG_FOOT);
        this.type = type;
    }

    public static TexturedModelData createHeadLayer() {
        ModelData model = new ModelData();
        ModelPartData modelPartData = model.getRoot();
        modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 3.0F), ModelTransform.NONE);
        return TexturedModelData.of(model, 64, 64);
    }

    public static TexturedModelData createFootLayer() {
        ModelData model = new ModelData();
        ModelPartData modelPartData = model.getRoot();
        modelPartData.addChild("main", ModelPartBuilder.create().uv(0, 19).cuboid(0.0F, 0.0F, 0.0F, 16.0F, 16.0F, 3.0F), ModelTransform.NONE);
        return TexturedModelData.of(model, 64, 64);
    }

    @Override
    public void render(SleepingBagBlockEntity entity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        SpriteIdentifier material = new SpriteIdentifier(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, new Identifier(Saws.MOD_ID, "entity/" + this.type + "/" + entity.getColor().getName()));
        World level = entity.getWorld();
        if (level != null) {
            BlockState blockstate = entity.getCachedState();
            DoubleBlockProperties.PropertySource<? extends BedBlockEntity> propertySource = DoubleBlockProperties.toPropertySource(BlockEntityType.BED, BedBlock::getBedPart, BedBlock::getOppositePartDirection, ChestBlock.FACING, blockstate, level, entity.getPos(), (world, pos) -> (false));
            int k = ((Int2IntFunction)propertySource.apply(new LightmapCoordinatesRetriever())).get(light);
            this.renderPiece(matrices, vertexConsumers, blockstate.get(BedBlock.PART) == BedPart.HEAD, blockstate.get(BedBlock.FACING), material, k, overlay, false);
        } else {
            this.renderPiece(matrices, vertexConsumers, true, Direction.SOUTH, material, light, overlay, false);
            this.renderPiece(matrices, vertexConsumers, false, Direction.SOUTH, material, light, overlay, true);
        }
    }

    protected void renderPiece(MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, boolean isHead, Direction direction, SpriteIdentifier material, int light, int overlay, boolean isFoot) {
        this.headPiece.visible = isHead;
        this.footPiece.visible = !isHead;
        matrixStack.push();
        matrixStack.translate(0.0, 0.1875, isFoot ? -1.0 : 0.0);
        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90.0F));
        matrixStack.translate(0.5, 0.5, 0.5);
        matrixStack.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(180.0F + direction.asRotation()));
        matrixStack.translate(-0.5, -0.5, -0.5);
        VertexConsumer vertexConsumer = material.getVertexConsumer(vertexConsumers, RenderLayer::getEntitySolid);
        this.headPiece.render(matrixStack, vertexConsumer, light, overlay);
        this.footPiece.render(matrixStack, vertexConsumer, light, overlay);
        matrixStack.pop();
    }
}