
package frc.robot.commands.shoulder;

import edu.wpi.first.wpilibj2.command.ConditionalCommand;

import frc.robot.util.*;
import frc.robot.commands.DoNothing;

/**
 *
 */
public class ShoulderSafeMoveFloorWithStallDetection extends ConditionalCommand {

	public ShoulderSafeMoveFloorWithStallDetection() {
		super(new ShoulderMoveFloorWithStallDetection(), new DoNothing(), new ArmAndRotatorSafetyCheckForShoulderAtFloor());
	}

}
