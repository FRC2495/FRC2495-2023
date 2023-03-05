package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.DropConeOnMiddleNodeAndShrink;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;
import frc.robot.auton.common.PickupConeFromFloor;
import frc.robot.auton.common.RightMoveFromConeNodeToConePickup;
import frc.robot.auton.common.RightMoveFromConePickupToConeNode;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionSixBTwoCones extends SequentialCommandGroup {

    public StartingPositionSixBTwoCones(){

        addCommands(

            new DropConeOnTopNodeAndShrink(),

            new RightMoveFromConeNodeToConePickup(), // TEST OUT (MAY BE WRONG)

            new PickupConeFromFloor(),

            new RightMoveFromConePickupToConeNode(), // TEST OUT (MAY BE WRONG)

            new DropConeOnMiddleNodeAndShrink()

        ); 
  
    }

    
   

}