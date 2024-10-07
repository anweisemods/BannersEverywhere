package net.anweisen.bannerseverywhere.mixin;

import net.anweisen.bannerseverywhere.BannersEverywhereMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BannerItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.VerticallyAttachableBlockItem;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
@Mixin(VerticallyAttachableBlockItem.class)
public abstract class BannerItemMixin extends BlockItem {

  public BannerItemMixin(Block block, Settings settings) {
    super(block, settings);
  }

  @Inject(method = "getPlacementState", at = @At("HEAD"), cancellable = true)
  protected void getPlacementState(ItemPlacementContext context, CallbackInfoReturnable<BlockState> callback) {
    if (context.getPlayer() == null) return;

    if ((VerticallyAttachableBlockItem) (BlockItem) this instanceof BannerItem item) {
      Direction side = context.getSide();

      if (side != Direction.UP && side != Direction.DOWN) {
        double deltaY = context.getHitPos().getY() - context.getBlockPos().getY();
        BlockState state;
        if (deltaY < 0.25 || deltaY > 0.75) {
          state = BannersEverywhereMod.getSideBannerBlock(item.getColor()).getPlacementState(context);
        } else if (context.getPlayer().isSneaking()) {
          state = BannersEverywhereMod.getSidewaysBannerBlock(item.getColor()).getPlacementState(context);
        } else {
          return;
        }
        callback.setReturnValue(state);
      } else if (side == Direction.DOWN) {
        BlockState state = BannersEverywhereMod.getHangingBannerBlock(item.getColor()).getPlacementState(context);
        callback.setReturnValue(state);
      }
    }
  }

}
