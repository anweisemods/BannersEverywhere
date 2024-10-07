package net.anweisen.bannerseverywhere.side;

import net.anweisen.bannerseverywhere.BannersEverywhereMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BannerBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.1
 */
public class SideBannerBlockEntity extends BannerBlockEntity {

  public SideBannerBlockEntity(BlockPos pos, BlockState state, DyeColor baseColor) {
    super(pos, state, baseColor);
  }

  public SideBannerBlockEntity(BlockPos pos, BlockState state) {
    super(pos, state);
  }

  @Override
  public boolean supports(BlockState state) {
    return state.getBlock() instanceof SideBannerBlock || super.supports(state);
  }

  @Override
  public BlockEntityType<?> getType() {
    return BannersEverywhereMod.SIDE_BANNER_TYPE;
  }

}
