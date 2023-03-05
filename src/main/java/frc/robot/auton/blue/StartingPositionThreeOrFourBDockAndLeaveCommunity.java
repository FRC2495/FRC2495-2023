package frc.robot.auton.blue;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.Dock;
import frc.robot.auton.common.LeaveCommunity;

public class StartingPositionThreeOrFourBDockAndLeaveCommunity extends SequentialCommandGroup {

    public StartingPositionThreeOrFourBDockAndLeaveCommunity(){

        addCommands(

            // maybe we have to move back a little bit before we can dock?
            // new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_DOCK),

            new LeaveCommunity(),

            // maybe we have to move back a little bit before we can dock?
            // new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_OUTSIDE_COMMUNITY_TO_BEFORE_DOCK),

            new Dock()

        );
        
    }


}