package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.ParamEnum;

import frc.robot.interfaces.*;
//import frc.robot.Ports;
import frc.robot.Robot;


/**
 * The {@code Shoulder} class contains fields and methods pertaining to the function of the shoulder.
 */
public class Shoulder extends SubsystemBase implements IShoulder {
	
	// general settings
	static final int TIMEOUT_MS = 15000;
	
	public static final double GEAR_RATIO = 3.0; // TODO change if needed
	
	public static final int ANGLE_TO_TRAVEL_TICKS = 140000; // TODO set proper value
	public static final int ANGLE_TO_MIDWAY_TICKS = 75000;
	//public static final int ANGLE_TO_FLOOR_TICKS = ;
	//public static final int ANGLE_TO_LEVEL_2_TICKS = ;
	//public static final int ANGLE_TO_LEVEL_3_TICKS = ;
	
	/*
	!!! VIRTUAL_HOME_OFFSET_TICKS is important for moving up,     !!!
	!!! if this is changed make sure to check to see if moveUp() works !!!
	(it's used as an error margin for moving up, since we can't reliably check when it's up)
	*/
	static final double VIRTUAL_HOME_OFFSET_TICKS = -1000; // position of virtual home compared to physical home
	
	static final double MAX_PCT_OUTPUT = 1.0; // ~full speed
	
	static final int TALON_TIMEOUT_MS = 20;
	public static final int TICKS_PER_REVOLUTION = 2048;
	
	
	// move settings
	static final int PRIMARY_PID_LOOP = 0;
	
	static final int SLOT_0 = 0;
	
	static final double REDUCED_PCT_OUTPUT = 0.4;
	static final double SUPER_REDUCED_PCT_OUTPUT = 0.1;
	
	static final double MOVE_PROPORTIONAL_GAIN = 0.06;
	static final double MOVE_INTEGRAL_GAIN = 0.0;
	static final double MOVE_DERIVATIVE_GAIN = 0.0;
	
	static final int TALON_TICK_THRESH = 1024; //256;
	static final double TICK_THRESH = 8192; //4096;	
	
	private final static int MOVE_ON_TARGET_MINIMUM_COUNT= 20; // number of times/iterations we need to be on target to really be on target

	
	// variables
	boolean isMoving, isMovingUp;
	
	WPI_TalonSRX shoulder;
	//BaseMotorController shoulder_follower;
	
	double tac;
	boolean hasBeenHomed = false;

	private int onTargetCount; // counter indicating how many times/iterations we were on target

