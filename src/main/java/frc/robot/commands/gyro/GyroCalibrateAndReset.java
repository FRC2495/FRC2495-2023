
package frc.robot.commands.gyro;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.subsystems.*;

/**
 *
 */
public class GyroCalibrateAndReset extends SequentialCommandGroup {

	public GyroCalibrateAndReset() {

		addCommands(
			new GyroCalibrate(),
			new GyroReset());
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}
}
