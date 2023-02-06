package frc.robot.auton.blue;

// GP = game piece

import frc.robot.commands.claw.*;
import frc.robot.commands.hinge.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.auton.AutonConstants;

public class StartingPositionOneAndTwo1GP extends SequentialCommandGroup {

    public StartingPositionOneAndTwo1GP(){

        addCommands(
            new HingeMoveUp());
            // lifts hinge up out of frame perimeter  
            
            new ArmExtendWithStallDetection();
            // extends arm above cube node

            // use limelight to adjust claw

            new ClawSetOpen();
            // opens claw to put cube onto cube node

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY);
            // moves backwards to outside the community
            
  
    }

    
   

}