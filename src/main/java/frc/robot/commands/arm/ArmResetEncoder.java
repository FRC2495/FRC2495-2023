
package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class ArmResetEncoder extends InstantCommand {

	public ArmResetEncoder() {
		addRequirements(Robot.arm);
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("ArmResetEncoder: initialize");
		Robot.arm.resetEncoder();
	}
}
