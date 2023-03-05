package frc.robot.auton.blue;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.DockOnChargeStation;
import frc.robot.commands.brake.BrakeSetEngaged;
import frc.robot.commands.drivetrain.DrivetrainMoveDistanceWithStallDetection;

public class StartingPositionThreeXOXrFourBDock extends SequentialCommandGroup {

    public StartingPositionThreeXOXrFourBDock(){

        addCommands(
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