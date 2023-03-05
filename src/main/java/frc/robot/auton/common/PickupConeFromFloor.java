package frc.robot.auton.common;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.auton.AutonConstants;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class PickupConeFromFloor extends SequentialCommandGroup {

    public PickupConeFromFloor(){

        addCommands(


            new ShoulderSafeMoveFloorWithStallDetection(),
            // brings shoulder to floor position to prepare to pickup second cone

            new ClawSetOpen(),
            // opens claw to pickup cone

            new WaitCommand(1),
            // allows for robot to realize that claw is open

            new ArmSafeExtendWithStallDetection(),
            // extends arm to reach cone

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_BEFORE_CONE_PICKUP_TO_CONE_PICKUP),
            // moves forward to pick up cone

            new ClawSetClosed(),
            // grabs cone 

            new ArmRetractWithStallDetection(),

            new ShoulderSafeMoveDownWithStallDetection(),
            // brings the shoulder into frame perimeter

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_CONE_PICKUP_TO_AREA_BEFORE_THIRD_TURN),

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(+AutonConstants.ANGLE_BETWEEN_AREA_BEFORE_THIRD_TURN_AND_TOWARDS_CONE_NODE_FIRST_PART), //-230

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(+AutonConstants.ANGLE_BETWEEN_AREA_BEFORE_THIRD_TURN_AND_TOWARDS_CONE_NODE_SECOND_PART), // split up the turn bc the robot cannot turn the other way bc of wall
            // turns toward cone node

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_THIRD_TURN_TO_AREA_BEFORE_FOURTH_TURN),
            
            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(-AutonConstants.ANGLE_BETWEEN_AREA_BEFORE_FOURTH_TURN_AND_CONE_NODE),
            // turns to cone node

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_FOURTH_TURN_TO_CONE_NODE),

            new DropConeOnMiddleNodeAndShrink(),

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY),
            // drives back to prepare for teleop

            new DrivetrainTurnAngleUsingPidController(+AutonConstants.ANGLE_BETWEEN_CONE_NODE_AND_CONE_PICKUP)
            // turns 180 to prepare for teleop

        ); 
  
    }

    
   

}