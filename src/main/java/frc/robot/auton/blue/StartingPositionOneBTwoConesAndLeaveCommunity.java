package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.FinalBackup;
import frc.robot.auton.common.DropConeOnMiddleNode;
import frc.robot.auton.common.DropConeOnMiddleNodeAndShrink;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;
import frc.robot.auton.common.LeftMoveFromConeNodeToConePickup;
import frc.robot.auton.common.LeftMoveFromConePickupToConeNode;
import frc.robot.auton.common.PickupConeFromFloor;
import frc.robot.auton.common.ShrinkAndFinalBackup;
import frc.robot.auton.common.ShrinkAndLeftMoveFromConeNodeToConePickup;
import frc.robot.auton.common.ShrinkAndLeftMoveFromConePickupToConeNode;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneBTwoConesAndLeaveCommunity extends SequentialCommandGroup {

    public StartingPositionOneBTwoConesAndLeaveCommunity(){

        addCommands(

            new DropConeOnTopNodeAndShrink(),

            new ShrinkAndLeftMoveFromConeNodeToConePickup(),
            // testing

            new PickupConeFromFloor(),

            new ShrinkAndLeftMoveFromConePickupToConeNode(),
            // testing

            new DropConeOnMiddleNode(),

            new ShrinkAndFinalBackup()
            // testing

        ); 
  
    }

    
   

}