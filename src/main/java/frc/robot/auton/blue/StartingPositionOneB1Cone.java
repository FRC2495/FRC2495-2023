package frc.robot.auton.blue;

import frc.robot.commands.brake.BrakeSetReleased;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneB1Cone extends SequentialCommandGroup {

    public StartingPositionOneB1Cone(){

        addCommands(
            
            new BrakeSetReleased(),
            // makes sure that the brake is not on the floor before match begins

            new DropConeOnTopNodeAndShrink(),
            // drops cone on top node and brings arm into frame perimeter

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY) // todo change distance if needed
            // drives backward to outside community

        ); 
  
    }

    
   

}