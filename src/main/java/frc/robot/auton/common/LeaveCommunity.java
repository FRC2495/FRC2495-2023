package frc.robot.auton.common;


import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


import frc.robot.auton.AutonConstants;

// Can be used to place one cone in either starting position three or four
public class LeaveCommunity extends SequentialCommandGroup {

    public LeaveCommunity(){

        addCommands(
            
            new DockOnChargeStation(),

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_DOCK_TO_OUTSIDE_COMMUNITY),
            // drives backward outside of community so we can get the points for moving out 

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_OUTSIDE_COMMUNITY_TO_BEFORE_DOCK)
            // drives forward just before charging station to get ready to dock
        
            
        );
    }

    
   

}