
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;

/**
 * Add your docs here.
 */
public class DoNothing extends InstantCommand {
	/**
	 * Add your docs here.
	 */
	public DoNothing() {
		super();
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called once when the command executes
	@Override
	public void initialize() {
		System.out.println("DoNothing: initialize");
	}

}