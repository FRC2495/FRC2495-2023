
package frc.robot.commands.shoulder;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
public class ShoulderMoveUp extends CommandBase {

	public ShoulderMoveUp() {
		addRequirements(Robot.shoulderControl);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("ShoulderMoveUp: initialize");
		Robot.shoulderControl.moveUp();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !Robot.shoulderControl.tripleCheckMove();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interupted) {
		System.out.println("ShoulderMoveUp: end");
		Robot.shoulderControl.stay();  // we don't want to stop so we stay up...
	}
}
