
package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class DrivetrainResetEncoders extends InstantCommand {

	public DrivetrainResetEncoders() {
		addRequirements(Robot.drivetrain);
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		System.out.println("DrivetrainResetEncoders: initialize");
		Robot.drivetrain.resetEncoders();
	}

}
