package frc.robot.auton.blue;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.auton.common.Dock;
import frc.robot.auton.common.LeaveCommunity;

public class StartingPositionThreeOrFourBDock extends SequentialCommandGroup {

    public StartingPositionThreeOrFourBDock(){

        addCommands(
            new LeaveCommunity(),

            new Dock()

        );
        
    }


}