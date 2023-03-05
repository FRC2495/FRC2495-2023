package frc.robot.auton.common;


import frc.robot.commands.brake.BrakeSetEngaged;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.groups.Park;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


import frc.robot.auton.AutonConstants;


// Can be used to place one cone in either starting position three or four
public class Dock extends SequentialCommandGroup {

    public Dock(){

        addCommands(

            new DockOnChargeStation(),

            new Park()
            // makes sure we stay on charge station
            
        );
    }

    
   

}