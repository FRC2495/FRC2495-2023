
package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
public class DrivetrainTurnAngleUsingPidControllerWithStallDetection extends CommandBase {

	private int m_angle;

	public DrivetrainTurnAngleUsingPidControllerWithStallDetection(int angle) {
		m_angle = angle;

		addRequirements(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("DrivetrainTurnAngleUsingPidControllerWithStallDetection: initialize");
		Robot.drivetrain.turnAngleUsingPidController(m_angle);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !Robot.drivetrain.tripleCheckTurnAngleUsingPidController() || Robot.drivetrain.tripleCheckIfStalled();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("DrivetrainTurnAngleUsingPidControllerWithStallDetection: end");
		Robot.drivetrain.stop();
	}
}
