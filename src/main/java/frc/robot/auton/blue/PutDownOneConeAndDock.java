package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.Dock;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;

// Can be used to place one cone in either starting position three or four
public class PutDownOneConeAndDock extends SequentialCommandGroup {

    public PutDownOneConeAndDock(){

        addCommands(
            new DropConeOnTopNodeAndShrink(),

            // maybe we have to move back a little bit before we can dock?

            new Dock()
            
        );
    }

    
   

}