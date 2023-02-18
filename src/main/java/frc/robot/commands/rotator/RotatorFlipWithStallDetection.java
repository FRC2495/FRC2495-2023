
package frc.robot.commands.rotator;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
/*public*/ class RotatorFlipWithStallDetection extends CommandBase {

	public RotatorFlipWithStallDetection() {
		addRequirements(Robot.rotator);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("RotatorFlipWithStallDetection: initialize");
		Robot.rotator.flip();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !Robot.rotator.tripleCheckMove() || Robot.rotator.tripleCheckIfStalled();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("RotatorFlipWithStallDetection: end");
		Robot.rotator.stop(); // adjust if neded
	}
}
