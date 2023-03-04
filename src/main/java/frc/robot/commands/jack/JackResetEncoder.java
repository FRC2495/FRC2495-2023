
package frc.robot.commands.jack;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class JackResetEncoder extends InstantCommand {

	public JackResetEncoder() {
		addRequirements(Robot.jack);
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("JackResetEncoder: initialize");
		Robot.jack.resetEncoder();
	}

}
