package frc.robot.auton.common;


import frc.robot.commands.brake.BrakeSetEngaged;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


import frc.robot.auton.AutonConstants;


// Can be used to place one cone in either starting position three or four
public class Dock extends SequentialCommandGroup {

    public Dock(){

        addCommands(
    
            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_OUTSIDE_COMMUNITY_TO_BEFORE_DOCK),
            // drives forward just before charging station to get ready to dock

            new DockOnChargeStation(),

            new BrakeSetEngaged()
            // makes sure we stay on charge station
            
        );
    }

    
   

}