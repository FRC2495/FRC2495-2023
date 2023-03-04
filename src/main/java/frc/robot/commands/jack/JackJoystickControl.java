
package frc.robot.commands.jack;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
public class JackJoystickControl extends CommandBase {

	public JackJoystickControl() {

		addRequirements(
			Robot.jack,
			Robot.drivetrain); // this is needed so that the default drivetrain command does not run at the same time
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("JackJoystickControl: initialize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		Robot.jack.joystickControl(Robot.oi.getLeftJoystick());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interupted) {
		System.out.println("JackJoystickControl: end");
		Robot.jack.stop();
	}
}
