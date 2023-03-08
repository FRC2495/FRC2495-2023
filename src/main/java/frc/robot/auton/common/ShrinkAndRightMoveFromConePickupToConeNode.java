package frc.robot.auton.common;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class ShrinkAndRightMoveFromConePickupToConeNode extends ParallelCommandGroup {

    public ShrinkAndRightMoveFromConePickupToConeNode(){

        addCommands(

            new Shrink(),

            new RightMoveFromConePickupToConeNode()

        );



    }

}
