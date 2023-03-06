package frc.robot.auton.common;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;


  
public class DropConeOnTopNode extends SequentialCommandGroup{

    public DropConeOnTopNode() {

        addCommands(
            
            new ShoulderMoveUpWithStallDetection(),

            new ArmSafeExtendWithStallDetection(),

            new ClawSetOpen(),

            new WaitCommand(.3)

        ); 
    }
}


