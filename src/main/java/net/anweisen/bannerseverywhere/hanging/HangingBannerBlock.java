package net.anweisen.bannerseverywhere.hanging;

import net.minecraft.block.BannerBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class HangingBannerBlock extends BannerBlock {

  public HangingBannerBlock(DyeColor dyeColor, Settings settings) {
    super(dyeColor, settings);
  }

  @Override
  protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
    return world.getBlockState(pos.up()).isSolid();
  }

  @Override
  public BlockState getPlacementState(ItemPlacementContext context) {
    return super.getPlacementState(context);
  }

  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new HangingBannerBlockEntity(pos, state);
  }
}
