
package frc.robot.commands.rotator;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class RotatorStop extends InstantCommand {

	public RotatorStop() {
		addRequirements(Robot.rotator);
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		System.out.println("rotatorStop: initialize");
		Robot.rotator.stop();
	}

}
