package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.DropConeOnMiddleNodeAndShrink;
import frc.robot.auton.common.DropConeOnTopNode;
import frc.robot.auton.common.PickupConeFromFloor;
import frc.robot.auton.common.ShrinkAndLeftMoveFromConeNodeToConePickup;
import frc.robot.auton.common.ShrinkAndLeftMoveFromConePickupToConeNode;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneBTwoCones extends SequentialCommandGroup {

    public StartingPositionOneBTwoCones(){

        addCommands(

            new DropConeOnTopNode(),

            new ShrinkAndLeftMoveFromConeNodeToConePickup(),
            // testing

            new PickupConeFromFloor(),

            new ShrinkAndLeftMoveFromConePickupToConeNode(),
            //

            new DropConeOnMiddleNodeAndShrink()

        ); 
  
    }

    
   

}