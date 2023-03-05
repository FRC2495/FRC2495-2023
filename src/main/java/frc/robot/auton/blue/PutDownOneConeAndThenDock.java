package frc.robot.auton.blue;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.brake.BrakeSetEngaged;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.DockOnChargeStation;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;

// Can be used to place one cone in either starting position three or four
public class PutDownOneConeAndThenDock extends SequentialCommandGroup {

    public PutDownOneConeAndThenDock(){

        addCommands(
            new DropConeOnTopNodeAndShrink(),

            new DockOnChargeStation(),

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_DOCK_TO_OUTSIDE_COMMUNITY),
            // drives backward outside of community so we can get the points for moving out 

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_OUTSIDE_COMMUNITY_TO_BEFORE_DOCK),
            // drives forward just before charging station to get ready to dock

            new DockOnChargeStation(),

            new BrakeSetEngaged()
            // makes sure we stay on charge station
        
            
        );
    }

    
   

}