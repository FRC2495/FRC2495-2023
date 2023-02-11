/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.Robot;
//import frc.robot.commands.*;
//import frc.robot.commands.gearbox.*;
//import frc.robot.commands.hinge.*;
//import frc.robot.auton.blue.StartingPositionFiveB2Cu;
import frc.robot.auton.blue.StartingPositionTwoB1Cu1Co;
import frc.robot.auton.blue.StartingPositionOneOrTwoB1GP;
import frc.robot.auton.blue.StartingPositionSixB1Cu1Co;
import frc.robot.auton.blue.StartingPositionThreeBDock;
import frc.robot.auton.blue.StartingPositionThreeOrFourB1GpAndDock;
//import frc.robot.auton.blue.StartingPositionTwoB2Cu;

public class CustomAuton extends SequentialCommandGroup {

	String gamePiece;
	String startPosition;
	String mainTarget;
	String cameraOption;
	String sonarOption;
	String autonOption;

	/**
     * Add your docs here.
     * 
     * @param gamePiece_in game piece
	 * @param startPosition_in starting position
     * @param mainTarget_in    main target
     * @param cameraOption_in  camera option
     * @param sonarOption_in   sonar option
    * @param autonOption_in   auton option
    */
    public CustomAuton(String gamePiece_in, String startPosition_in, String mainTarget_in, String cameraOption_in,
            String sonarOption_in, String autonOption_in) {

		gamePiece = gamePiece_in;
		startPosition = startPosition_in;
		mainTarget = mainTarget_in;
		cameraOption = cameraOption_in;
		sonarOption = sonarOption_in;
		autonOption = autonOption_in;

		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.

		//addSequential(new GearboxSetGearLow()); // forces low gear just in case

		//addSequential(new HingeMoveUp()); // forces hinge back up just in case

		switch (startPosition) {
			case Robot.START_POSITION_1:
				switch (mainTarget) {
					case Robot.MAIN_TARGET_CHARGING_STATION:
						//TODO
						break;
					case Robot.MAIN_TARGET_CONE_NODE:
						addCommands(new StartingPositionOneOrTwoB1GP());
						break;
					case Robot.MAIN_TARGET_CUBE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_TWO_CUBE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_BOTH_NODE:
						addCommands(new StartingPositionTwoB1Cu1Co());
						break;
					case Robot.MAIN_TARGET_NOWHERE:
						//TODO
						break;
					default:
						// nothing
						break;
				}
				break;

			case Robot.START_POSITION_2:
				switch (mainTarget) {
					case Robot.MAIN_TARGET_CHARGING_STATION:
						//TODO
						break;
					case Robot.MAIN_TARGET_CONE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_CUBE_NODE:
						addCommands(new StartingPositionOneOrTwoB1GP());
						break;
					case Robot.MAIN_TARGET_TWO_CUBE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_BOTH_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_NOWHERE:
						//TODO
						break;	
					default:
						// nothing
						break;
				}
				break;

			case Robot.START_POSITION_3:
				switch (mainTarget) {
					case Robot.MAIN_TARGET_CHARGING_STATION:
						addCommands(new StartingPositionThreeBDock());
						break;
					case Robot.MAIN_TARGET_CONE_NODE:
						addCommands(new StartingPositionThreeOrFourB1GpAndDock());
						break;
					case Robot.MAIN_TARGET_CUBE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_TWO_CUBE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_BOTH_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_NOWHERE:
						//TODO
						break;
					default:
						// nothing
						break;
				}
				break;

				case Robot.START_POSITION_4:
				switch (mainTarget) {
					case Robot.MAIN_TARGET_CHARGING_STATION:
						//TODO
						break;
					case Robot.MAIN_TARGET_CONE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_CUBE_NODE:
						addCommands(new StartingPositionThreeOrFourB1GpAndDock());
						break;
					case Robot.MAIN_TARGET_TWO_CUBE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_BOTH_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_NOWHERE:
						//TODO
						break;
					default:
						// nothing
						break;
				}
				break;

				case Robot.START_POSITION_5:
				switch (mainTarget) {
					case Robot.MAIN_TARGET_CHARGING_STATION:
						//TODO
						break;
					case Robot.MAIN_TARGET_CONE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_CUBE_NODE:
						addCommands(new StartingPositionOneOrTwoB1GP());
						break;
					case Robot.MAIN_TARGET_TWO_CUBE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_BOTH_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_NOWHERE:
						//TODO
						break;
					default:
						// nothing
						break;
				}
				break;

				case Robot.START_POSITION_6:
				switch (mainTarget) {
					case Robot.MAIN_TARGET_CHARGING_STATION:
						//TODO
						break;
					case Robot.MAIN_TARGET_CONE_NODE:
						addCommands(new StartingPositionOneOrTwoB1GP());
						break;
					case Robot.MAIN_TARGET_CUBE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_TWO_CUBE_NODE:
						//TODO
						break;
					case Robot.MAIN_TARGET_BOTH_NODE:
						addCommands(new StartingPositionSixB1Cu1Co());
						break;
					case Robot.MAIN_TARGET_NOWHERE:
						//TODO
						break;
					default:
						// nothing
						break;
				}
				break;

            default:{
                //nothing
                break;
            }
		} // end switch
	}
}