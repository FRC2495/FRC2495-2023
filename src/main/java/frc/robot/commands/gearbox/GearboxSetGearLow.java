/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.gearbox;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.Robot;
import frc.robot.subsystems.Gearbox.Gear;

// To set low gear
public class GearboxSetGearLow extends InstantCommand {

	public GearboxSetGearLow() {
		addRequirements(Robot.gearbox);
	}

	// Called once when the command executes
	@Override
	public void initialize() {
		System.out.println("GearboxSetGearLow: initialize ");
		Robot.gearbox.setGear(Gear.LOW); // Making the piston go down
	}

}