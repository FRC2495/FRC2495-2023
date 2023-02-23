package frc.robot.auton.blue;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.auton.AutonConstants;

public class StartingPositionThreeOrFourB1GpAndDock extends SequentialCommandGroup {

    public StartingPositionThreeOrFourB1GpAndDock(){

        addCommands(
            new ShoulderMoveUpWithStallDetection(),
            // lifts shoulder up out of frame perimeter  
            
            new ArmSafeExtendWithStallDetection(),
            // extends arm above cube node

            new ClawSetOpen(),
            // opens claw to put cube onto cube node

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_CUBE_NODE_TO_CHARGING_STATION)
            // moves backwards onto charging station

            // rubber block mechanic?
            
        );
    }

    
   

}