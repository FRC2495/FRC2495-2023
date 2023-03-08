package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.FinalBackup;
import frc.robot.auton.common.DropConeOnMiddleNode;
import frc.robot.auton.common.DropConeOnMiddleNodeAndShrink;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;
import frc.robot.auton.common.PickupConeFromFloor;
import frc.robot.auton.common.RightMoveFromConeNodeToConePickup;
import frc.robot.auton.common.RightMoveFromConePickupToConeNode;
import frc.robot.auton.common.ShrinkAndFinalBackup;
import frc.robot.auton.common.ShrinkAndRightMoveFromConeNodeToConePickup;
import frc.robot.auton.common.ShrinkAndRightMoveFromConePickupToConeNode;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionSixBTwoConesAndLeaveCommunity extends SequentialCommandGroup {

    public StartingPositionSixBTwoConesAndLeaveCommunity(){

        addCommands(

            new DropConeOnTopNodeAndShrink(),

            new ShrinkAndRightMoveFromConeNodeToConePickup(), // TEST OUT (MAY BE WRONG)

            new PickupConeFromFloor(),

            new ShrinkAndRightMoveFromConePickupToConeNode(), // TEST OUT (MAY BE WRONG)

            new DropConeOnMiddleNode(),

            new ShrinkAndFinalBackup()
            // testing

        ); 
  
    }

    
   

}