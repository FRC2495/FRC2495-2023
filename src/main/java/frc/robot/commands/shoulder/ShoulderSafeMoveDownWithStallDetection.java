
package frc.robot.commands.shoulder;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import frc.robot.util.*;
import frc.robot.commands.DoNothing;

/**
 *
 */
public class ShoulderSafeMoveDownWithStallDetection extends ConditionalCommand {

	public ShoulderSafeMoveDownWithStallDetection() {
		super(new ShoulderMoveDownWithStallDetection(), new DoNothing(), new ArmAndRotatorSafetyCheck());
	}

}
