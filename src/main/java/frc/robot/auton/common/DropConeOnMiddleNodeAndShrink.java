package frc.robot.auton.common;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


  
public class DropConeOnMiddleNodeAndShrink extends SequentialCommandGroup{

    public DropConeOnMiddleNodeAndShrink() {

        addCommands(
            
            new DropConeOnMiddleNode(),

            new Shrink()

        ); 
    }
}


