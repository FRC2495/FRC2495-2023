/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.InstantCommand;

//import frc.robot.Robot;
//import frc.robot.subsystems.Gearbox.Gear;

// To set low gear
public class ClawSetClosed extends InstantCommand {

	public ClawSetClosed() { //true is up, false is down
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(Robot.claw); // While using this command no other command can use it 
	}

	// Called once when the command executes
	@Override
	public void initialize() {
		System.out.println("ClawSetClosed: initialize ");
		//Robot.claw.setPosition(Position.CLOSED); // Making the piston go down
	}

}