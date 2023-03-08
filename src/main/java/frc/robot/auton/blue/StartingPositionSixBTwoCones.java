package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.DropConeOnMiddleNodeAndShrink;
import frc.robot.auton.common.DropConeOnTopNode;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;
import frc.robot.auton.common.PickupConeFromFloor;
import frc.robot.auton.common.RightMoveFromConeNodeToConePickup;
import frc.robot.auton.common.RightMoveFromConePickupToConeNode;
import frc.robot.auton.common.ShrinkAndRightMoveFromConeNodeToConePickup;
import frc.robot.auton.common.ShrinkAndRightMoveFromConePickupToConeNode;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionSixBTwoCones extends SequentialCommandGroup {

    public StartingPositionSixBTwoCones(){

        addCommands(

            new DropConeOnTopNode(),

            new ShrinkAndRightMoveFromConeNodeToConePickup(), // TEST OUT (MAY BE WRONG)

            new PickupConeFromFloor(),

            new ShrinkAndRightMoveFromConePickupToConeNode(), // TEST OUT (MAY BE WRONG)

            new DropConeOnMiddleNodeAndShrink()

        ); 
  
    }

    
   

}