package net.anweisen.bannerseverywhere.util;

import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

/**
 * @author anweisen | https://github.com/anweisen
 * @since 1.1
 */
public class OrientationHelper {

  public static final DirectionProperty ORIENTATION_PROPERTY = DirectionProperty.of("orientation", Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);

  public static BlockState getPlacementStateWithFacingAndOrientation(ItemPlacementContext context, BlockState defaultState) {
    WorldView worldView = context.getWorld();
    BlockPos blockPos = context.getBlockPos();
    Direction[] directions = context.getPlacementDirections();

    for (Direction direction : directions) {
      if (direction.getAxis().isHorizontal()) {
        Direction facing = direction.getOpposite();
        defaultState = defaultState.with(HorizontalFacingBlock.FACING, facing);
        if (defaultState.canPlaceAt(worldView, blockPos)) {
          return defaultState.with(OrientationHelper.ORIENTATION_PROPERTY, OrientationHelper.calculateNearerOrientationDirection(direction, context.getPlayerYaw()));
        }
      }
    }

    return null;
  }

  public static Direction calculateNearerOrientationDirection(Direction currentDirection, float yaw) {
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
}
