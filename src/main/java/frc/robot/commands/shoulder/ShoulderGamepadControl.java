
package frc.robot.commands.shoulder;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
public class ShoulderGamepadControl extends CommandBase {

	public ShoulderGamepadControl() {
		addRequirements(Robot.shoulder);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("ShoulderGamepadControl: initialize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		Robot.shoulder.gamepadControl(Robot.oi.getGamepad());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("ShoulderGamepadControl: end");
		Robot.shoulder.stop();
	}
}
