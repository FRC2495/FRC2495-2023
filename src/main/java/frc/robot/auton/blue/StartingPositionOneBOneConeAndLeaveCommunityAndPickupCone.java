package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.FinalBackup;
import frc.robot.auton.common.PickupConeFromFloor;
import frc.robot.auton.common.SecondPickupConeFromFloor;
import frc.robot.auton.common.ShrinkAndFinalBackup;
import frc.robot.auton.common.DropConeOnTopNode;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneBOneConeAndLeaveCommunityAndPickupCone extends SequentialCommandGroup {

    public StartingPositionOneBOneConeAndLeaveCommunityAndPickupCone(){

        addCommands(

            new DropConeOnTopNode(),
            // drops cone on top node

            new ShrinkAndFinalBackup(),
            // brings arm into frame perimeter and moves backward

            new SecondPickupConeFromFloor()
            // picks up cone to get ready for teleop

            
        ); 
  
    }

    
   

}