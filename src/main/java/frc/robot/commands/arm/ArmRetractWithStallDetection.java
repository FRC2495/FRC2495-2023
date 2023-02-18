
package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
public class ArmRetractWithStallDetection extends CommandBase {

	public ArmRetractWithStallDetection() {
		addRequirements(Robot.arm);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("ArmRetractWithStallDetection: initialize");
		Robot.arm.retract();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !Robot.arm.tripleCheckMove() || Robot.arm.tripleCheckIfStalled();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("ArmRetractWithStallDetection: end");
		Robot.arm.stop(); // adjust if needed
	}
}
