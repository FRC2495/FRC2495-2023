package frc.robot.auton.common;

import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.AutonConstants;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class RightMoveFromConePickupToConeNode extends SequentialCommandGroup {

    public RightMoveFromConePickupToConeNode(){

        addCommands(

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_CONE_PICKUP_TO_AREA_BEFORE_THIRD_TURN),

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(-AutonConstants.ANGLE_BETWEEN_AREA_BEFORE_THIRD_TURN_AND_TOWARDS_CONE_NODE_FIRST_PART), //-230

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(-AutonConstants.ANGLE_BETWEEN_AREA_BEFORE_THIRD_TURN_AND_TOWARDS_CONE_NODE_SECOND_PART), // split up the turn bc the robot cannot turn the other way bc of wall
            // turns toward cone node

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_THIRD_TURN_TO_AREA_BEFORE_FOURTH_TURN),
            
            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(+AutonConstants.ANGLE_BETWEEN_AREA_BEFORE_FOURTH_TURN_AND_CONE_NODE),
            // turns to cone node

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_FOURTH_TURN_TO_CONE_NODE)
            
        ); 
  
    }

    
   

}