/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.indicator;

import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class IndicatorTimedScrollRainbow extends WaitCommand {
	/**
	 * Add your docs here.
	 */
	public IndicatorTimedScrollRainbow(double timeout) {
		super(timeout);

		//setRunWhenDisabled(true); // allows running of command when robot is disabled
		
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//requires(Robot.indicator);
		addRequirements(Robot.indicator);
	}

	// This instant command can run disabled
	@Override
	public boolean runsWhenDisabled() {
		return true;
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("IndicatorTimedScrollRainbow: initialize");
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		Robot.indicator.updateRainbow();
	}

	// Called once after timeout
	@Override
	public void end(boolean interrupted) {
		System.out.println("IndicatorTimedScrollRainbow: end");
		//Robot.indicator.stop();
	}
}
