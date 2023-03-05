package frc.robot.auton.blue;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.DockOnChargeStation;
import frc.robot.commands.drivetrain.DrivetrainMoveDistanceWithStallDetection;

public class StartingPositionThreeOrFourBDockStep1 extends SequentialCommandGroup {

    public StartingPositionThreeOrFourBDockStep1(){

        addCommands(
            new DockOnChargeStation(),

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_DOCK_TO_OUTSIDE_COMMUNITY)
            // drives backward outside of community so we can get the points for moving out 
            
        );
        
    }


}