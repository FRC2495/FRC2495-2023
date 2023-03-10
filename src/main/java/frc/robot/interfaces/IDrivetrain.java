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

	// engages to the charge station using the accelerometer
	public void engageUsingAccelerometerPidController();

	// checks if engagement is complete
	public boolean tripleCheckEngageUsingAccelerometerPidController();
	
	// this method needs to be paired with checkMoveDistance()
	public void moveDistance(double dist);
	
	// same as moveDistance(), but at high speed
	public void moveDistanceHighSpeed(double dist);

	// same as moveDistance(), but at low speed
	public void moveDistanceLowSpeed(double dist);
	
	// checks that move is complete
	public boolean tripleCheckMoveDistance();

	// this method needs to be paired with checkMoveDistance()
	public void moveDistanceAlongArc(int angle);
	
	// checks if drivetrain might be stalled
	public boolean tripleCheckIfStalled();

	// checks if drivetrain is on flat terrain
	public boolean tripleCheckIfFlat();

	// checks if drivetrain is on steep terrain
	public boolean tripleCheckIfSteep();
	
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

	public boolean isEngagingUsingAccelerometer();
	
	// return if stalled
	public boolean isStalled();

	// return if flat detected
	public boolean isFlatDetected();

	// return if steep detected
	public boolean isSteepDetected();

	public int getFlatCount();

	public int getSuperFlatCount();
	
	public int getSteepCount();
		
	// MAKE SURE THAT YOU ARE NOT IN A CLOSED LOOP CONTROL MODE BEFORE CALLING THIS METHOD.
	// OTHERWISE THIS IS EQUIVALENT TO MOVING TO THE DISTANCE TO THE CURRENT ZERO IN REVERSE! 
	public void resetEncoders();

	public void setCoastNeutralMode();

	public void setBrakeNeutralMode();

	public boolean isInCoastNeutralMode();
}


