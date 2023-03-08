package frc.robot.auton.common;

import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.AutonConstants;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class FinalBackup extends SequentialCommandGroup {

    public FinalBackup(){

        addCommands(

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY), // todo change distance if needed
            // drives backward to outside community

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(+AutonConstants.ANGLE_BETWEEN_CONE_NODE_AND_CONE_PICKUP)
            // turns to get ready for teleop
            
        ); 
  
    }

    
   

}