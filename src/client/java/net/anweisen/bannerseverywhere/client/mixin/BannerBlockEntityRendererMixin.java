package net.anweisen.bannerseverywhere.client.mixin;

import net.anweisen.bannerseverywhere.hanging.HangingBannerBlock;
import net.anweisen.bannerseverywhere.hanging.HangingBannerBlockEntity;
import net.anweisen.bannerseverywhere.sideways.SidewaysBannerBlock;
import net.anweisen.bannerseverywhere.sideways.SidewaysBannerBlockEntity;
import net.minecraft.block.BannerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBannerBlock;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BannerBlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
@Mixin(value = BannerBlockEntityRenderer.class, priority = 10000)
public abstract class BannerBlockEntityRendererMixin implements BlockEntityRenderer<BannerBlockEntity> {

  @Shadow
  @Final
  private ModelPart banner;

  @Shadow
  @Final
  private ModelPart pillar;

  @Shadow
  @Final
  private ModelPart crossbar;

  @Inject(method = "render(Lnet/minecraft/block/entity/BannerBlockEntity;FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;II)V", at = @At("HEAD"), cancellable = true)
  private void render(BannerBlockEntity bannerBlockEntity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, int overlay, CallbackInfo callback) {
    BlockState blockState = bannerBlockEntity.getCachedState();

    if (bannerBlockEntity instanceof SidewaysBannerBlockEntity && blockState.getBlock() instanceof SidewaysBannerBlock) {
      try {
        renderSidewaysBanner(bannerBlockEntity, tickDelta, matrixStack, vertexConsumerProvider, light, overlay, blockState);
        callback.cancel();
      } catch (Exception ex) { // dont crash the game
        ex.printStackTrace();
      }
      return;
    }

    if (bannerBlockEntity instanceof HangingBannerBlockEntity && blockState.getBlock() instanceof HangingBannerBlock) {
      try {
        renderHangingBanner(bannerBlockEntity, tickDelta, matrixStack, vertexConsumerProvider, light, overlay, blockState);
        callback.cancel();
      } catch (Exception ex) { // dont crash the game
        ex.printStackTrace();
      }
    }
  }

  private void renderSidewaysBanner(BannerBlockEntity bannerBlockEntity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, int overlay, BlockState blockState) {
    Direction facing = blockState.get(WallBannerBlock.FACING);

    matrixStack.push();
    matrixStack.translate(0.5F, 0.5F, 0.5);
    matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-facing.asRotation()));
    matrixStack.translate(0, 0, 1);
    int orientationInversion = blockState.get(SidewaysBannerBlock.ORIENTATION) != facing.rotateYCounterclockwise() ? -1 : 1;
    matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90));
    matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(orientationInversion * 90)); // originally: z-axis(north)
    matrixStack.translate(0, 0.166667, 0);

    matrixStack.push();
    matrixStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
    VertexConsumer vertexConsumer = ModelLoader.BANNER_BASE.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntitySolid);
    this.pillar.pivotX = -9 * orientationInversion;
    this.pillar.pivotY = 0;
    this.pillar.render(matrixStack, vertexConsumer, light, overlay);

    this.crossbar.render(matrixStack, vertexConsumer, light, overlay);
    this.banner.pivotY = -29.3333F;
    this.banner.pivotZ = .5F;

    BlockPos blockPos = bannerBlockEntity.getPos();
    long time = bannerBlockEntity.getWorld().getTime();
    float swingAngle = MathHelper.sin((blockPos.getX() * 7L + blockPos.getY() * 9L + blockPos.getZ() * 13L + time + tickDelta) * 0.1F) * 2.0F;
    matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(swingAngle), -1 * orientationInversion, 0, 0);

    BannerBlockEntityRenderer.renderCanvas(matrixStack, vertexConsumerProvider, light, overlay, this.banner, ModelLoader.BANNER_BASE, true, bannerBlockEntity.getColorForState(), bannerBlockEntity.getPatterns());
    matrixStack.pop();
    matrixStack.pop();
  }

  @Unique
  private void renderHangingBanner(BannerBlockEntity bannerBlockEntity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int light, int overlay, BlockState blockState) {
    float rotation = RotationPropertyHelper.toDegrees(blockState.get(BannerBlock.ROTATION));

    matrixStack.push();
    matrixStack.translate(0.5F, -0.5F + 0.166667F, 0.5); // center and move to ceiling
    matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-rotation));

    matrixStack.push();
    matrixStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
    VertexConsumer vertexConsumer = ModelLoader.BANNER_BASE.getVertexConsumer(vertexConsumerProvider, RenderLayer::getEntitySolid);
    this.crossbar.pivotZ = -1.5F;
    this.crossbar.render(matrixStack, vertexConsumer, light, overlay);

    BlockPos blockPos = bannerBlockEntity.getPos();
    long time = bannerBlockEntity.getWorld().getTime();
    float swingAngle = ((float) Math.floorMod(blockPos.getX() * 7L + blockPos.getY() * 9L + blockPos.getZ() * 13L + time, 100L) + tickDelta) / 100.0F;
    this.banner.pitch = (-0.0125F + 0.01F * MathHelper.cos(6.2831855F * swingAngle)) * 3.1415927F;
    this.banner.pivotY = -30.0F;

    BannerBlockEntityRenderer.renderCanvas(matrixStack, vertexConsumerProvider, light, overlay, this.banner, ModelLoader.BANNER_BASE, true, bannerBlockEntity.getColorForState(), bannerBlockEntity.getPatterns());
    matrixStack.pop();
    matrixStack.pop();
  }

}
