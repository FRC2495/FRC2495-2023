
package frc.robot.commands.gamepad;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;
import frc.robot.ControllerBase;

/**
 *
 */
public class GamepadRumble extends InstantCommand {

	private boolean m_rumble;

	public GamepadRumble(boolean rumble) {

		m_rumble = rumble;

		// ControllerBase only supports instant commands, so no need to reserve it
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		
		System.out.println("GamepadRumble: initialize");
		ControllerBase.rumble(m_rumble, Robot.oi.getGamepad());
	}

}
