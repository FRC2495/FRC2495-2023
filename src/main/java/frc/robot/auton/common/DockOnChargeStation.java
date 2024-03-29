package frc.robot.auton.common;

import frc.robot.auton.AutonConstants;
import frc.robot.commands.drivetrain.DrivetrainMoveDistanceWithStallDetection;
import frc.robot.commands.jack.JackMoveDownWithStallDetection;
import frc.robot.commands.jack.JackMoveUpWithStallDetection;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;


  
public class DockOnChargeStation extends SequentialCommandGroup{

    public DockOnChargeStation() {

        addCommands(
            
            //new JackMoveUpWithStallDetection(),
            
            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_START_OF_CHARGING_STATION_TO_DOCKED_AT_CHARGING_STATION)

            //new JackMoveDownWithStallDetection()

        ); 
    }
}


