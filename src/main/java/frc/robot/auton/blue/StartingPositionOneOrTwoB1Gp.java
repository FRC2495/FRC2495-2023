package frc.robot.auton.blue;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.auton.AutonConstants;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneOrTwoB1Gp extends SequentialCommandGroup {

    public StartingPositionOneOrTwoB1Gp(){

        addCommands(
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

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY)
            // moves backwards to outside the community
        ); 
  
    }

    
   

}