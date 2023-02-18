
package frc.robot.commands.rotator;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import frc.robot.util.*;
import frc.robot.commands.DoNothing;

/**
 *
 */
public class RotatorSafeRestWithStallDetection extends ConditionalCommand {

	public RotatorSafeRestWithStallDetection() {
		super(new RotatorRestWithStallDetection(), new DoNothing(), new ShoulderSafetyCheck());
	}

}
