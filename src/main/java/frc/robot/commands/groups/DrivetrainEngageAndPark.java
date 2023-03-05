
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.drivetrain.*;

/**
 *
 */
public class DrivetrainEngageAndPark extends SequentialCommandGroup {

	public DrivetrainEngageAndPark() {

		addCommands(
			new DrivetrainSetBrakeNeutralMode(),
			new DrivetrainEngageUsingAccelerometerPidControllerWithStallDetection(),
			new Park());
	} 
}
