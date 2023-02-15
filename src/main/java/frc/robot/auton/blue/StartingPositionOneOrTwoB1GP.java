package frc.robot.auton.blue;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.auton.AutonConstants;

public class StartingPositionOneOrTwoB1GP extends SequentialCommandGroup {

    public StartingPositionOneOrTwoB1GP(){

        addCommands(
            new ShoulderMoveUp());
            // lifts shoulder up out of frame perimeter  
            
            new ArmExtendWithStallDetection();
            // extends arm above cube node


            new ClawSetOpen();
            // opens claw to put cube onto cube node

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY);
            // moves backwards to outside the community
            
  
    }

    
   

}