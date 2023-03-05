package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


import frc.robot.auton.common.DropConeOnTopNodeAndShrink;
import frc.robot.auton.common.LeaveCommunity;

// Can be used to place one cone in either starting position three or four
public class PutDownOneConeAndLeaveCommunity extends SequentialCommandGroup {

    public PutDownOneConeAndLeaveCommunity(){

        addCommands(
            new DropConeOnTopNodeAndShrink(),

            new LeaveCommunity()
            
        );
    }

    
   

}