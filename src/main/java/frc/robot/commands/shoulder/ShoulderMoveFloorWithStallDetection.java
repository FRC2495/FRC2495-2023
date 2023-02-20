
package frc.robot.commands.shoulder;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
/*public*/ class ShoulderMoveFloorWithStallDetection extends CommandBase {

	public ShoulderMoveFloorWithStallDetection() {
		addRequirements(Robot.shoulder);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("ShoulderMoveFloorWithStallDetection: initialize");
		Robot.shoulder.moveFloor();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !Robot.shoulder.tripleCheckMove() || Robot.shoulder.tripleCheckIfStalled();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interupted) {
		System.out.println("ShoulderMoveFloorWithStallDetection: end");
		Robot.shoulder.stay();
	}
}
