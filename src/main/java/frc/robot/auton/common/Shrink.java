package frc.robot.auton.common;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;


  
public class Shrink extends SequentialCommandGroup{

    public Shrink() {

        addCommands(

            new ClawSetClosed(),
            
            new ArmRetractWithStallDetection(),

            new ShoulderSafeMoveDownWithStallDetection()

        ); 
    }
}


