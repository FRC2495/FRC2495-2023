
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drivetrain.*;

/**
 *
 */
public class DrivetrainMoveToTop extends SequentialCommandGroup {

	public DrivetrainMoveToTop() {

		addCommands(
			new DrivetrainSetBrakeNeutralMode(),
			new DrivetrainMoveDistanceWithSteepDetection(60),
			new WaitCommand(1), // temp
			//new DrivetrainMoveDistanceLowSpeed(12),
			new DrivetrainMoveDistanceWithFlatDetection(12)
			//new Park()
			);
	} 
}