	Robot robot; 
	
	
	public Shoulder(WPI_TalonSRX shoulder_in/*, BaseMotorController shoulder_follower_in*/, Robot robot_in) {
		shoulder = shoulder_in;
		//shoulder_follower = shoulder_follower_in;
		robot = robot_in;
		
		shoulder.configFactoryDefault();
		//shoulder_follower.configFactoryDefault();

		// Mode of operation during Neutral output may be set by using the setNeutralMode() function.
		// As of right now, there are two options when setting the neutral mode of a motor controller,
		// brake and coast.	
		shoulder.setNeutralMode(NeutralMode.Brake);
		//shoulder_follower.setNeutralMode(NeutralMode.Brake);
		
		// Sensor phase is the term used to explain sensor direction.
		// In order for limit switches and closed-loop features to function properly the sensor and motor has to be in-phase.
		// This means that the sensor position must move in a positive direction as the motor controller drives positive output.
		shoulder.setSensorPhase(true);

		// Enables limit switches
		shoulder.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, TALON_TIMEOUT_MS);
		shoulder.configReverseLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen, TALON_TIMEOUT_MS);
		shoulder.overrideLimitSwitchesEnable(true);

		// Motor controller output direction can be set by calling the setInverted() function as seen below.
		// Note: Regardless of invert value, the LEDs will blink green when positive output is requested (by robot code or firmware closed loop).
		// Only the motor leads are inverted. This feature ensures that sensor phase and limit switches will properly match the LED pattern
		// (when LEDs are green => forward limit switch and soft limits are being checked). 	
		shoulder.setInverted(true); // invert if required
		//shoulder_follower.setInverted(false);

		// Both the Talon SRX and Victor SPX have a follower feature that allows the motor controllers to mimic another motor controller's output.
		// Users will still need to set the motor controller's direction, and neutral mode.
		// The method follow() allows users to create a motor controller follower of not only the same model, but also other models
		// , talon to talon, victor to victor, talon to victor, and victor to talon.
		//shoulder_follower.follow(shoulder);

		// Motor controllers that are followers can set Status 1 and Status 2 to 255ms(max) using setStatusFramePeriod.
		// The Follower relies on the master status frame allowing its status frame to be slowed without affecting performance.
		// This is a useful optimization to manage CAN bus utilization.
		//shoulderfollower.setStatusFramePeriod(StatusFrame.Status_1_General, 255, TALON_TIMEOUT_MS);
		//shoulderFollower.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 255, TALON_TIMEOUT_MS);

		setPIDParameters();
		
		setNominalAndPeakOutputs(REDUCED_PCT_OUTPUT);

		// Sensors for motor controllers provide feedback about the position, velocity, and acceleration
		// of the system using that motor controller.
		// Note: With Phoenix framework, position units are in the natural units of the sensor.
		// This ensures the best resolution possible when performing closed-loops in firmware.
		// CTRE Magnetic Encoder (relative/quadrature) =  4096 units per rotation		
		// FX Integrated Sensor = 2048 units per rotation
		shoulder.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,	PRIMARY_PID_LOOP, TALON_TIMEOUT_MS);

		// this will reset the encoder automatically when at or past the reverse limit sensor
		shoulder.configSetParameter(ParamEnum.eClearPositionOnLimitR, 0, 0, 0, TALON_TIMEOUT_MS);
		shoulder.configSetParameter(ParamEnum.eClearPositionOnLimitF, 1, 0, 0, TALON_TIMEOUT_MS);		
		
		isMoving = false;
		isMovingUp = false;

		hasBeenHomed = true; // we always consider the shoulderd homed as we have limit sensors on both sides
	}

	/*@Override
	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}*/

	@Override
	public void periodic() {
		// Put code here to be run every loop

	}

	// returns the state of the limit switch
	public boolean getLimitSwitchState() {
		return shoulder.getSensorCollection().isRevLimitSwitchClosed();
	}

	public boolean getForwardLimitSwitchState() {
		return shoulder.getSensorCollection().isFwdLimitSwitchClosed();
	}

	
	// This method should be called to assess the progress of a move
	public boolean tripleCheckMove() {
		if (isMoving) {
			
			double error = shoulder.getClosedLoopError(PRIMARY_PID_LOOP);
			//System.out.println("Shoulder moving error: " + Math.abs(error));
			
			boolean isOnTarget = (Math.abs(error) < TICK_THRESH);
			
			if (isOnTarget) { // if we are on target in this iteration 
				onTargetCount++; // we increase the counter
			} else { // if we are not on target in this iteration
				if (onTargetCount > 0) { // even though we were on target at least once during a previous iteration
					onTargetCount = 0; // we reset the counter as we are not on target anymore
					System.out.println("Triple-check failed (shoulder moving).");
				} else {
					// we are definitely moving
				}
			}
			
			if (onTargetCount > MOVE_ON_TARGET_MINIMUM_COUNT) { // if we have met the minimum
				isMoving = false;
			}
			
			if (!isMoving) {
				System.out.println("You have reached the target (shoulder moving).");
				//shoulder.set(ControlMode.PercentOutput,0);
				if (isMovingUp) {
					stay();
				} else {
					stop();
					//stay();
				}
			}
		}
		return isMoving; 
	}
	
	public void moveUp() {
		
		// since we reset encoder on limit sensor it is always ok to go to zero.

		//if (hasBeenHomed) {
			//setPIDParameters();
			System.out.println("Moving Up");
			
			setNominalAndPeakOutputs(REDUCED_PCT_OUTPUT);

			tac = -ANGLE_TO_TRAVEL_TICKS; // because we cannot reach 0 reliably
			shoulder.set(ControlMode.Position,tac);
			
			isMoving = true;
			isMovingUp = true;
			onTargetCount = 0;
		//} else {
		//	System.out.println("You have not been home, your mother must be worried sick");
		//}

		hasBeenHomed = true; // we consider that we homed if we went up all the way at least once.
	}

	public void moveMidway() {
		
		if (hasBeenHomed) {
			//setPIDParameters();
			System.out.println("Moving Midway");
			
			setNominalAndPeakOutputs(REDUCED_PCT_OUTPUT); // we may need to check if we were up in which case we may want to reduce output

			//tac = ANGLE_TO_TRAVEL_TICKS / 2;
			tac = -ANGLE_TO_MIDWAY_TICKS;
			shoulder.set(ControlMode.Position,tac);
			
			isMoving = true;
			isMovingUp = true;
			onTargetCount = 0;
		} else {
			System.out.println("You have not been home, your mother must be worried sick");
		}
	}
	
	public void moveDown() {
		
		if (hasBeenHomed) {
			//setPIDParameters();
			System.out.println("Moving Down");
			
			setNominalAndPeakOutputs(SUPER_REDUCED_PCT_OUTPUT);
	
			tac = VIRTUAL_HOME_OFFSET_TICKS;
			shoulder.set(ControlMode.Position,tac);
			
			isMoving = true;
			isMovingUp = false;
			onTargetCount = 0;
		} else {
			System.out.println("You have not been home, your mother must be worried sick");
		}
	}

	public double getPosition() {
		return shoulder.getSelectedSensorPosition(PRIMARY_PID_LOOP) * GEAR_RATIO / TICKS_PER_REVOLUTION;
	}

	public double getEncoderPosition() {
		return shoulder.getSelectedSensorPosition(PRIMARY_PID_LOOP);
	}

	public boolean isMoving() {
		return isMoving;
	}
	
	public boolean isUp() {
		//return Math.abs(getEncoderPosition()) < ANGLE_TO_TRAVEL_TICKS * 1/3;
		return Math.abs(getEncoderPosition()) > ANGLE_TO_TRAVEL_TICKS * 2/3;
	}
	
	public boolean isDown() {
		//return Math.abs(getEncoderPosition()) > ANGLE_TO_TRAVEL_TICKS * 2/3;
		return Math.abs(getEncoderPosition()) < ANGLE_TO_TRAVEL_TICKS * 1/3;
	}
	
	public boolean isMidway() {
		return !isUp() && !isDown();
	}

	public void stay() {	 		
		isMoving = false;		
	}
	
	public void stop() {	 

		shoulder.set(ControlMode.PercentOutput, 0);
		
		isMoving = false;
		
		setNominalAndPeakOutputs(MAX_PCT_OUTPUT); // we undo what me might have changed
	}	
	
	private void setPIDParameters() {		
		shoulder.configAllowableClosedloopError(SLOT_0, TALON_TICK_THRESH, TALON_TIMEOUT_MS);
		
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
		
		shoulder.config_kP(SLOT_0, MOVE_PROPORTIONAL_GAIN, TALON_TIMEOUT_MS);
		shoulder.config_kI(SLOT_0, MOVE_INTEGRAL_GAIN, TALON_TIMEOUT_MS);
		shoulder.config_kD(SLOT_0, MOVE_DERIVATIVE_GAIN, TALON_TIMEOUT_MS);
		shoulder.config_kF(SLOT_0, 0, TALON_TIMEOUT_MS);
	}

	public void setNominalAndPeakOutputs(double peakOutput)
	{
		shoulder.configPeakOutputForward(peakOutput, TALON_TIMEOUT_MS);
		shoulder.configPeakOutputReverse(-peakOutput, TALON_TIMEOUT_MS);
		
		shoulder.configNominalOutputForward(0, TALON_TIMEOUT_MS);
		shoulder.configNominalOutputForward(0, TALON_TIMEOUT_MS);
	}
	
	// for debug purpose only
	public void joystickControl(Joystick joystick)
	{
		if (!isMoving) // if we are already doing a move we don't take over
		{
			shoulder.set(ControlMode.PercentOutput, -joystick.getY());
		}
	}	
	
	public double getTarget() {
		return tac;
	}

	// MAKE SURE THAT YOU ARE NOT IN A CLOSED LOOP CONTROL MODE BEFORE CALLING THIS METHOD.
	// OTHERWISE THIS IS EQUIVALENT TO MOVING TO THE DISTANCE TO THE CURRENT ZERO IN REVERSE! 
	public void resetEncoder() {
		shoulder.set(ControlMode.PercentOutput,0); // we stop AND MAKE SURE WE DO NOT MOVE WHEN SETTING POSITION
		shoulder.setSelectedSensorPosition(0, PRIMARY_PID_LOOP, TALON_TIMEOUT_MS); // we mark the virtual zero
	}

}
