
package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;
import frc.robot.subsystems.Gearbox;
import frc.robot.subsystems.Gearbox.Gear;

/**
 *
 */
public class DrivetrainEngageUsingAccelerometerPidController extends CommandBase {

	Gear prevSetting;

	public DrivetrainEngageUsingAccelerometerPidController() {
		addRequirements(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("DrivetrainEngageUsingAccelerometerPidController: initialize");
		prevSetting = Robot.gearbox.getGear(); //Saves previous gear setting

		Robot.gearbox.setGear(Gearbox.Gear.LOW);
		Robot.drivetrain.engageUsingAccelerometerPidController();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !Robot.drivetrain.tripleCheckEngageUsingAccelerometerPidController();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("DrivetrainEngageUsingAccelerometerPidController: end");
		Robot.drivetrain.stop();
		Robot.gearbox.setGear(prevSetting);
	}
}