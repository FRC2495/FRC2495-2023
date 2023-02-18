
package frc.robot.commands.shoulder;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class ShoulderResetEncoder extends InstantCommand {

	public ShoulderResetEncoder() {
		addRequirements(Robot.shoulderControl);
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("ShoulderResetEncoder: initialize");
		Robot.shoulderControl.resetEncoder();
	}

}
