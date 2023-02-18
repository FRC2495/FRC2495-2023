
package frc.robot.commands.rotator;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import frc.robot.util.*;
import frc.robot.commands.DoNothing;

/**
 *
 */
public class RotatorSafeSidewayWithStallDetection extends ConditionalCommand {

	public RotatorSafeSidewayWithStallDetection() {
		super(new RotatorSidewayWithStallDetection(), new DoNothing(), new ShoulderSafetyCheck());
	}

}
