
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.drivetrain.*;
import frc.robot.commands.brake.*;


/**
 *
 */
public class Park extends SequentialCommandGroup {

	public Park() {

		addCommands(
			new DrivetrainSetBrakeNeutralMode(),
			new BrakeSetEngaged());
	} 
}
