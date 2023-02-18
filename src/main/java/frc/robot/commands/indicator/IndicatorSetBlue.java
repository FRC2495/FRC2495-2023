
package frc.robot.commands.indicator;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class IndicatorSetBlue extends InstantCommand {

	public IndicatorSetBlue() {
		addRequirements(Robot.indicator);
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		//System.out.println("IndicatorSetBlue: initialize");
		Robot.indicator.setBlue();
	}

}
