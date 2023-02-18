
package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.Robot;
//import frc.robot.subsystems.Gearbox.Gear;
//import frc.robot.subsystems.Gearbox;

/**
 *
 */
public class DrivetrainTurnAngleUsingPidController extends CommandBase {

	private int m_angle;
	//Gear prevSetting;

	public DrivetrainTurnAngleUsingPidController(int angle) {
		m_angle = angle;

		addRequirements(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	@Override
	public void initialize() {
		System.out.println("DrivetrainTurnAngleUsingPidController: initialize");
		//prevSetting = Robot.gearbox.getGear(); //Saves previous gear setting

		//Robot.gearbox.setGear(Gearbox.Gear.LOW);
		Robot.drivetrain.turnAngleUsingPidController(m_angle);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	public void execute() {
		// nothing
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	public boolean isFinished() {
		return !Robot.drivetrain.tripleCheckTurnAngleUsingPidController();
	}

	// Called once after isFinished returns true
	@Override
	public void end(boolean interrupted) {
		System.out.println("DrivetrainTurnAngleUsingPidController: end");
		Robot.drivetrain.stop();
		//Robot.gearbox.setGear(prevSetting);
	}
}
