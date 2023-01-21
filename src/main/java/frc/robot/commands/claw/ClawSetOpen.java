/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.claw;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;
import frc.robot.subsystems.Claw.Position;

// To set claw open
public class ClawSetOpen extends InstantCommand {
	public ClawSetOpen() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(Robot.claw); //
		addRequirements(Robot.claw);
	}

	// Called once when the command executes
	@Override
	public void initialize() {
		System.out.println("ClawSetOpen: initialize");
		Robot.claw.setPosition(Position.OPEN);
	}

}