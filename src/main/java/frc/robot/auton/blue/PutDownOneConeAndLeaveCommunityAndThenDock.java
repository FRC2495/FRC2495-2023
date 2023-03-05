package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


import frc.robot.auton.common.Dock;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;
import frc.robot.auton.common.LeaveCommunity;

// Can be used to place one cone in either starting position three or four
public class PutDownOneConeAndLeaveCommunityAndThenDock extends SequentialCommandGroup {

    public PutDownOneConeAndLeaveCommunityAndThenDock(){

        addCommands(

            new DropConeOnTopNodeAndShrink(),

            // maybe we need to back up a little bit before we can dock?
            // new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_DOCK),

            new LeaveCommunity(),

            new Dock()
            
        );
    }

    
   

}