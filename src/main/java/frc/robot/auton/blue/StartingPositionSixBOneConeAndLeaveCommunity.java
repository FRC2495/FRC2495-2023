package frc.robot.auton.blue;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.FinalBackup;
import frc.robot.auton.common.DropConeOnTopNodeAndShrink;

public class StartingPositionSixBOneConeAndLeaveCommunity extends SequentialCommandGroup {

    public StartingPositionSixBOneConeAndLeaveCommunity(){

        addCommands(

            new DropConeOnTopNodeAndShrink(),
            // drops cone on top node and brings arm into frame perimeter

            new FinalBackup()

        );
        
    }


}