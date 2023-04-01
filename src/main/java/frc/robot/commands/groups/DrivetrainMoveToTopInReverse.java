
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.drivetrain.*;

/**
 *
 */
public class DrivetrainMoveToTopInReverse extends SequentialCommandGroup {

	public DrivetrainMoveToTopInReverse() {

		addCommands(
			new DrivetrainSetBrakeNeutralMode(),
			new DrivetrainMoveDistanceWithSteepDetection(-60),
			//new WaitCommand(1), // temp
			//new DrivetrainMoveDistanceLowSpeed(-25.65)
			//new DrivetrainMoveDistanceWithFlatDetection(-24)
			new DrivetrainMoveDistanceWithFlatDetectionAndJackDown(-37) //-37
			//new Park()
			);
	} 
}
