
package frc.robot.commands.rotator;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import frc.robot.util.*;
import frc.robot.commands.DoNothing;

/**
 *
 */
public class RotatorSafeFlipWithStallDetection extends ConditionalCommand {

	public RotatorSafeFlipWithStallDetection() {
		super(new RotatorFlipWithStallDetection(), new DoNothing(), new ShoulderSafetyCheck());
	}

}
