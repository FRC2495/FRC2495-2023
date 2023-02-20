
package frc.robot.commands.shoulder;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import frc.robot.util.*;
import frc.robot.commands.DoNothing;

/**
 *
 */
public class ShoulderSafeMoveFloor extends ConditionalCommand {

	public ShoulderSafeMoveFloor() {
		super(new ShoulderMoveFloor(), new DoNothing(), new ArmAndRotatorSafetyCheck());
	}

}
