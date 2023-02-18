
package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;

/**
 *
 */
public class ArmStop extends InstantCommand {

	public ArmStop() {
		addRequirements(Robot.arm);
	}

	// Called once when this command runs
	@Override
	public void initialize() {
		System.out.println("ArmStop: initialize");
		Robot.arm.stop();
	}

}
