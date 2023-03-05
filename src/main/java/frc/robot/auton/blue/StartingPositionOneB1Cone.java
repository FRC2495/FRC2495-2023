package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.Backup;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneB1Cone extends SequentialCommandGroup {

    public StartingPositionOneB1Cone(){

        addCommands(

            new DropConeOnTopNodeAndShrink(),
            // drops cone on top node and brings arm into frame perimeter

            new Backup()
        ); 
  
    }

    
   

}