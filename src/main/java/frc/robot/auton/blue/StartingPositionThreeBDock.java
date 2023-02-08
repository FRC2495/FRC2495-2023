package frc.robot.auton.blue;

import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.auton.AutonConstants;

public class StartingPositionThreeBDock extends SequentialCommandGroup {

    public StartingPositionThreeBDock(){

        addCommands(

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_LEFT_TURNING_TO_CUBE_PICKUP));
            // moves from turning point to cube pickup 

            // rubber block mechanic

  
    }

    
   

}