
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

//import frc.robot.commands.drivetrain.*;
import frc.robot.commands.jack.*;

/**
 *
 */
public class DrivetrainEngageInReverse extends SequentialCommandGroup {

	public DrivetrainEngageInReverse() {

		addCommands(

			//new JackMoveUpWithStallDetection(),

            new DrivetrainMoveToTopInReverse()

            //new JackMoveDownWithStallDetection()

        );
        
	} 
}
