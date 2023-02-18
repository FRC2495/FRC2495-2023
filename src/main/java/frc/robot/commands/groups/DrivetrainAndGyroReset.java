
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//import frc.robot.commands.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.gyro.*;

/**
 *
 */
public class DrivetrainAndGyroReset extends SequentialCommandGroup {

	public DrivetrainAndGyroReset() {

		addCommands(
			new DrivetrainResetEncoders(),
			new GyroReset());
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}
}
