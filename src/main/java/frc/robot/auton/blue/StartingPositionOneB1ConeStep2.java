package frc.robot.auton.blue;

import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.AutonConstants;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneB1ConeStep2 extends SequentialCommandGroup {

    public StartingPositionOneB1ConeStep2(){

        addCommands(

            new StartingPositionOneB1ConeStep1(),

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY) // todo change distance if needed
            // drives backward to outside community

        ); 
  
    }

    
   

}