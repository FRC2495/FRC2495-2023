
package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class DrivetrainStop extends InstantCommand {

	public DrivetrainStop() {
		addRequirements(Robot.drivetrain);
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		System.out.println("DrivetrainResetEncoders: stop");
		Robot.drivetrain.stop();
	}

}
