
package frc.robot.commands.shoulder;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import frc.robot.util.*;
import frc.robot.commands.DoNothing;

/**
 *
 */
public class ShoulderSafeMoveDown extends ConditionalCommand {

	public ShoulderSafeMoveDown() {
		super(new ShoulderMoveDown(), new DoNothing(), new ArmAndRotatorSafetyCheck());
	}

}
