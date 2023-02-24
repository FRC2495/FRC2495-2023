
package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import frc.robot.util.*;
import frc.robot.commands.DoNothing;

/**
 *
 */
public class ArmSafePickupExtendWithStallDetection extends ConditionalCommand {

	public ArmSafePickupExtendWithStallDetection() {
		super(new ArmPickupExtendWithStallDetection(), new DoNothing(), new ShoulderSafetyCheck());
	}

}
