package frc.robot.interfaces;

import edu.wpi.first.wpilibj2.command.Subsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public interface IArm extends Subsystem {
	// returns the state of the limit switch
	public boolean getLimitSwitchState();

	public boolean getReverseLimitSwitchState();
	
	// This method should be called to assess the progress of a move
	public boolean tripleCheckMove();

	public void extend();
	
	public void retract();
	
	public double getEncoderPosition();
	
	public boolean isMoving();
	
	public boolean isExtending();	

	public boolean isExtended();
	
	public boolean isRetracted();
	
	public boolean isMidway();

	public boolean isDangerous();

	public boolean isDangerousForShoulderAtFloor();

	// return if stalled
	public boolean isStalled();

	// checks if drivetrain might be stalled
	public boolean tripleCheckIfStalled();

	public void stay();	
		
	public void stop();

	// NOTE THAT THIS METHOD WILL IMPACT BOTH OPEN AND CLOSED LOOP MODES
	public void setNominalAndPeakOutputs(double peakOutput);

	// for debug purpose only
	public void joystickControl(Joystick joystick);

	public void gamepadControl(XboxController gamepad);
	
	public double getTarget();

	// MAKE SURE THAT YOU ARE NOT IN A CLOSED LOOP CONTROL MODE BEFORE CALLING THIS METHOD.
	// OTHERWISE THIS IS EQUIVALENT TO MOVING TO THE DISTANCE TO THE CURRENT ZERO IN REVERSE! 
	public void resetEncoder();
}
