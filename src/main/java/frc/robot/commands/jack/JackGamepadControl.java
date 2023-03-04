
package frc.robot.commands.jack;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;

/**
 *
 */
public class JackGamepadControl extends CommandBase {

	public JackGamepadControl() {
		addRequirements(Robot.jack);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("JackGamepadControl: initialize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		Robot.jack.gamepadControl(Robot.oi.getGamepad());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("JackGamepadControl: end");
		Robot.jack.stop();
	}
}
