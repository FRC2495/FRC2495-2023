
package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import frc.robot.util.*;
import frc.robot.commands.DoNothing;

/**
 *
 */
public class ArmSafeExtendWithStallDetection extends ConditionalCommand {

	public ArmSafeExtendWithStallDetection() {
		super(new ArmExtendWithStallDetection(), new DoNothing(), new ShoulderSafetyCheck());
	}

}
