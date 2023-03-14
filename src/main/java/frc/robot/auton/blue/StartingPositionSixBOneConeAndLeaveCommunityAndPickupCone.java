package frc.robot.auton.blue;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.FinalBackup;
import frc.robot.auton.common.PickupConeFromFloor;
import frc.robot.auton.common.ShrinkAndFinalBackup;
import frc.robot.auton.common.ShrinkAndRightFinalBackup;
import frc.robot.auton.common.DropConeOnTopNode;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;

public class StartingPositionSixBOneConeAndLeaveCommunityAndPickupCone extends SequentialCommandGroup {

    public StartingPositionSixBOneConeAndLeaveCommunityAndPickupCone(){

        addCommands(

            new DropConeOnTopNode(),
            // drops cone on top node and brings arm into frame perimeter

            new ShrinkAndRightFinalBackup(),

            new PickupConeFromFloor()

        );
        
    }


}