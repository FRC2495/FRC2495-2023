package frc.robot.auton.blue;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.Dock;

public class StartingPositionThreeOrFourBDock extends SequentialCommandGroup {

    public StartingPositionThreeOrFourBDock(){

        addCommands(

            // maybe we have to move back a little bit before we can dock?
            // new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_DOCK),

            new Dock()

        );
        
    }


}