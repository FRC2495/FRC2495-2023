
package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
public class DrivetrainMoveDistance extends CommandBase {

	private double m_distance;

	public DrivetrainMoveDistance(double distance) {
		m_distance = distance;

		addRequirements(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("DrivetrainMoveDistance: initialize");
		Robot.drivetrain.moveDistance(m_distance);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !Robot.drivetrain.tripleCheckMoveDistance();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("DrivetrainMoveDistance: end");
		Robot.drivetrain.stop();
	}
}
