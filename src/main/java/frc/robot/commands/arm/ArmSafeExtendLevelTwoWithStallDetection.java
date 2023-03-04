
package frc.robot.commands.arm;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import frc.robot.util.*;
import frc.robot.commands.DoNothing;

/**
 *
 */
public class ArmSafeExtendLevelTwoWithStallDetection extends ConditionalCommand {

	public ArmSafeExtendLevelTwoWithStallDetection() {
		super(new ArmExtendLevelTwoWithStallDetection(), new DoNothing(), new ShoulderSafetyCheck());
	}

}
