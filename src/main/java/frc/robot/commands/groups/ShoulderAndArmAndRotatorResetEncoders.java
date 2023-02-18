
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//import frc.robot.commands.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.rotator.*;

/**
 *
 */
public class ShoulderAndArmAndRotatorResetEncoders extends SequentialCommandGroup {

	public ShoulderAndArmAndRotatorResetEncoders() {

		addCommands(
			new ShoulderResetEncoder(),
			new ArmResetEncoder(),
			new RotatorResetEncoder());
	} 

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

}
