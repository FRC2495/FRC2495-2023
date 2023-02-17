package frc.robot.interfaces;

import edu.wpi.first.wpilibj.Joystick;

public interface IRotator {
	// returns the state of the limit switch
	public boolean getLimitSwitchState();
	
	// This method should be called to assess the progress of a move
	public boolean tripleCheckMove();

	public void flip();

	public void sideway();
	
	public void rest();
	
	public double getEncoderPosition();
	
	public boolean isMoving();
	
	public boolean isFlipping();	

	public boolean isFlipped();
	
	public boolean isRested();
	
	public boolean isSideway();

	// return if stalled
	public boolean isStalled();

	public void stay();	
		
	public void stop();

	// NOTE THAT THIS METHOD WILL IMPACT BOTH OPEN AND CLOSED LOOP MODES
	public void setNominalAndPeakOutputs(double peakOutput);

	// for debug purpose only
	public void joystickControl(Joystick joystick);
	
	public double getTarget();
}
