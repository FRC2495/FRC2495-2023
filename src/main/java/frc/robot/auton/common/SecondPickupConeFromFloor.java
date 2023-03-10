package frc.robot.auton.common;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.auton.AutonConstants;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class SecondPickupConeFromFloor extends SequentialCommandGroup {

    public SecondPickupConeFromFloor(){

        addCommands(


            new ShoulderSafeMoveFloorWithStallDetection(),
            // brings shoulder to floor position to prepare to pickup second cone

            new ClawSetOpen(),
            // opens claw to pickup cone

            new WaitCommand(1),
            // allows for robot to realize that claw is open

            new ArmSafeExtendWithStallDetection(),
            // extends arm to reach cone

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_OUTSIDE_COMMUNITY_TO_SECOND_CONE_PICKUP),
            // moves forward to pick up cone

            new ClawSetClosed()
            // grabs cone 

        ); 
  
    }

    
   

}