
package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class DrivetrainSetBrakeNeutralMode extends InstantCommand {

	public DrivetrainSetBrakeNeutralMode() {
		addRequirements(Robot.drivetrain);
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		System.out.println("DrivetrainSetBrakeNeutralMode: initialize");
		Robot.drivetrain.setBrakeNeutralMode();
	}

}
