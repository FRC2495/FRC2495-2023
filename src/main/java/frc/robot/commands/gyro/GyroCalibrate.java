
package frc.robot.commands.gyro;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class GyroCalibrate extends InstantCommand {

	public GyroCalibrate() {
		// gyro only supports instant commands, so no need to reserve it
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		System.out.println("GyroCalibrate: initialize");
		Robot.gyro.calibrate();
		Robot.setGyroHasBeenManuallyCalibratedAtLeastOnce(true);
	}

}
