package frc.robot.auton.blue;


import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.DockOnChargeStation;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;

// Can be used to place one cone in either starting position three or four
public class PutDownOneConeAndLeaveCommunityAndThenDockStep1 extends SequentialCommandGroup {

    public PutDownOneConeAndLeaveCommunityAndThenDockStep1(){

        addCommands(
            new DropConeOnTopNodeAndShrink(),

            new DockOnChargeStation(),

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_DOCK_TO_OUTSIDE_COMMUNITY)
            // drives backward outside of community so we can get the points for moving out 
        
            
        );
    }

    
   

}