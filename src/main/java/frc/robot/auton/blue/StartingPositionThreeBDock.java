package frc.robot.auton.blue;

import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.auton.AutonConstants;

public class StartingPositionThreeBDock extends SequentialCommandGroup {

    public StartingPositionThreeBDock(){

        addCommands(

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_CUBE_NODE_TO_CHARGING_STATION));
            // moves from cube node to charging station

            // rubber block mechanic

  
    }

    
   

}