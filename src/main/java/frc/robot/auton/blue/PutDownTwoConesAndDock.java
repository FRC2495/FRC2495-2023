package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.Dock;
import frc.robot.auton.common.DockOnChargeStation;
import frc.robot.auton.common.DropConeOnMiddleNodeAndShrink;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;
import frc.robot.auton.common.PickupConeFromFloor;
import frc.robot.commands.drivetrain.DrivetrainMoveDistanceWithStallDetection;
import frc.robot.commands.drivetrain.DrivetrainTurnAngleUsingPidControllerWithStallDetection;

// Can be used to place two cones in either starting position three or four
public class PutDownTwoConesAndDock extends SequentialCommandGroup {

    public PutDownTwoConesAndDock(){

        addCommands(
            new DropConeOnTopNodeAndShrink(),

            // maybe we have to move back a little bit before we can dock?
            // new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_DOCK),

            new DockOnChargeStation(),

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_DOCK_NEAR_NODE_TO_AREA_BEFORE_CONE_PICKUP),

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(+AutonConstants.ANGLE_BETWEEN_CONE_NODE_AND_CONE_PICKUP),

            new PickupConeFromFloor(),

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_CONE_PICKUP_TO_AREA_BEFORE_DOCK),

            new DockOnChargeStation(),

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_DOCK_NEAR_CONE_PICKUP_TO_AREA_NEAR_CONE_NODE),

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(+AutonConstants.ANGLE_BETWEEN_CONE_NODE_AND_CONE_PICKUP),

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_NEAR_CONE_NODE_TO_CONE_NODE),

            new DropConeOnMiddleNodeAndShrink(),

            // maybe we have to move back a little bit before we can dock?
            // new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_DOCK),

            new Dock()
            
        );
    }

    
   

}