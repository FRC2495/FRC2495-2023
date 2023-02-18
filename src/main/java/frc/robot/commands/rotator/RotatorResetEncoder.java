
package frc.robot.commands.rotator;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class RotatorResetEncoder extends InstantCommand {

	public RotatorResetEncoder() {

		addRequirements(Robot.rotator);
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("RotatorResetEncoder: initialize");
		Robot.rotator.resetEncoder();
	}

}
