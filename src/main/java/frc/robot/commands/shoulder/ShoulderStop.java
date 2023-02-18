
package frc.robot.commands.shoulder;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class ShoulderStop extends InstantCommand {

	public ShoulderStop() {
		addRequirements(Robot.shoulderControl);
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		System.out.println("ShoulderStop: initialize");
		Robot.shoulderControl.stop();
	}

}
