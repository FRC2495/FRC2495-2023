
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import frc.robot.commands.drivetrain.*;
import frc.robot.commands.jack.*;

/**
 *
 */
public class DrivetrainMoveDistanceWithFlatDetectionAndJackDown extends ParallelCommandGroup {

	public DrivetrainMoveDistanceWithFlatDetectionAndJackDown(double distance) {

		addCommands(

            new DrivetrainMoveDistanceWithFlatDetection(distance)

            //new JackMoveDownWithStallDetection()

        );
        
	} 
}
