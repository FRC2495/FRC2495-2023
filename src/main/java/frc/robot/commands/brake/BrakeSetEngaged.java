/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.brake;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;
import frc.robot.subsystems.Brake.Position;

// To set brake closed
public class BrakeSetEngaged extends InstantCommand {

	public BrakeSetEngaged() { //true is up, false is down
		addRequirements(Robot.brake);
	}

	// Called once when the command executes
	@Override
	public void initialize() {
		System.out.println("BrakeSetEngaged: initialize ");
		Robot.brake.setPosition(Position.ENGAGED); // Making the piston go down
	}

}