package frc.robot.auton.common;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;


  
public class DropConeOnMiddleNode extends SequentialCommandGroup{

    public DropConeOnMiddleNode() {

        addCommands(
            
            new ShoulderMoveLevelTwoWithStallDetection(),

            new ArmSafeExtendLevelTwoWithStallDetection(),

            new ClawSetOpen(),

            new WaitCommand(1)

        ); 
    }
}


