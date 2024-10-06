package net.anweisen.bannerseverywhere.sideways;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.WallBannerBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import java.util.Map;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.0
 */
public class SidewaysBannerBlock extends WallBannerBlock {

  public static final DirectionProperty ORIENTATION = DirectionProperty.of("orientation", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);

  private static final double OUTLINE_WIDTH = 2, HEIGHT_MARGIN = 1.5;
  private static final Map<Direction, VoxelShape> FACING_TO_SHAPE = Maps.newEnumMap(ImmutableMap.of(
    Direction.NORTH, Block.createCuboidShape(8 - OUTLINE_WIDTH, HEIGHT_MARGIN, 0, 8 + OUTLINE_WIDTH, 16 - HEIGHT_MARGIN, 16),
    Direction.SOUTH, Block.createCuboidShape(8 - OUTLINE_WIDTH, HEIGHT_MARGIN, 0, 8 + OUTLINE_WIDTH, 16 - HEIGHT_MARGIN, 16),
    Direction.WEST, Block.createCuboidShape(0, HEIGHT_MARGIN, 8 - OUTLINE_WIDTH, 16, 16 - HEIGHT_MARGIN, 8 + OUTLINE_WIDTH),
    Direction.EAST, Block.createCuboidShape(0, HEIGHT_MARGIN, 8 - OUTLINE_WIDTH, 16, 16 - HEIGHT_MARGIN, 8 + OUTLINE_WIDTH))
  );

  public SidewaysBannerBlock(DyeColor color, Settings settings) {
    super(color, settings);
    this.setDefaultState(stateManager.getDefaultState().with(FACING, Direction.NORTH).with(ORIENTATION, Direction.WEST));
  }

  @Override
  protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
    builder.add(FACING, ORIENTATION);
  }

  @Override
  @Nullable
  public BlockState getPlacementState(ItemPlacementContext context) {
    BlockState blockState = this.getDefaultState();
    WorldView worldView = context.getWorld();
    BlockPos blockPos = context.getBlockPos();
    Direction[] directions = context.getPlacementDirections();

    for (Direction direction : directions) {
      if (direction.getAxis().isHorizontal()) {
        Direction facing = direction.getOpposite();
        blockState = blockState.with(FACING, facing);
        if (blockState.canPlaceAt(worldView, blockPos)) {
          return blockState.with(ORIENTATION, calculateNearerOrientationDirection(direction, context.getPlayerYaw()));
        }
      }
    }

    return null;
  }

  public Direction calculateNearerOrientationDirection(Direction currentDirection, float yaw) {
    float normalizedYaw = Math.abs(yaw + 360) % 360;

    Direction clockwiseDirection = currentDirection.rotateYClockwise();
    Direction counterclockwiseDirection = currentDirection.rotateYCounterclockwise();

    float clockwiseYaw = clockwiseDirection.asRotation();
    float counterclockwiseYaw = counterclockwiseDirection.asRotation();
    if (clockwiseYaw == 0 && normalizedYaw > 180) clockwiseYaw = 360;
    if (counterclockwiseYaw == 0 && normalizedYaw > 180) counterclockwiseYaw = 360;

    float clockwiseDiff = Math.abs((normalizedYaw - clockwiseYaw) % 360);
    float counterclockwiseDiff = Math.abs((normalizedYaw - counterclockwiseYaw) % 360);

    // get direction further away
    return (clockwiseDiff < counterclockwiseDiff) ? counterclockwiseDirection : clockwiseDirection;
  }

  @Override
  public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
    return new SidewaysBannerBlockEntity(pos, state, this.getColor());
  }

  @Override
  protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
    return FACING_TO_SHAPE.get(state.get(FACING));
  }
}
