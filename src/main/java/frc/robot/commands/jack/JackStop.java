
package frc.robot.commands.jack;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class JackStop extends InstantCommand {

	public JackStop() {
		addRequirements(Robot.jack);
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		System.out.println("JackStop: initialize");
		Robot.jack.stop();
	}

}
