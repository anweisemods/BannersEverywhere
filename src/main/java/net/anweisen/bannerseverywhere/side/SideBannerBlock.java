package net.anweisen.bannerseverywhere.side;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.anweisen.bannerseverywhere.util.OrientationHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallBannerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.1
 */
public class SideBannerBlock extends WallBannerBlock {

  private static final double OUTLINE_WIDTH = 2, HEIGHT_MARGIN = 1.5;
  private static final Map<Direction, VoxelShape> FACING_TO_SHAPE = Maps.newEnumMap(ImmutableMap.of(
    Direction.NORTH, Block.createCuboidShape(8 - OUTLINE_WIDTH, HEIGHT_MARGIN, 0, 8 + OUTLINE_WIDTH, 16 - HEIGHT_MARGIN, 16),
    Direction.SOUTH, Block.createCuboidShape(8 - OUTLINE_WIDTH, HEIGHT_MARGIN, 0, 8 + OUTLINE_WIDTH, 16 - HEIGHT_MARGIN, 16),
    Direction.WEST, Block.createCuboidShape(0, HEIGHT_MARGIN, 8 - OUTLINE_WIDTH, 16, 16 - HEIGHT_MARGIN, 8 + OUTLINE_WIDTH),
    Direction.EAST, Block.createCuboidShape(0, HEIGHT_MARGIN, 8 - OUTLINE_WIDTH, 16, 16 - HEIGHT_MARGIN, 8 + OUTLINE_WIDTH))
  );

  public SideBannerBlock(DyeColor color, Settings settings) {
    super(color, settings);
    this.setDefaultState(stateManager.getDefaultState().with(FACING, Direction.NORTH).with(OrientationHelper.ORIENTATION_PROPERTY, Direction.WEST));
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING, OrientationHelper.ORIENTATION_PROPERTY);
  }

  @Override
  @Nullable
  public BlockState getPlacementState(ItemPlacementContext context) {
    return OrientationHelper.getPlacementStateWithFacingAndOrientation(context, this.getDefaultState());
  }

  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new SideBannerBlockEntity(pos, state, this.getColor());
  }

  @Override
  protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return FACING_TO_SHAPE.get(state.get(FACING));
  }
}
