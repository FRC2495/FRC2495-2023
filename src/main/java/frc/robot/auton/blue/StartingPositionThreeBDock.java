package frc.robot.auton.blue;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


import frc.robot.auton.common.DockOnChargeStation;

public class StartingPositionThreeBDock extends SequentialCommandGroup {

    public StartingPositionThreeBDock(){

        addCommands(
            new DockOnChargeStation() 
            
        );
        
    }


}