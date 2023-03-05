
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.commands.drivetrain.*;
import frc.robot.commands.brake.*;


/**
 *
 */
public class Drive extends SequentialCommandGroup {

	public Drive() {

		addCommands(
			new BrakeSetReleased(),
			new DrivetrainSetCoastNeutralMode());
			
	} 
}
