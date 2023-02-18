
package frc.robot.commands.rotator;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
public class RotatorJoystickControl extends CommandBase {

	public RotatorJoystickControl() {

		addRequirements(
			Robot.rotator,
			Robot.drivetrain); // this is needed so that the default drivetrain command does not run at the same time
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("RotatorJoystickControl: initialize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		Robot.rotator.joystickControl(Robot.oi.getLeftJoystick());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("RotatorJoystickControl: end");
		Robot.rotator.stop();
	}
}
