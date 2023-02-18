
package frc.robot.commands.indicator;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class IndicatorSetYellow extends InstantCommand {

	public IndicatorSetYellow() {
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
		//System.out.println("IndicatorSetYellow: initialize");
		Robot.indicator.setYellow();
	}

}
