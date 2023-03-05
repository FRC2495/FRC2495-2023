package frc.robot.auton.blue;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneB1ConeStep1 extends SequentialCommandGroup {

    public StartingPositionOneB1ConeStep1(){

        addCommands(

            new DropConeOnTopNodeAndShrink()
            // drops cone on top node and brings arm into frame perimeter

        ); 
  
    }

    
   

}