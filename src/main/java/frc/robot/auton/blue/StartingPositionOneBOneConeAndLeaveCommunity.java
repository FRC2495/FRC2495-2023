package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.FinalBackup;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneBOneConeAndLeaveCommunity extends SequentialCommandGroup {

    public StartingPositionOneBOneConeAndLeaveCommunity(){

        addCommands(

            new DropConeOnTopNodeAndShrink(),
            // drops cone on top node

            new FinalBackup()
        ); 
  
    }

    
   

}