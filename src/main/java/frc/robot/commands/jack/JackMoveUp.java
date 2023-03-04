
package frc.robot.commands.jack;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
public class JackMoveUp extends CommandBase {

	public JackMoveUp() {
		addRequirements(Robot.jack);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("JackMoveUp: initialize");
		Robot.jack.moveUp();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !Robot.jack.tripleCheckMove();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interupted) {
		System.out.println("JackMoveUp: end");
		Robot.jack.stay();  // we don't want to stop so we stay up...
	}
}
