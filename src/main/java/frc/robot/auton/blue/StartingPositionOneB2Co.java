package frc.robot.auton.blue;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.brake.BrakeSetReleased;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.auton.AutonConstants;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneB2Co extends SequentialCommandGroup {

    public StartingPositionOneB2Co(){

        addCommands(
            
            new BrakeSetReleased(),
            // makes sure that the brake is not on the floor before match begins

            new ShoulderMoveUpWithStallDetection(),
            // lifts shoulder up out of frame perimeter

            new ArmSafeExtendWithStallDetection(),
            // extends arm above cube node

            new ClawSetOpen(),
            // opens claw to put cube onto cube node

            new WaitCommand(1),

            new ClawSetClosed(),
            // closes claw

            new ArmRetractWithStallDetection(),
            // retracts arm

            new ShoulderSafeMoveDownWithStallDetection(),
            // brings shoulder into frame perimeter

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY), // todo change distance if needed
            // drives backward to outside community

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_CONE_NODE_TO_AREA_BEFORE_FIRST_TURN),
            // drives backward from cone node to area before the first turn

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(-20),
            // turns before moving forward

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_FIRST_TURN_TO_AREA_BEFORE_SECOND_TURN),
            // moves forward to before second turning point

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(160),
            // turns toward cone pickup

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_SECOND_TURN_TO_ARE_BEFORE_CONE_PICKUP),
            // moves forward to before pickup

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

            new DrivetrainTurnAngleUsingPidController(+90), //-230

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(+90+25), // split up the turn bc the robot cannot turn the other way bc of wall
            // turns toward cone node

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_THIRD_TURN_TO_AREA_BEFORE_FOURTH_TURN),
            
            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(-25),

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_FOURTH_TURN_TO_CONE_NODE)


        ); 
  
    }

    
   

}