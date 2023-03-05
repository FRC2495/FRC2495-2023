package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.Backup;
import frc.robot.auton.common.DropConeOnMiddleNodeAndShrink;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;
import frc.robot.auton.common.LeftMoveFromConeNodeToConePickup;
import frc.robot.auton.common.LeftMoveFromConePickupToConeNode;
import frc.robot.auton.common.PickupConeFromFloor;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneBTwoConesAndLeaveCommunity extends SequentialCommandGroup {

    public StartingPositionOneBTwoConesAndLeaveCommunity(){

        addCommands(

            new DropConeOnTopNodeAndShrink(),

            new LeftMoveFromConeNodeToConePickup(),

            new PickupConeFromFloor(),

            new LeftMoveFromConePickupToConeNode(),

            new DropConeOnMiddleNodeAndShrink(),

            new Backup()

        ); 
  
    }

    
   

}