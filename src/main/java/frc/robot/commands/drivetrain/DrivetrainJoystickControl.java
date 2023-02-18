
package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;
import frc.robot.ControllerBase.JoystickButtons;

/**
 *
 */
public class DrivetrainJoystickControl extends CommandBase {

	public DrivetrainJoystickControl() {
		addRequirements(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("DrivetrainJoystickControl: initialize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		Robot.drivetrain.joystickControl(Robot.oi.getLeftJoystick(), Robot.oi.getRightJoystick(), 
			Robot.oi.getLeftJoystick().getRawButton(JoystickButtons.BTN1) || Robot.oi.getRightJoystick().getRawButton(JoystickButtons.BTN1));
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false; // we run forever (unless interrupted)
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("DrivetrainJoystickControl: end");
		Robot.drivetrain.stop();
	}
}
