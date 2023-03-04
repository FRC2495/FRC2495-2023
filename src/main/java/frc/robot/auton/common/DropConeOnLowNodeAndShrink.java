package frc.robot.auton.common;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


  
public class DropConeOnLowNodeAndShrink extends SequentialCommandGroup{

    public DropConeOnLowNodeAndShrink() {

        addCommands(
            
            new DropConeOnLowNode(),

            new Shrink()

        ); 
    }
}


