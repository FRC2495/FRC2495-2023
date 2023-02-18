
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//import frc.robot.commands.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.rotator.*;
//import frc.robot.commands.grasper.*;
//import frc.robot.commands.feeder.*;
//import frc.robot.commands.shooter.*;


/**
 *
 */
public class AlmostEverythingStop extends SequentialCommandGroup {

	public AlmostEverythingStop() {

		addCommands(
			new ShoulderStop(),
			new ArmStop(),
			new RotatorStop());
	} 
}
