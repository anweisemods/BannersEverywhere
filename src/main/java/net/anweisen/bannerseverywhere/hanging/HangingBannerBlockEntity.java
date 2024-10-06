package net.anweisen.bannerseverywhere.hanging;

import net.anweisen.bannerseverywhere.BannersEverywhereMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class HangingBannerBlockEntity extends BannerBlockEntity {

  public HangingBannerBlockEntity(BlockPos pos, BlockState state, DyeColor baseColor) {
    super(pos, state, baseColor);
  }

  public HangingBannerBlockEntity(BlockPos pos, BlockState state) {
    super(pos, state);
  }

  @Override
  public boolean supports(BlockState state) {
    return state.getBlock() instanceof HangingBannerBlock || super.supports(state);
  }

  @Override
  public BlockEntityType<?> getType() {
    return BannersEverywhereMod.HANGING_BANNER_TYPE;
  }

}
