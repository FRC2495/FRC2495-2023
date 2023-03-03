package frc.robot.interfaces;

import edu.wpi.first.wpilibj2.command.Subsystem;

import edu.wpi.first.wpilibj.Joystick;

public interface IDrivetrain extends Subsystem {
	
	// this method needs to be paired with checkTurnAngleUsingPidController()
	public void turnAngleUsingPidController(double angle);

	// turns to previous known heading asynchronously
	public void turnToPreviousKnownHeadingUsingPidController();
	
	// checks if turn if complete
	public boolean tripleCheckTurnAngleUsingPidController();

	// turns to the target using the camera
	public void turnUsingCameraPidController();

	// checks if turn is complete
	public boolean tripleCheckTurnUsingCameraPidController();
	
	// this method needs to be paired with checkMoveDistance()
	public void moveDistance(double dist);
	
	// same as moveDistance(), but at high speed
	public void moveDistanceHighSpeed(double dist);
	
	// checks that move is complete
	public boolean tripleCheckMoveDistance();

	// this method needs to be paired with checkMoveDistance()
	public void moveDistanceAlongArc(int angle);
	
	// checks if drivetrain might be stalled
	public boolean tripleCheckIfStalled();
	
	// stops the drivetrain (cuts power)
	public void stop();
	
	public void setPIDParameters();
	
	// NOTE THAT THIS METHOD WILL IMPACT BOTH OPEN AND CLOSED LOOP MODES
	public void setNominalAndPeakOutputs(double peakOutput);
	
	public void joystickControl(Joystick joyLeft, Joystick joyRight, boolean held);
	
	public int getRightEncoderPosition();

	public int getLeftEncoderPosition();

	public int getRightPosition();

	public int getLeftPosition();
	
	public int getRightEncoderVelocity();

	public int getLeftEncoderVelocity();
	
	public boolean isMoving();
	
	public boolean isTurning();

	public boolean isMovingUsingCamera();

	public boolean isTurningUsingCamera();
	
	// return if stalled
	public boolean isStalled();
		
	// MAKE SURE THAT YOU ARE NOT IN A CLOSED LOOP CONTROL MODE BEFORE CALLING THIS METHOD.
	// OTHERWISE THIS IS EQUIVALENT TO MOVING TO THE DISTANCE TO THE CURRENT ZERO IN REVERSE! 
	public void resetEncoders();
}


