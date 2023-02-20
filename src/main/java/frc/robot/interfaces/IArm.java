package frc.robot.interfaces;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public interface IArm {
	// returns the state of the limit switch
	public boolean getLimitSwitchState();
	
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

	public void stay();	
		
	public void stop();

	// NOTE THAT THIS METHOD WILL IMPACT BOTH OPEN AND CLOSED LOOP MODES
	public void setNominalAndPeakOutputs(double peakOutput);

	// for debug purpose only
	public void joystickControl(Joystick joystick);

	public void gamepadControl(XboxController gamepad);
	
	public double getTarget();
}
