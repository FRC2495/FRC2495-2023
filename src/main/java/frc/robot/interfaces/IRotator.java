package frc.robot.interfaces;

import edu.wpi.first.wpilibj2.command.Subsystem;

import edu.wpi.first.wpilibj.Joystick;

public interface IRotator extends Subsystem {
	// returns the state of the limit switch
	public boolean getLimitSwitchState();

	public boolean getReverseLimitSwitchState();
	
	// This method should be called to assess the progress of a move
	public boolean tripleCheckMove();

	public void move(int position);

	public void flip();

	public void sideway();
	
	public void rest();
	
	public double getEncoderPosition();
	
	public boolean isMoving();
	
	public boolean isFlipping();	

	public boolean isFlipped();
	
	public boolean isRested();
	
	public boolean isSideway();

	public boolean isDangerous();

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
	
	public double getTarget();

	// MAKE SURE THAT YOU ARE NOT IN A CLOSED LOOP CONTROL MODE BEFORE CALLING THIS METHOD.
	// OTHERWISE THIS IS EQUIVALENT TO MOVING TO THE DISTANCE TO THE CURRENT ZERO IN REVERSE! 
	public void resetEncoder();
}
