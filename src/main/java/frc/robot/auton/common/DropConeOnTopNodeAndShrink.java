package frc.robot.auton.common;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


  
public class DropConeOnTopNodeAndShrink extends SequentialCommandGroup{

    public DropConeOnTopNodeAndShrink() {

        addCommands(
            
            new DropConeOnTopNode(),

            new Shrink()

        ); 
    }
}


