package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrame;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.motorcontrol.RemoteSensorSource;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.MathUtil;
//import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.interfaces.*;
import frc.robot.Robot;
import frc.robot.sensors.HMCamera;
import frc.robot.sensors.HMAccelerometer;
//import frc.robot.commands.drivetrain.*;
//import frc.robot.util.*;


/**
 * The {@code Drivetrain} class contains fields and methods pertaining to the function of the drivetrain.
 */
public class Drivetrain extends SubsystemBase implements /*PIDOutput, PIDOutput2, PIDOutput3,*/ IDrivetrain {

	// general settings
	static final double DIAMETER_WHEEL_INCHES = 6; //5; TODO set proper diameter
	public static final double PERIMETER_WHEEL_INCHES = DIAMETER_WHEEL_INCHES * Math.PI;
	
	static final int TIMEOUT_MS = 15000;	
	
	public static final double RADIUS_DRIVEVETRAIN_INCHES = 9.5; // 12.5;
	
	static final double MAX_PCT_OUTPUT = 1.0;
		
	static final int TALON_TIMEOUT_MS = 20;
	public static final int TICKS_PER_REVOLUTION = 2048; // TODO switch to 2048 if needed for Talon FX


	// turn using camera settings
	// NOTE: it might make sense to decrease the PID controller period below 0.02 sec (which is the period used by the main loop)
	public static final double TURN_USING_CAMERA_PID_CONTROLLER_PERIOD_SECONDS = .01; // 0.01 sec = 10 ms 	
	
	public static final double MIN_TURN_USING_CAMERA_PCT_OUTPUT = 0.1; // 0.4;
	public static final double MAX_TURN_USING_CAMERA_PCT_OUTPUT = 0.4; // 0.7;
	
	public static final double TURN_USING_CAMERA_PROPORTIONAL_GAIN = 0.001; //0.005; // TODO tune 320 pixels -> 1.6 pct output
	public static final double TURN_USING_CAMERA_INTEGRAL_GAIN = 0.0;
	public static final double TURN_USING_CAMERA_DERIVATIVE_GAIN = 0.000001; // 0.00001
	
	public static final int PIXEL_THRESHOLD = HMCamera.HORIZONTAL_CAMERA_RES_PIXELS / 40; // about 3/2 degrees - TODO adjust as needed
	
	public final static int TURN_USING_CAMERA_ON_TARGET_MINIMUM_COUNT = 10; //25; // number of times/iterations we need to be on target to really be on target

	public final static int TURN_USING_CAMERA_STALLED_MINIMUM_COUNT = TURN_USING_CAMERA_ON_TARGET_MINIMUM_COUNT * 2 + 10; // number of times/iterations we need to be stalled to really be stalled

	
	// turn settings
	// NOTE: it might make sense to decrease the PID controller period below 0.02 sec (which is the period used by the main loop)
	static final double TURN_PID_CONTROLLER_PERIOD_SECONDS = .01; // 0.01 sec = 10 ms 	
	
	static final double MIN_TURN_PCT_OUTPUT = 0.1; // 0.3;
	static final double MAX_TURN_PCT_OUTPUT = 0.4; // 0.6;
	
	static final double TURN_PROPORTIONAL_GAIN = 0.01; //0.02;
	static final double TURN_INTEGRAL_GAIN = 0.0;
	static final double TURN_DERIVATIVE_GAIN = 0.0001; // 0.0001
	
	static final int DEGREE_THRESHOLD = 3; //1;
	
	private final static int TURN_ON_TARGET_MINIMUM_COUNT = 10; // number of times/iterations we need to be on target to really be on target
	
	private final static int TURN_STALLED_MINIMUM_COUNT = TURN_ON_TARGET_MINIMUM_COUNT * 2 + 30; // number of times/iterations we need to be stalled to really be stalled
	

	// move using camera settings
	// NOTE: it might make sense to decrease the PID controller period below 0.02 sec (which is the period used by the main loop)
	public static final double MOVE_USING_CAMERA_PID_CONTROLLER_PERIOD_SECONDS = .01; // 0.01 sec = 10 ms 	
	
	public static final double MIN_MOVE_USING_CAMERA_PCT_OUTPUT = 0.1;
	public static final double MAX_MOVE_USING_CAMERA_PCT_OUTPUT = 0.5;
	
	public static final double MOVE_USING_CAMERA_PROPORTIONAL_GAIN = 0.01; // TODO tune 12 inches -> 0.12 pct output
	public static final double MOVE_USING_CAMERA_INTEGRAL_GAIN = 0.0;
	public static final double MOVE_USING_CAMERA_DERIVATIVE_GAIN = 0.0;
	
	public static final int DISTANCE_THRESHOLD_INCHES = 12; // TODO adjust as needed
	
	public final static int MOVE_USING_CAMERA_ON_TARGET_MINIMUM_COUNT = 10; //25; // number of times/iterations we need to be on target to really be on target

	public final static int MOVE_USING_CAMERA_STALLED_MINIMUM_COUNT = MOVE_USING_CAMERA_ON_TARGET_MINIMUM_COUNT * 2 + 10; // number of times/iterations we need to be stalled to really be stalled


	// engage using accelerometer settings
	// NOTE: it might make sense to decrease the PID controller period below 0.02 sec (which is the period used by the main loop)
	public static final double ENGAGE_USING_ACCELEROMETER_PID_CONTROLLER_PERIOD_SECONDS = .01; // 0.01 sec = 10 ms 	
	
	public static final double MIN_ENGAGE_USING_ACCELEROMETER_PCT_OUTPUT = 0.1;
	public static final double MAX_ENGAGE_USING_ACCELEROMETER_PCT_OUTPUT = 0.2;
	
	public static final double ENGAGE_USING_ACCELEROMETER_PROPORTIONAL_GAIN = 0.01; // TODO tune 15 degrees -> 0.3 pct output
	public static final double ENGAGE_USING_ACCELEROMETER_INTEGRAL_GAIN = 0.0;
	public static final double ENGAGE_USING_ACCELEROMETER_DERIVATIVE_GAIN = 0.0;
	
	public static final int TILT_THRESHOLD_DEGREES = 2; // LEVEL = A CHARGE STATION within approximately 2.5 degrees of parallel to FIELD carpet
	
	public final static int ENGAGE_USING_ACCELEROMETER_ON_TARGET_MINIMUM_COUNT = 10; //25; // number of times/iterations we need to be on target to really be on target

	public final static int ENGAGE_USING_ACCELEROMETER_STALLED_MINIMUM_COUNT = ENGAGE_USING_ACCELEROMETER_ON_TARGET_MINIMUM_COUNT * 2 + 10; // number of times/iterations we need to be stalled to really be stalled	

	public final static int ENGAGE_SAFE_TRAVEL_LENGTH_FORWARD_INCHES =  12; // if we get near the center that's probably enough
	public final static int ENGAGE_SAFE_TRAVEL_LENGTH_REVERSE_INCHES = 12; // if we get near the center that's probably enough


	// move settings
	static final int PRIMARY_PID_LOOP = 0;
	
	static final int SLOT_0 = 0;

