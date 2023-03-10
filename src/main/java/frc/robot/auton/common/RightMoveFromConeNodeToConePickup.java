package frc.robot.auton.common;

import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.AutonConstants;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class RightMoveFromConeNodeToConePickup extends SequentialCommandGroup {

    public RightMoveFromConeNodeToConePickup(){

        addCommands(

            //new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY), // todo change distance if needed
            // drives backward to outside community

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_CONE_NODE_TO_AREA_BEFORE_FIRST_TURN),
            // drives backward from cone node to area before the first turn

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(+AutonConstants.ANGLE_BETWEEN_CONE_NODE_AND_AREA_AFTER_FIRST_TURN),
            // turns before moving forward

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_FIRST_TURN_TO_AREA_BEFORE_SECOND_TURN),
            // moves forward to before second turning point

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(-AutonConstants.ANGLE_BETWEEN_AREA_AFTER_FIRST_TURN_AND_CONE_PICKUP),
            // turns toward cone pickup

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_SECOND_TURN_TO_AREA_BEFORE_CONE_PICKUP)
            // moves forward to before pickup

        ); 
  
    }

    
   

}