	static final int REMOTE_0 = 0;
	
	static final double SUPER_REDUCED_PCT_OUTPUT = 0.20; //0.25;
	static final double REDUCED_PCT_OUTPUT = 0.3;
	static final double HIGH_PCT_OUTPUT = 0.5;
	
	static final double MOVE_PROPORTIONAL_GAIN = 0.4;
	static final double MOVE_INTEGRAL_GAIN = 0.0;
	static final double MOVE_DERIVATIVE_GAIN = 0.0;
	
	static final int TALON_TICK_THRESH = 128;
	static final double TICK_THRESH = TALON_TICK_THRESH * 4;
	public static final double TICK_PER_100MS_THRESH = 64; // about a tenth of a rotation per second 

	private final static int MOVE_ON_TARGET_MINIMUM_COUNT = 20; // number of times/iterations we need to be on target to really be on target

	private final static int MOVE_STALLED_MINIMUM_COUNT = MOVE_ON_TARGET_MINIMUM_COUNT * 2 + 30; // number of times/iterations we need to be stalled to really be stalled

	private final static int MOVE_FLAT_MINIMUM_COUNT = 10;

	public static final double FLAT_THRESHOLD_DEGREES = 6.0; // LEVEL = A CHARGE STATION within approximately 2.5 degrees of parallel to FIELD carpet
	public static final double SUPER_FLAT_THRESHOLD_DEGREES = 3.0; // LEVEL = A CHARGE STATION within approximately 2.5 degrees of parallel to FIELD carpet
	
	private final static int MOVE_STEEP_MINIMUM_COUNT = 10; // 20 during testing on 10-Mar-2023; // TODO: STEEP CALIBRATION

	public static final double STEEP_THRESHOLD_DEGREES = 13.0; // 15.0 during testing on 10-Mr-2023; // NOTE: REAL CHARGE STATION IS AT 15 DEGREES, SO THRESHOLD SHOULD BE ABOUT 13 DEGREES
	
	// variables
	boolean isMoving; // indicates that the drivetrain is moving using the PID controllers embedded on the motor controllers 
	boolean isTurning;  // indicates that the drivetrain is turning using the PID controller hereunder
	boolean isTurningUsingCamera;  // indicates that the drivetrain is turning using the second PID controller hereunder
	boolean isMovingUsingCamera;  // indicates that the drivetrain is turning using the third PID controller hereunder
	boolean isEngagingUsingAccelerometer;  // indicates that the drivetrain is engaging using the fourth PID controller hereunder
	boolean isReallyStalled;
	boolean isReallyFlat;
	boolean isReallySteep;
	boolean isInCoastNeutralMode;
	
	double ltac, rtac; // target positions 
	
	private int onTargetCountMoving; // counter indicating how many times/iterations we were on target
	private int onTargetCountTurning; // counter indicating how many times/iterations we were on target
	private int onTargetCountTurningUsingCamera; // counter indicating how many times/iterations we were on target
	private int onTargetCountMovingUsingCamera; // counter indicating how many times/iterations we were on target
	private int onTargetCountEngagingUsingAccelerometer; // counter indicating how many times/iterations we were on target
	private int stalledCount; // counter indicating how many times/iterations we were stalled
	private int flatCount; // counter indicating how many times/iterations we were flat
	private int superFlatCount; // counter indicating how many times/iterations we were flat
	private int steepCount; // counter indicating how many times/iterations we were steep

	WPI_TalonSRX masterLeft, masterRight; // motor controllers
	BaseMotorController followerLeft, followerRight; // motor controllers
	
	ADXRS450_Gyro gyro; // gyroscope
	
	DifferentialDrive differentialDrive; // a class to simplify tank or arcade drive (open loop driving) 
	
	Robot robot; // a reference to the robot
	
	PIDController turnPidController; // the PID controller used to turn

	ICamera camera;
	HMAccelerometer accelerometer;
	PIDController turnUsingCameraPidController; // the PID controller used to turn using camera
	PIDController moveUsingCameraPidController; // the PID controller used to turn
	PIDController engageUsingAccelerometerPidController; // the PID controller used to turn

	private final static double RATIO_BETWEEN_INPUT_AND_OUTPUT_LOW = 17.325*0.85; // 17.325*0.8;
	private final static double RATIO_BETWEEN_INPUT_AND_OUTPUT_HIGH = 8*0.8;
	
	public Drivetrain(WPI_TalonSRX masterLeft_in, WPI_TalonSRX masterRight_in, BaseMotorController followerLeft_in, BaseMotorController followerRight_in, ADXRS450_Gyro gyro_in, Robot robot_in, ICamera camera_in, HMAccelerometer accelerometer_in) 
	{
		masterLeft = masterLeft_in;
		masterRight = masterRight_in;
		followerLeft = followerLeft_in;
		followerRight = followerRight_in;
		gyro = gyro_in;	
		robot = robot_in;
		camera = camera_in;
		accelerometer = accelerometer_in;

		masterLeft.configFactoryDefault();
		followerLeft.configFactoryDefault();
		masterRight.configFactoryDefault();
		followerRight.configFactoryDefault();
		
		// Mode of operation during Neutral output may be set by using the setNeutralMode() function.
		// As of right now, there are two options when setting the neutral mode of a motor controller,
		// brake and coast.
		setCoastNeutralMode();
		
		// Sensors for motor controllers provide feedback about the position, velocity, and acceleration
		// of the system using that motor controller.
		// Note: With Phoenix framework, position units are in the natural units of the sensor.
		// This ensures the best resolution possible when performing closed-loops in firmware.
		// CTRE Magnetic Encoder (relative/quadrature) =  4096 units per rotation
		// FX Integrated Sensor = 2048 units per rotation
		masterLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,
				PRIMARY_PID_LOOP, TALON_TIMEOUT_MS);
				
		masterRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,
				PRIMARY_PID_LOOP, TALON_TIMEOUT_MS);
		
		// Sensor phase is the term used to explain sensor direction.
		// In order for limit switches and closed-loop features to function properly the sensor and motor has to be in-phase.
		// This means that the sensor position must move in a positive direction as the motor controller drives positive output.  
		masterLeft.setSensorPhase(true);
		masterRight.setSensorPhase(true);
		
		// Disables limit switches
		masterLeft.overrideLimitSwitchesEnable(false);
		masterRight.overrideLimitSwitchesEnable(false);

		// Disables software limit switches
		masterLeft.overrideSoftLimitsEnable(false);
		masterRight.overrideSoftLimitsEnable(false);
		
		// Motor controller output direction can be set by calling the setInverted() function as seen below.
		// Note: Regardless of invert value, the LEDs will blink green when positive output is requested (by robot code or firmware closed loop).
		// Only the motor leads are inverted. This feature ensures that sensor phase and limit switches will properly match the LED pattern
		// (when LEDs are green => forward limit switch and soft limits are being checked). 
		masterLeft.setInverted(true);
		masterRight.setInverted(true);
		followerLeft.setInverted(true); 
		followerRight.setInverted(true);
		
		// motors will turn in opposite directions if not inverted 
		
		// Both the Talon SRX and Victor SPX have a follower feature that allows the motor controllers to mimic another motor controller's output.
		// Users will still need to set the motor controller's direction, and neutral mode.
		// The method follow() allows users to create a motor controller follower of not only the same model, but also other models
		// , talon to talon, victor to victor, talon to victor, and victor to talon.
		followerLeft.follow(masterLeft);
		followerRight.follow(masterRight);

		// Motor controllers that are followers can set Status 1 and Status 2 to 255ms(max) using setStatusFramePeriod.
		// The Follower relies on the master status frame allowing its status frame to be slowed without affecting performance.
		// This is a useful optimization to manage CAN bus utilization.
		followerLeft.setStatusFramePeriod(StatusFrame.Status_1_General, 255, TALON_TIMEOUT_MS);
		followerLeft.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 255, TALON_TIMEOUT_MS);
		followerRight.setStatusFramePeriod(StatusFrame.Status_1_General, 255, TALON_TIMEOUT_MS);
		followerRight.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 255, TALON_TIMEOUT_MS);
		
		// set peak output to max in case if had been reduced previously
		setNominalAndPeakOutputs(MAX_PCT_OUTPUT);

		
		//creates a PID controller
		turnPidController = new PIDController(TURN_PROPORTIONAL_GAIN, TURN_INTEGRAL_GAIN, TURN_DERIVATIVE_GAIN/*, gyro, this, TURN_PID_CONTROLLER_PERIOD_SECONDS*/);
		
		//turnPidController.setInputRange(-180, 180); // valid input range 
		//turnPidController.setOutputRange(-MAX_TURN_PCT_OUTPUT, MAX_TURN_PCT_OUTPUT); // output range NOTE: might need to change signs
		//TODO manually clamp with MathUtil.clamp(turnPidController.calculate(gyro.getAngle()), -MAX_TURN_PCT_OUTPUT, MAX_TURN_PCT_OUTPUT);
		
		turnPidController.enableContinuousInput(-180, 180); // because -180 degrees is the same as 180 degrees (needs input range to be defined first)
		turnPidController.setTolerance(DEGREE_THRESHOLD); // 1 degree error tolerated

		//creates a second PID controller
		turnUsingCameraPidController = new PIDController(TURN_USING_CAMERA_PROPORTIONAL_GAIN, TURN_USING_CAMERA_INTEGRAL_GAIN, TURN_USING_CAMERA_DERIVATIVE_GAIN/*, camera, new PIDOutput2Adapter(this), TURN_USING_CAMERA_PID_CONTROLLER_PERIOD_SECONDS*/);
		
		//turnUsingCameraPidController.setInputRange(-HMCamera.HORIZONTAL_CAMERA_RES_PIXELS/2, HMCamera.HORIZONTAL_CAMERA_RES_PIXELS/2); // valid input range 
		//turnUsingCameraPidController.setOutputRange(-MAX_TURN_USING_CAMERA_PCT_OUTPUT, MAX_TURN_USING_CAMERA_PCT_OUTPUT); // output range NOTE: might need to change signs
		//TODO manually clamp with MathUtil.clamp(turnUsingCameraPidController.calculate(camera.pidGet()), -MAX_TURN_USING_CAMERA_PCT_OUTPUT, MAX_TURN_USING_CAMERA_PCT_OUTPUT);
		
		turnUsingCameraPidController.setTolerance(PIXEL_THRESHOLD); // error tolerated

		//creates a third PID controller
		moveUsingCameraPidController = new PIDController(MOVE_USING_CAMERA_PROPORTIONAL_GAIN, MOVE_USING_CAMERA_INTEGRAL_GAIN, MOVE_USING_CAMERA_DERIVATIVE_GAIN/*, new PIDSource2Adapter(camera), new PIDOutput3Adapter(this), MOVE_USING_CAMERA_PID_CONTROLLER_PERIOD_SECONDS*/);

		//moveUsingCameraPidController.setInputRange(-HMCamera.SAFE_DISTANCE_INCHES, HMCamera.SAFE_DISTANCE_INCHES); // valid input range 
		//moveUsingCameraPidController.setOutputRange(-MAX_MOVE_USING_CAMERA_PCT_OUTPUT, MAX_MOVE_USING_CAMERA_PCT_OUTPUT); // output range NOTE: might need to change signs
		//TODO manually clamp with MathUtil.clamp(moveUsingCameraPidController.calculate(camera.pidGet2()), -MAX_MOVE_USING_CAMERA_PCT_OUTPUT, MAX_MOVE_USING_CAMERA_PCT_OUTPUT);
		
		moveUsingCameraPidController.setTolerance(DISTANCE_THRESHOLD_INCHES); // error tolerated

		//creates a fourth PID controller
		engageUsingAccelerometerPidController = new PIDController(ENGAGE_USING_ACCELEROMETER_PROPORTIONAL_GAIN, ENGAGE_USING_ACCELEROMETER_INTEGRAL_GAIN, ENGAGE_USING_ACCELEROMETER_DERIVATIVE_GAIN);

		engageUsingAccelerometerPidController.setTolerance(TILT_THRESHOLD_DEGREES); // error tolerated
		
		
		differentialDrive = new DifferentialDrive(masterLeft, masterRight);
		differentialDrive.setSafetyEnabled(false); // disables the stupid timeout error when we run in closed loop
	}
	
	@Override
	public void periodic() {
		// Put code here to be run every loop
		calculateTurnAngleUsingPidController();
		calculateTurnUsingCameraPidController();
		calculateMoveUsingCameraPidController();
		calculateEngageUsingAccelerometerPidController();
	}  

	// this method needs to be paired with checkTurnAngleUsingPidController()
	public void turnAngleUsingPidController(double angle) {
		// switches to percentage vbus
		stop(); // resets state
		
		gyro.reset(); // resets to zero for now
		//double current = gyro.getAngle();
		double heading = angle; //+ current; // calculates new heading

		//System.out.println("heading " + heading);
		
		turnPidController.setSetpoint(heading); // sets the heading
		//turnPidController.enable(); // begins running
		turnPidController.reset(); // resets controller
		
		isTurning = true;
		onTargetCountTurning = 0;
		isReallyStalled = false;
		stalledCount = 0;
		isReallyFlat = false;
		flatCount = 0;
		superFlatCount = 0;
		isReallySteep = false;
		steepCount = 0;
	}

	// this method needs to be paired with checkTurnAngleUsingPidController()
	public void turnToPreviousKnownHeadingUsingPidController() {
		// switches to percentage vbus
		stop(); // resets state
		
		// we do NOT reset the gyro since we want to go back to zero

		double heading = 0; // go back to zero
		
		turnPidController.setSetpoint(heading); // sets the heading
		//turnPidController.enable(); // begins running
		turnPidController.reset(); // resets controller
		
		isTurning = true;
		onTargetCountTurning = 0;
		isReallyStalled = false;
		stalledCount = 0;
		isReallyFlat = false;
		flatCount = 0;
		superFlatCount = 0;
		isReallySteep = false;
		steepCount = 0;
	}	
	
	public void calculateTurnAngleUsingPidController() {	
		if (isTurning) {

			//System.out.println("gyro angle: " + gyro.getAngle());

			double output = MathUtil.clamp(turnPidController.calculate(gyro.getAngle()), -MAX_TURN_PCT_OUTPUT, MAX_TURN_PCT_OUTPUT);
			pidWrite(output);
		}
	}

	// This method checks that we are within target up to ON_TARGET_MINIMUM_COUNT times
	// It relies on its own counter
	public boolean tripleCheckTurnAngleUsingPidController() {	
		if (isTurning) {
			boolean isOnTarget = turnPidController.atSetpoint();
			
			if (isOnTarget) { // if we are on target in this iteration 
				onTargetCountTurning++; // we increase the counter
			} else { // if we are not on target in this iteration
				if (onTargetCountTurning > 0) { // even though we were on target at least once during a previous iteration
					onTargetCountTurning = 0; // we reset the counter as we are not on target anymore
					System.out.println("Triple-check failed (turning).");
				} else {
					// we are definitely turning
				}
			}
			
			if (onTargetCountTurning > TURN_ON_TARGET_MINIMUM_COUNT) { // if we have met the minimum
				isTurning = false;
			}
			
			if (!isTurning) {
				System.out.println("You have reached the target (turning).");
				stop();				 
			}
		}
		return isTurning;
	}

	// this method needs to be paired with checkTurnUsingCameraPidController()
	public void turnUsingCameraPidController()
	{
		// switches to percentage vbus
		stop(); // resets state 
		
		turnUsingCameraPidController.setSetpoint(0); // we want to end centered
		//turnUsingCameraPidController.enable(); // begins running
		turnUsingCameraPidController.reset(); // resets controller
		
		isTurningUsingCamera = true;
		onTargetCountTurningUsingCamera = 0;
		isReallyStalled = false;
		stalledCount = 0;
		isReallyFlat = false;
		flatCount = 0;
		superFlatCount = 0;
		isReallySteep = false;
		steepCount = 0;
	}

	public void calculateTurnUsingCameraPidController() {	
		if (isTurningUsingCamera) {
			double output = MathUtil.clamp(turnUsingCameraPidController.calculate(camera.pidGet()), -MAX_TURN_USING_CAMERA_PCT_OUTPUT, MAX_TURN_USING_CAMERA_PCT_OUTPUT);
			pidWrite2(output);
		}
	}
		
	public boolean tripleCheckTurnUsingCameraPidController()
	{
		if (isTurningUsingCamera) {
			boolean isOnTarget = turnUsingCameraPidController.atSetpoint();
			
			if (isOnTarget) { // if we are on target in this iteration 
				onTargetCountTurningUsingCamera++; // we increase the counter
			} else { // if we are not on target in this iteration
				if (onTargetCountTurningUsingCamera > 0) { // even though we were on target at least once during a previous iteration
					onTargetCountTurningUsingCamera = 0; // we reset the counter as we are not on target anymore
					System.out.println("Triple-check failed (turning using camera).");
				} else {
					// we are definitely turning
				}
			}
			
			if (onTargetCountTurningUsingCamera > TURN_USING_CAMERA_ON_TARGET_MINIMUM_COUNT) { // if we have met the minimum
				isTurningUsingCamera = false;
			}
			
			if (!isTurningUsingCamera) {
				System.out.println("You have reached the target (turning using camera).");
				stop();				 
			}
		}

		//Thread.onSpinWait();

		return isTurningUsingCamera;
	}

	// this method needs to be paired with checkMoveUsingCameraPidController()
	public void moveUsingCameraPidController()
	{
		// switches to percentage vbus
		stop(); // resets state 
		
		moveUsingCameraPidController.setSetpoint(0); // we want to end centered
		//moveUsingCameraPidController.enable(); // begins running
		moveUsingCameraPidController.reset(); // resets controller
		
		isMovingUsingCamera = true;
		onTargetCountMovingUsingCamera = 0;
		isReallyStalled = false;
		stalledCount = 0;
		isReallyFlat = false;
		flatCount = 0;
		superFlatCount = 0;
		isReallySteep = false;
		steepCount = 0;
	}

	public void calculateMoveUsingCameraPidController() {	
		if (isMovingUsingCamera) {
			double output = MathUtil.clamp(moveUsingCameraPidController.calculate(camera.pidGet2()), -MAX_MOVE_USING_CAMERA_PCT_OUTPUT, MAX_MOVE_USING_CAMERA_PCT_OUTPUT);
			pidWrite3(output);
		}
	}
		
	public boolean tripleCheckMoveUsingCameraPidController()
	{
		if (isMovingUsingCamera) {
			boolean isOnTarget = moveUsingCameraPidController.atSetpoint();
			
			if (isOnTarget) { // if we are on target in this iteration 
				onTargetCountMovingUsingCamera++; // we increase the counter
			} else { // if we are not on target in this iteration
				if (onTargetCountMovingUsingCamera > 0) { // even though we were on target at least once during a previous iteration
					onTargetCountMovingUsingCamera = 0; // we reset the counter as we are not on target anymore
					System.out.println("Triple-check failed (moving using camera).");
				} else {
					// we are definitely turning
				}
			}
			
			if (onTargetCountMovingUsingCamera > MOVE_USING_CAMERA_ON_TARGET_MINIMUM_COUNT) { // if we have met the minimum
				isMovingUsingCamera = false;
			}
			
			if (!isMovingUsingCamera) {
				System.out.println("You have reached the target (moving using camera).");
				stop();				 
			}
		}
		return isMovingUsingCamera;
	}

	// this method needs to be paired with checkEngageUsingAccelerometerPidController()
	public void engageUsingAccelerometerPidController()
	{
		// switches to percentage vbus
		stop(); // resets state 

		resetEncoders(); // we zero them to keep track of were were start from for troubleshooting purpose

		// right is the real forward direction, so left is the opposite
		masterRight.configForwardSoftLimitThreshold(+convertInchesToEncoderTicks(ENGAGE_SAFE_TRAVEL_LENGTH_FORWARD_INCHES),TALON_TIMEOUT_MS);
		masterLeft.configReverseSoftLimitThreshold(-convertInchesToEncoderTicks(ENGAGE_SAFE_TRAVEL_LENGTH_FORWARD_INCHES),TALON_TIMEOUT_MS);

		masterRight.configReverseSoftLimitThreshold(-convertInchesToEncoderTicks(ENGAGE_SAFE_TRAVEL_LENGTH_REVERSE_INCHES),TALON_TIMEOUT_MS);
		masterLeft.configForwardSoftLimitThreshold(+convertInchesToEncoderTicks(ENGAGE_SAFE_TRAVEL_LENGTH_REVERSE_INCHES),TALON_TIMEOUT_MS);

		// Enables software limits so we never go too crazy
		masterRight.overrideSoftLimitsEnable(true);
		masterLeft.overrideSoftLimitsEnable(true);
		
		engageUsingAccelerometerPidController.setSetpoint(0); // we want to end level
		engageUsingAccelerometerPidController.reset(); // resets controller
		
		isEngagingUsingAccelerometer = true;
		onTargetCountEngagingUsingAccelerometer = 0;
		isReallyStalled = false;
		stalledCount = 0;
		isReallyFlat = false;
		flatCount = 0;
		superFlatCount = 0;
		isReallySteep = false;
		steepCount = 0;
	}

	public void calculateEngageUsingAccelerometerPidController() {	
		if (isEngagingUsingAccelerometer) {
			// we use the "roll" because of the way we installed the rio on the robot even though we are really looking at "pitch"
			double output = MathUtil.clamp(engageUsingAccelerometerPidController.calculate(accelerometer.getAccurateRoll()), -MAX_ENGAGE_USING_ACCELEROMETER_PCT_OUTPUT, MAX_ENGAGE_USING_ACCELEROMETER_PCT_OUTPUT);
			pidWrite4(output);
		}
	}
		
	public boolean tripleCheckEngageUsingAccelerometerPidController()
	{
		if (isEngagingUsingAccelerometer) {
			boolean isOnTarget = engageUsingAccelerometerPidController.atSetpoint();
			
			if (isOnTarget) { // if we are on target in this iteration 
				onTargetCountEngagingUsingAccelerometer++; // we increase the counter
			} else { // if we are not on target in this iteration
				if (onTargetCountEngagingUsingAccelerometer > 0) { // even though we were on target at least once during a previous iteration
					onTargetCountEngagingUsingAccelerometer = 0; // we reset the counter as we are not on target anymore
					System.out.println("Triple-check failed (engaging using accelerometer).");
				} else {
					// we are definitely turning
				}
			}
			
			if (onTargetCountMovingUsingCamera > ENGAGE_USING_ACCELEROMETER_ON_TARGET_MINIMUM_COUNT) { // if we have met the minimum
				isEngagingUsingAccelerometer = false;
			}
			
			if (!isEngagingUsingAccelerometer) {
				System.out.println("You have reached the target (engaging using accelerometer).");
				stop();				 
			}
		}
		return isEngagingUsingAccelerometer;
	}
		
	public void moveDistance(double dist) // moves the distance in inch given
	{
		moveDistance(dist, REDUCED_PCT_OUTPUT);
	}

	public double convertInchesToEncoderTicks(double dist)
	{
		if (Robot.gearbox == null || Robot.gearbox.getGear() == Gearbox.Gear.LOW){ //Using the low gear ratio between input gear and output gear
			return dist / PERIMETER_WHEEL_INCHES * RATIO_BETWEEN_INPUT_AND_OUTPUT_LOW * TICKS_PER_REVOLUTION;
		}
		else {			//Using the high gear ratio between input gear and output gear
			return dist / PERIMETER_WHEEL_INCHES * RATIO_BETWEEN_INPUT_AND_OUTPUT_HIGH * TICKS_PER_REVOLUTION;
		}
	}

	public void moveDistance(double dist, double percentOutput) // True is low gear setting, false is high gear setting
	{
		stop(); // in case we were still doing something
		
		resetEncoders();
		setPIDParameters();

		// use slot 0 for closed-looping
		//masterLeft.selectProfileSlot(SLOT_0, PRIMARY_PID_LOOP);
		//masterRight.selectProfileSlot(SLOT_0, PRIMARY_PID_LOOP);

		setNominalAndPeakOutputs(percentOutput); //this has a global impact, so we reset in stop()


		/*System.out.println("right safe travel length forward: " + convertInchesToEncoderTicks(ENGAGE_SAFE_TRAVEL_LENGTH_FORWARD_INCHES));
		System.out.println("right safe travel length reverse: " + -convertInchesToEncoderTicks(ENGAGE_SAFE_TRAVEL_LENGTH_FORWARD_INCHES));

		// right is the real forward direction, so left is the opposite
		masterRight.configForwardSoftLimitThreshold(+convertInchesToEncoderTicks(ENGAGE_SAFE_TRAVEL_LENGTH_FORWARD_INCHES),TALON_TIMEOUT_MS);
		masterLeft.configReverseSoftLimitThreshold(-convertInchesToEncoderTicks(ENGAGE_SAFE_TRAVEL_LENGTH_FORWARD_INCHES),TALON_TIMEOUT_MS);

		masterRight.configReverseSoftLimitThreshold(-convertInchesToEncoderTicks(ENGAGE_SAFE_TRAVEL_LENGTH_REVERSE_INCHES),TALON_TIMEOUT_MS);
		masterLeft.configForwardSoftLimitThreshold(+convertInchesToEncoderTicks(ENGAGE_SAFE_TRAVEL_LENGTH_REVERSE_INCHES),TALON_TIMEOUT_MS);

		// Enables software limits so we never go too crazy
		masterRight.overrideSoftLimitsEnable(true);
		masterLeft.overrideSoftLimitsEnable(true);*/


		rtac = +convertInchesToEncoderTicks(dist);
		ltac = -convertInchesToEncoderTicks(dist);

		System.out.println("rtac, ltac: " + rtac + ", " + ltac);

		masterRight.set(ControlMode.Position, rtac);
		masterLeft.set(ControlMode.Position, ltac);

		isMoving = true;
		onTargetCountMoving = 0;
		isReallyStalled = false;
		stalledCount = 0;
		isReallyFlat = false;
		flatCount = 0;
		superFlatCount = 0;
		isReallySteep = false;
		steepCount = 0;
	}

	public void moveDistanceHighSpeed(double dist) // moves the distance in inch given
	{
		moveDistance(dist, HIGH_PCT_OUTPUT);
	}

	public void moveDistanceLowSpeed(double dist) // moves the distance in inch given
	{
		moveDistance(dist, SUPER_REDUCED_PCT_OUTPUT);
	}

	// this method needs to be paired with checkMoveDistance()
	/*public void moveAngle(int angle, double percentOutput) {

		pidgey.setStatusFramePeriod(PigeonIMU_StatusFrame.CondStatus_9_SixDeg_YPR, 5, TALON_TIMEOUT_MS);

		masterLeft.configRemoteFeedbackFilter(pidgey.getDeviceID(), RemoteSensorSource.Pigeon_Yaw, REMOTE_0, TALON_TIMEOUT_MS);
		masterRight.configRemoteFeedbackFilter(pidgey.getDeviceID(), RemoteSensorSource.Pigeon_Yaw, REMOTE_0, TALON_TIMEOUT_MS);

		masterLeft.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0, PRIMARY_PID_LOOP, TALON_TIMEOUT_MS);
		masterRight.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0, PRIMARY_PID_LOOP, TALON_TIMEOUT_MS);

		stop(); // in case we were still doing something
		
		resetEncoders();

		pidgey.setYaw(0, TALON_TIMEOUT_MS);

		setPIDParameters();

		// use slot 0 for closed-looping
		//masterLeft.selectProfileSlot(SLOT_0, PRIMARY_PID_LOOP);
		//masterRight.selectProfileSlot(SLOT_0, PRIMARY_PID_LOOP);

		setNominalAndPeakOutputs(percentOutput); //this has a global impact, so we reset in stop()

		rtac = + 8192 * angle / 360; 
		ltac = - 8192 * angle / 360; 

		System.out.println("rtac, ltac: " + rtac + ", " + ltac);
		masterRight.set(ControlMode.Position, rtac);
		masterLeft.set(ControlMode.Position, ltac);

		isMoving = true;
		onTargetCountMoving = 0;
		isReallyStalled = false;
		stalledCount = 0;
		isReallyFlat = false;
		flatCount = 0;		
		superFlatCount = 0;
		isReallySteep = false;
		steepCount = 0;
	}*/
		
	public boolean tripleCheckMoveDistance() {
		if (isMoving) {
			
			double rerror = masterRight.getClosedLoopError(PRIMARY_PID_LOOP);
			double lerror = masterLeft.getClosedLoopError(PRIMARY_PID_LOOP);
			
			boolean isOnTarget = (Math.abs(rerror) < TICK_THRESH && Math.abs(lerror) < TICK_THRESH);
			
			if (isOnTarget) { // if we are on target in this iteration 
				onTargetCountMoving++; // we increase the counter
			} else { // if we are not on target in this iteration
				if (onTargetCountMoving > 0) { // even though we were on target at least once during a previous iteration
					onTargetCountMoving = 0; // we reset the counter as we are not on target anymore
					System.out.println("Triple-check failed (moving).");
				} else {
					// we are definitely moving
					//System.out.println("ltac, rtac: " + ltac + ", " + rtac);
					//System.out.println("encoder left: " + masterLeft.getSelectedSensorPosition(PRIMARY_PID_LOOP));
					//System.out.println("encoder right: " + masterRight.getSelectedSensorPosition(PRIMARY_PID_LOOP));
					
					//System.out.println("moving error left: " + lerror);
					//System.out.println("moving error right: " + rerror);
				}
			}
			
			if (onTargetCountMoving > MOVE_ON_TARGET_MINIMUM_COUNT) { // if we have met the minimum
				isMoving = false;
			}
			
			if (!isMoving) {
				System.out.println("You have reached the target (moving).");
				stop();				 
			}
		}
		return isMoving;
	}
	
	private double arclength(int angle) // returns the inches needed to be moved
	// to turn the specified angle
	{
		return Math.toRadians(angle) * RADIUS_DRIVEVETRAIN_INCHES;
	}

	// this method needs to be paired with checkMoveDistance()
	public void moveDistanceAlongArc(int angle) {
		stop(); // in case we were still doing something
		
		double dist = arclength(angle);
		
		resetEncoders();
		setPIDParameters();

		// use slot 0 for closed-looping
		//masterLeft.selectProfileSlot(SLOT_0, PRIMARY_PID_LOOP);
		//masterRight.selectProfileSlot(SLOT_0, PRIMARY_PID_LOOP);

		rtac = +convertInchesToEncoderTicks(dist); // todo adjust sign as needed
		ltac = +convertInchesToEncoderTicks(dist); // todo adjust sign as needed

		System.out.println("rtac, ltac: " + rtac + ", " + ltac);
		
		masterRight.set(ControlMode.Position, rtac);
		masterLeft.set(ControlMode.Position, ltac);
		
		isMoving = true;
		onTargetCountMoving = 0;
		isReallyStalled = false;
		stalledCount = 0;
		isReallyFlat = false;
		flatCount = 0;		
		superFlatCount = 0;
		isReallySteep = false;
		steepCount = 0;
	}
	
	// return if drivetrain might be stalled
	public boolean tripleCheckIfStalled() {
		if (isMoving || isTurning || isMovingUsingCamera || isTurningUsingCamera || isEngagingUsingAccelerometer) {
			
			double rvelocity = getRightEncoderVelocity();
			double lvelocity = getLeftEncoderVelocity();
			
			boolean isStalled = (Math.abs(rvelocity) < TICK_PER_100MS_THRESH && Math.abs(lvelocity) < TICK_PER_100MS_THRESH);
			
			if (isStalled) { // if we are stalled in this iteration 
				stalledCount++; // we increase the counter
			} else { // if we are not stalled in this iteration
				if (stalledCount > 0) { // even though we were stalled at least once during a previous iteration
					stalledCount = 0; // we reset the counter as we are not stalled anymore
					System.out.println("Triple-check failed (detecting stall).");
				} else {
					// we are definitely not stalled
					
					//System.out.println("moving velocity left: " + lvelocity);
					//System.out.println("moving velocity right: " + rvelocity);
				}
			}
			
			if (isMoving && stalledCount > MOVE_STALLED_MINIMUM_COUNT) { // if we have met the minimum
				isReallyStalled = true;
			}
			
			if (isTurning && stalledCount > TURN_STALLED_MINIMUM_COUNT) { // if we have met the minimum
				isReallyStalled = true;
			}

			if (isMovingUsingCamera && stalledCount > MOVE_USING_CAMERA_STALLED_MINIMUM_COUNT) { // if we have met the minimum
				isReallyStalled = true;
			}
			
			if (isTurningUsingCamera && stalledCount > TURN_USING_CAMERA_STALLED_MINIMUM_COUNT) { // if we have met the minimum
				isReallyStalled = true;
			}

			if (isEngagingUsingAccelerometer && stalledCount > ENGAGE_USING_ACCELEROMETER_STALLED_MINIMUM_COUNT) { // if we have met the minimum
				isReallyStalled = true;
			}
			
			if (isReallyStalled) {
				System.out.println("WARNING: Stall detected!");
				stop(); // WE STOP IF A STALL IS DETECTED				 
			}
		}
		
		return isReallyStalled;
	}

	// return if drivetrain might be stalled
	public boolean tripleCheckIfFlat() {
		if (isMoving) {
			
			double pitch = accelerometer.getAccurateRoll(); // roll is picth because of how Rio is mounted
			
			boolean isFlat = Math.abs(pitch) < FLAT_THRESHOLD_DEGREES;

			boolean isSuperFlat = Math.abs(pitch) < SUPER_FLAT_THRESHOLD_DEGREES;
			
			if (isFlat) { // if we are flat in this iteration 
				flatCount++; // we increase the counter

				if (isSuperFlat) {
					superFlatCount++;
				}

			} else { // if we are not flat in this iteration
				if (flatCount > 0) { // even though we were flat at least once during a previous iteration
					// TODO: FLAT CALIBRATION
					//flatCount = 0; // we reset the counter as we are not flat anymore
					flatCount-=1;
					superFlatCount = 0; // we reset the counter as we are not flat anymore
					System.out.println("Triple-check failed (detecting flat).");
				} else {
					// we are definitely not flat
					
					//System.out.println("pitch: " + pitch);
				}
			}
			
			if (isMoving && flatCount > MOVE_FLAT_MINIMUM_COUNT) { // if we have met the minimum
				isReallyFlat = true;
			}
						
			if (isReallyFlat) {
				System.out.println("WARNING: Flat detected!");
				stop(); // WE STOP IF A FLAT IS DETECTED				 
			}
		}
		
		return isReallyFlat;
	}

	// return if drivetrain might be stalled
	public boolean tripleCheckIfSteep() {
		if (isMoving) {
			
			double pitch = accelerometer.getAccurateRoll(); // roll is picth because of how Rio is mounted
			
			boolean isSteep = Math.abs(pitch) > STEEP_THRESHOLD_DEGREES;
			
			if (isSteep) { // if we are steep in this iteration 
				steepCount++; // we increase the counter
			} else { // if we are not steep in this iteration
				if (steepCount > 0) { // even though we were steep at least once during a previous iteration
					// TODO: STEEP CALIBRATION
					// NOTE THAT LINE BELOW WAS COMMENTED OUT DURING TESTING ON 10-MAR-2023 BUT THAT IS PROBABLY NOT A GOOD IDEA
					// THAT SAID COMMENT BACK IF JUSTIFIED
					//steepCount = 0; // we reset the counter as we are not steep anymore
					steepCount-=1;
					System.out.println("Triple-check failed (detecting steep).");
				} else {
					// we are definitely not steep
					
					//System.out.println("pitch: " + pitch);
				}
			}
			
			if (isMoving && steepCount > MOVE_STEEP_MINIMUM_COUNT) { // if we have met the minimum
				isReallySteep = true;
			}
						
			if (isReallySteep) {
				System.out.println("WARNING: Steep detected!");
				stop(); // WE STOP IF A STEEP IS DETECTED				 
			}
		}
		
		return isReallySteep;
	}
	
	public void stop() {
		//turnPidController.disable(); // exits PID loop
		//turnUsingCameraPidController.disable(); // exits PID loop
		//moveUsingCameraPidController.disable(); // exits PID loop
		 
		masterLeft.set(ControlMode.PercentOutput, 0);
		masterRight.set(ControlMode.PercentOutput, 0);
		
		isMoving = false;
		isTurning = false;
		isMovingUsingCamera = false;
		isTurningUsingCamera = false;
		isEngagingUsingAccelerometer = false;
		
		setNominalAndPeakOutputs(MAX_PCT_OUTPUT); // we undo what me might have changed

		// Disables software limits in case we enabled them during auto-engage
		masterLeft.overrideSoftLimitsEnable(false);
		masterRight.overrideSoftLimitsEnable(false);
	}
	
	public void setPIDParameters()
	{
		masterRight.configAllowableClosedloopError(SLOT_0, TALON_TICK_THRESH, TALON_TIMEOUT_MS);
		masterLeft.configAllowableClosedloopError(SLOT_0, TALON_TICK_THRESH, TALON_TIMEOUT_MS);
		
		// P is the proportional gain. It modifies the closed-loop output by a proportion (the gain value)
		// of the closed-loop error.
		// P gain is specified in output unit per error unit.
		// When tuning P, it's useful to estimate your starting value.
		// If you want your mechanism to drive 50% output when the error is 4096 (one rotation when using CTRE Mag Encoder),
		// then the calculated Proportional Gain would be (0.50 X 1023) / 4096 = ~0.125.
		
		// I is the integral gain. It modifies the closed-loop output according to the integral error
		// (summation of the closed-loop error each iteration).
		// I gain is specified in output units per integrated error.
		// If your mechanism never quite reaches your target and using integral gain is viable,
		// start with 1/100th of the Proportional Gain.
		
		// D is the derivative gain. It modifies the closed-loop output according to the derivative error
		// (change in closed-loop error each iteration).
		// D gain is specified in output units per derivative error.
		// If your mechanism accelerates too abruptly, Derivative Gain can be used to smooth the motion.
		// Typically start with 10x to 100x of your current Proportional Gain.

		// Feed-Forward is typically used in velocity and motion profile/magic closed-loop modes.
		// F gain is multiplied directly by the set point passed into the programming API.
		// The result of this multiplication is in motor output units [-1023, 1023]. This allows the robot to feed-forward using the target set-point.
		// In order to calculate feed-forward, you will need to measure your motor's velocity at a specified percent output
		// (preferably an output close to the intended operating range).
		
		masterRight.config_kP(SLOT_0, MOVE_PROPORTIONAL_GAIN, TALON_TIMEOUT_MS);
		masterRight.config_kI(SLOT_0, MOVE_INTEGRAL_GAIN, TALON_TIMEOUT_MS);
		masterRight.config_kD(SLOT_0, MOVE_DERIVATIVE_GAIN, TALON_TIMEOUT_MS);
		masterRight.config_kF(SLOT_0, 0, TALON_TIMEOUT_MS);
		
		masterLeft.config_kP(SLOT_0, MOVE_PROPORTIONAL_GAIN, TALON_TIMEOUT_MS);
		masterLeft.config_kI(SLOT_0, MOVE_INTEGRAL_GAIN, TALON_TIMEOUT_MS);
		masterLeft.config_kD(SLOT_0, MOVE_DERIVATIVE_GAIN, TALON_TIMEOUT_MS);	
		masterLeft.config_kF(SLOT_0, 0, TALON_TIMEOUT_MS);
	}
	
	// NOTE THAT THIS METHOD WILL IMPACT BOTH OPEN AND CLOSED LOOP MODES
	public void setNominalAndPeakOutputs(double peakOutput)
	{
		masterLeft.configPeakOutputForward(peakOutput, TALON_TIMEOUT_MS);
		masterLeft.configPeakOutputReverse(-peakOutput, TALON_TIMEOUT_MS);
		masterRight.configPeakOutputForward(peakOutput, TALON_TIMEOUT_MS);
		masterRight.configPeakOutputReverse(-peakOutput, TALON_TIMEOUT_MS);
		
		masterRight.configNominalOutputForward(0, TALON_TIMEOUT_MS);
		masterLeft.configNominalOutputForward(0, TALON_TIMEOUT_MS);
		masterRight.configNominalOutputReverse(0, TALON_TIMEOUT_MS);
		masterLeft.configNominalOutputReverse(0, TALON_TIMEOUT_MS);
	}

	public void joystickControl(Joystick joyLeft, Joystick joyRight, boolean held) // sets talons to
	// joystick control
	{
		if (!isMoving && !isTurning && !isMovingUsingCamera && !isTurningUsingCamera && !isEngagingUsingAccelerometer) // if we are already doing a move or turn we don't take over
		{
			//if(!held)
			//{
				//masterRight.set(ControlMode.PercentOutput, joyRight.getY() * .75);
				//masterLeft.set(ControlMode.PercentOutput, joyLeft.getY() * .75);
				
				//differentialDrive.tankDrive(joyLeft.getY() * .75, -joyRight.getY() * .75); // right needs to be reversed
				
			//	differentialDrive.arcadeDrive(-joyRight.getX() * .80, joyLeft.getY() * .80); // right needs to be reversed
			//}
			//else
			//{
				//masterRight.set(ControlMode.PercentOutput, joyRight.getY());
				//masterLeft.set(ControlMode.PercentOutput, joyLeft.getY());
				
				//differentialDrive.tankDrive(joyLeft.getY(), -joyRight.getY()); // right needs to be reversed
				
				differentialDrive.arcadeDrive(-joyRight.getX() * 0.85, -joyLeft.getY()); // right needs to be reversed
			//}
		}
	}	

	// offers direct access to raw arcade drive functionality
	public void arcadeDrive(double movePctOutput, double turnPctOutput) {
		differentialDrive.arcadeDrive(-turnPctOutput, movePctOutput); // TODO double-check signs
	}
	
	public int getRightEncoderPosition() {
		return (int) (masterRight.getSelectedSensorPosition(PRIMARY_PID_LOOP));
	}

	public int getLeftEncoderPosition() {
		return (int) (masterLeft.getSelectedSensorPosition(PRIMARY_PID_LOOP));
	}

	public int getRightPosition() {
		return (int) (masterRight.getSelectedSensorPosition(PRIMARY_PID_LOOP)*PERIMETER_WHEEL_INCHES/TICKS_PER_REVOLUTION);
	}

	public int getLeftPosition() {
		return (int) (masterLeft.getSelectedSensorPosition(PRIMARY_PID_LOOP)*PERIMETER_WHEEL_INCHES/TICKS_PER_REVOLUTION);
	}
	
	public int getRightEncoderVelocity() {
		return (int) (masterRight.getSelectedSensorVelocity(PRIMARY_PID_LOOP));
	}

	public int getLeftEncoderVelocity() {
		return (int) (masterLeft.getSelectedSensorVelocity(PRIMARY_PID_LOOP));
	}
	
	public boolean isMoving() {
		return isMoving;
	}

	public boolean isMovingUsingCamera() {
		return isMovingUsingCamera;
	}
	
	public boolean isTurning(){
		return isTurning;
	}

	public boolean isTurningUsingCamera() {
		return isTurningUsingCamera;
	}

	public boolean isEngagingUsingAccelerometer() {
		return isEngagingUsingAccelerometer;
	}


	// return if stalled
	public boolean isStalled() {
		return isReallyStalled;
	}

	// return if flat detected
	public boolean isFlatDetected() {
		return isReallyFlat;
	}

	// return if steep detected
	public boolean isSteepDetected() {
		return isReallySteep;
	}

	public int getFlatCount() {
		return flatCount;
	}

	public int getSuperFlatCount() {
		return superFlatCount;
	}

	public int getSteepCount() {
		return steepCount;
	}

	//@Override
	public void pidWrite(double output) {

		//System.out.println("position error: " + turnPidController.getPositionError());
		//System.out.println("raw output: " + output);
		
		// calling disable() on controller will force a call to pidWrite with zero output
		// which we need to handle by not doing anything that could have a side effect 
		if (output != 0 && Math.abs(turnPidController.getPositionError()) < DEGREE_THRESHOLD)
		{
			output = 0;
		}
		if (output != 0 && Math.abs(output) < MIN_TURN_PCT_OUTPUT)
		{
			output = Math.signum(output) * MIN_TURN_PCT_OUTPUT;
		}

		//System.out.println("output: " + output);

		masterRight.set(ControlMode.PercentOutput, -output);
		masterLeft.set(ControlMode.PercentOutput, -output);		
	}
	
	//@Override
	public void pidWrite2(double output) {

		// calling disable() on controller will force a call to pidWrite with zero output
		// which we need to handle by not doing anything that could have a side effect 
		if (output != 0 && Math.abs(turnUsingCameraPidController.getPositionError()) < PIXEL_THRESHOLD)
		{
			output = 0;
		}
		if (output != 0 && Math.abs(output) < MIN_TURN_USING_CAMERA_PCT_OUTPUT)
		{
			output = Math.signum(output) * MIN_TURN_USING_CAMERA_PCT_OUTPUT;
		}
		masterRight.set(ControlMode.PercentOutput, -output); // TODO double-check signs
		masterLeft.set(ControlMode.PercentOutput, -output);		

		//Thread.onSpinWait();
	}

	//@Override
	public void pidWrite3(double output) {

		// calling disable() on controller will force a call to pidWrite with zero output
		// which we need to handle by not doing anything that could have a side effect 
		if (output != 0 && Math.abs(moveUsingCameraPidController.getPositionError()) < DISTANCE_THRESHOLD_INCHES)
		{
			output = 0;
		}
		if (output != 0 && Math.abs(output) < MIN_MOVE_USING_CAMERA_PCT_OUTPUT)
		{
			output = Math.signum(output) * MIN_MOVE_USING_CAMERA_PCT_OUTPUT;
		}
		masterRight.set(ControlMode.PercentOutput, +output); // TODO double-check signs
		masterLeft.set(ControlMode.PercentOutput, -output);		
	}

	public void pidWrite4(double output) {

		// calling disable() on controller will force a call to pidWrite with zero output
		// which we need to handle by not doing anything that could have a side effect 
		if (output != 0 && Math.abs(engageUsingAccelerometerPidController.getPositionError()) < TILT_THRESHOLD_DEGREES)
		{
			output = 0;
		}
		if (output != 0 && Math.abs(output) < MIN_ENGAGE_USING_ACCELEROMETER_PCT_OUTPUT)
		{
			output = Math.signum(output) * MIN_ENGAGE_USING_ACCELEROMETER_PCT_OUTPUT;
		}
		masterRight.set(ControlMode.PercentOutput, +output); // TODO double-check signs
		masterLeft.set(ControlMode.PercentOutput, -output);		
	}

	// MAKE SURE THAT YOU ARE NOT IN A CLOSED LOOP CONTROL MODE BEFORE CALLING THIS METHOD.
	// OTHERWISE THIS IS EQUIVALENT TO MOVING TO THE DISTANCE TO THE CURRENT ZERO IN REVERSE! 
	public void resetEncoders() {
		masterRight.set(ControlMode.PercentOutput, 0); // we switch to open loop to be safe.
		masterLeft.set(ControlMode.PercentOutput, 0);			
		
		masterRight.setSelectedSensorPosition(0, PRIMARY_PID_LOOP, TALON_TIMEOUT_MS);
		masterLeft.setSelectedSensorPosition(0, PRIMARY_PID_LOOP, TALON_TIMEOUT_MS);
	}	

	public void setCoastNeutralMode() {
		// Mode of operation during Neutral output may be set by using the setNeutralMode() function.
		// As of right now, there are two options when setting the neutral mode of a motor controller,
		// brake and coast.
		masterLeft.setNeutralMode(NeutralMode.Coast); // sets the talons on coast mode
		followerLeft.setNeutralMode(NeutralMode.Coast);	
		masterRight.setNeutralMode(NeutralMode.Coast);
		followerRight.setNeutralMode(NeutralMode.Coast);

		isInCoastNeutralMode = true;
	}

	public void setBrakeNeutralMode() {
		// Mode of operation during Neutral output may be set by using the setNeutralMode() function.
		// As of right now, there are two options when setting the neutral mode of a motor controller,
		// brake and coast.
		masterLeft.setNeutralMode(NeutralMode.Brake); // sets the talons on brake mode
		followerLeft.setNeutralMode(NeutralMode.Brake);	
		masterRight.setNeutralMode(NeutralMode.Brake);
		followerRight.setNeutralMode(NeutralMode.Brake);

		isInCoastNeutralMode = false;
	}

	public boolean isInCoastNeutralMode() {
		return isInCoastNeutralMode;
	}

}


