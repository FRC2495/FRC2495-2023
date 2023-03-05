package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.net.PortForwarder;

import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.auton.CustomAuton;
import frc.robot.interfaces.*;
import frc.robot.sensors.*;
import frc.robot.subsystems.*;
//import frc.robot.util.*;
//import frc.robot.commands.gamepad.*;
import frc.robot.commands.indicator.*;
//import frc.robot.Ports;
//import frc.robot.util.Magic;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	// IMPORTANT MAKE SURE THAT THIS CONSTANT IS SET TO TRUE IF USING COMPETITION BOT!
	// use this constant to switch between competition and practice bot
	public static final boolean COMPETITION_BOT_CONFIG = true;

	public static OI oi;
	
	Command m_autonomousCommand;
	//Command gamepadRumbleUsingCameraCommand;
	Command indicatorTimedScrollRainbow;
	
	// choosers (for auton)
	
	public static final String AUTON_DO_NOTHING = "Do Nothing";
	public static final String AUTON_CUSTOM = "My Auto";
	private String autonSelected;
	private SendableChooser<String> autonChooser = new SendableChooser<>();

	public static final String GAME_PIECE_NONE = "None";
	public static final String GAME_PIECE_1_CONE = "1 Cone";
	public static final String GAME_PIECE_2_CONES = "2 Cones";
	private String gamePieceSelected;
	private SendableChooser<String> gamePieceChooser = new SendableChooser<>();
	
	public static final String START_POSITION_1 = "Starting Position 1";
	public static final String START_POSITION_2 = "Starting Position 2";
	public static final String START_POSITION_3 = "Starting Position 3";
	public static final String START_POSITION_4 = "Starting Position 4";
	public static final String START_POSITION_5 = "Starting Position 5";
	public static final String START_POSITION_6 = "Starting Position 6";
	private String startPosition;
	private SendableChooser<String> startPositionChooser = new SendableChooser<>();

	public static final String MAIN_TARGET_CONE_NODE = "Cone Node";
	public static final String MAIN_TARGET_TWO_CONE_NODES = "Two Cone Nodes";
	public static final String MAIN_TARGET_CHARGING_STATION = "Charging Station";
	public static final String MAIN_TARGET_NOWHERE = "Nowhere";
	private String mainTarget;
	private SendableChooser<String> mainTargetChooser = new SendableChooser<>();
	
	public static final String CAMERA_OPTION_USE_ALWAYS = "Always";
	public static final String CAMERA_OPTION_USE_OPEN_LOOP_ONLY = "Open Loop Only";
	public static final String CAMERA_OPTION_USE_CLOSED_LOOP_ONLY = "Closed Loop Only";
	public static final String CAMERA_OPTION_USE_NEVER = "Never";
	private String cameraOption;
	private SendableChooser<String> cameraOptionChooser = new SendableChooser<>();
	
	public static final String SONAR_OPTION_USE_ALWAYS = "Always";
	public static final String SONAR_OPTION_USE_RELEASE_ONLY = "Release Only";
	public static final String SONAR_OPTION_USE_GRASP_ONLY = "Grasp Only";
	public static final String SONAR_OPTION_USE_NEVER = "Never";
	private String sonarOption;
	private SendableChooser<String> sonarOptionChooser = new SendableChooser<>();
	
	public static final String CLAW_OPTION_RELEASE = "Release";
	public static final String CLAW_OPTION_DONT_RELEASE = "Don't Release"; 
	private String releaseSelected;
	private SendableChooser<String> releaseChooser = new SendableChooser<>();

	public static final String AUTON_OPTION_JUST_DROP_CONE = "Just Drop Cone";
	public static final String AUTON_OPTION_ALSO_DOCK = "Also Dock";
	public static final String AUTON_OPTION_LEAVE_COMMUNITY = "Leave Community";
	private String autonOption;
	private SendableChooser<String> autonOptionChooser = new SendableChooser<>();

	// sensors
	
	public static ICamera camera;
	
	public static ADXRS450_Gyro gyro; // gyro
	static boolean hasGyroBeenManuallyCalibratedAtLeastOnce = false;
	
	public static Sonar sonar;
	
	public static HMAccelerometer accelerometer;

	public static PressureSensor pressureSensor;

	// public static ColorSensor colorSensor;
	
	// motorized devices
	
	public static IDrivetrain drivetrain;

	WPI_TalonSRX frontLeft;
	WPI_TalonSRX frontRight;
	BaseMotorController rearLeft; 
	BaseMotorController rearRight;
	//CANSparkMax frontLeft;
	//CANSparkMax frontRight;
	//CANSparkMax rearLeft;
	//CANSparkMax rearRight;

	//public static /*I*/Grasper grasper;

	//public static /*I*/Shooter shooter;

	//public static /*I*/Feeder feeder;

	//BaseMotorController shooterMotor;

	//BaseMotorController feederMotor;

	public static IJack jack;

	WPI_TalonSRX jack_master;

	public static IShoulder shoulder;
	
	WPI_TalonSRX shoulder_master;
	//BaseMotorController shoulder_follower;

	//WPI_TalonSRX grasperMotor;

	//public BaseMotorController spinnerMotor;
	//public static Spinner spinnerWheel;

	WPI_TalonSRX arm_master;
	//BaseMotorController arm_follower;

	public static IArm arm;

	WPI_TalonSRX rotator_master;
	//BaseMotorController rotator_follower;

	public static IRotator rotator;

	
	// pneumatic devices
	
	Compressor compressor; // the compressor's lifecycle needs to be the same as the robot

	public static Gearbox gearbox;

	public static Claw claw;

	public static Brake brake;

	//public static WinchStopper winchStopperControl; 
	//public static WinchLock winchLockControl;


	// misc

	public static Indicator indicator;

	//public static GameData gameData;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		// Port forwarders for LimeLight
		// Do not place these function calls in any periodic functions
		PortForwarder.add(5800, "limelight.local", 5800);
		PortForwarder.add(5801, "limelight.local", 5801);
		PortForwarder.add(5802, "limelight.local", 5802);
		PortForwarder.add(5803, "limelight.local", 5803);
		PortForwarder.add(5804, "limelight.local", 5804);
		PortForwarder.add(5805, "limelight.local", 5805);

		// choosers (for auton)
		
		autonChooser.setDefaultOption("Do Nothing", AUTON_DO_NOTHING);
		autonChooser.addOption("My Auto", AUTON_CUSTOM);
		SmartDashboard.putData("Auto choices", autonChooser);

		gamePieceChooser.setDefaultOption("None", GAME_PIECE_NONE);
		gamePieceChooser.addOption("1 Cone", GAME_PIECE_1_CONE);
		gamePieceChooser.addOption("2 Cones", GAME_PIECE_2_CONES);
		SmartDashboard.putData("Game piece choices", gamePieceChooser);

		startPositionChooser.setDefaultOption("Starting Position 1", START_POSITION_1);
		startPositionChooser.addOption("Starting Position 2", START_POSITION_2);
		startPositionChooser.addOption("Starting Position 3", START_POSITION_3);
		startPositionChooser.addOption("Starting Position 4", START_POSITION_4);
		startPositionChooser.addOption("Starting Position 5", START_POSITION_5);
		startPositionChooser.addOption("Starting Position 6", START_POSITION_6);
		SmartDashboard.putData("Start positions", startPositionChooser);

		mainTargetChooser.setDefaultOption("To Nowhere", MAIN_TARGET_NOWHERE);
		mainTargetChooser.addOption("Cone Node", MAIN_TARGET_CONE_NODE);
		mainTargetChooser.addOption("Two Cone Nodes", MAIN_TARGET_TWO_CONE_NODES);
		mainTargetChooser.addOption("Charging Station", MAIN_TARGET_CHARGING_STATION);
		SmartDashboard.putData("Main targets", mainTargetChooser);
		
		cameraOptionChooser.setDefaultOption("Always", CAMERA_OPTION_USE_ALWAYS);
		cameraOptionChooser.addOption("Open Loop Only", CAMERA_OPTION_USE_OPEN_LOOP_ONLY);
		cameraOptionChooser.addOption("Closed Loop Only", CAMERA_OPTION_USE_CLOSED_LOOP_ONLY);
		cameraOptionChooser.addOption("Never", CAMERA_OPTION_USE_NEVER);		
		SmartDashboard.putData("Camera options", cameraOptionChooser);
		
		sonarOptionChooser.setDefaultOption("Always", SONAR_OPTION_USE_ALWAYS);
		sonarOptionChooser.addOption("Release Only", SONAR_OPTION_USE_RELEASE_ONLY);
		sonarOptionChooser.addOption("Grasp Only", SONAR_OPTION_USE_GRASP_ONLY);		
		sonarOptionChooser.addOption("Never", SONAR_OPTION_USE_NEVER);
		SmartDashboard.putData("Sonar options", sonarOptionChooser);
		
		releaseChooser.setDefaultOption("Release", CLAW_OPTION_RELEASE);
		releaseChooser.addOption("Don't release", CLAW_OPTION_DONT_RELEASE);
		SmartDashboard.putData("Release options", releaseChooser);

		autonOptionChooser.setDefaultOption("Just Drop Cone", AUTON_OPTION_JUST_DROP_CONE);
		autonOptionChooser.addOption("Also Dock", AUTON_OPTION_ALSO_DOCK);
		autonOptionChooser.addOption("Leave Community", AUTON_OPTION_LEAVE_COMMUNITY);
	
		SmartDashboard.putData("Auton options", autonOptionChooser);		


		// sensors
			
		sonar = new Sonar(Ports.Analog.SONAR); 
			
		gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0); // we want to instantiate before we pass to drivetrain	

		gyro.calibrate(); 
		gyro.reset();

		//camera = new HMCamera("GRIP/myContoursReport");
		camera = new LimelightCamera();

		accelerometer = new HMAccelerometer();

		pressureSensor = new PressureSensor();

		// colorSensor = new ColorSensor();


		// motorized devices

		frontLeft = new WPI_TalonSRX(Ports.CAN.LEFT_FRONT);
		frontRight = new WPI_TalonSRX(Ports.CAN.RIGHT_FRONT);
		rearLeft = new WPI_TalonFX(Ports.CAN.LEFT_REAR);
		rearRight = new WPI_TalonFX(Ports.CAN.RIGHT_REAR);
		//frontLeft = new CANSparkMax(Ports.CAN.LEFT_FRONT, MotorType.kBrushless);
		//frontRight = new CANSparkMax(Ports.CAN.RIGHT_FRONT, MotorType.kBrushless);
		//rearLeft  = new CANSparkMax(Ports.CAN.LEFT_REAR, MotorType.kBrushless);
		//rearRight = new CANSparkMax(Ports.CAN.RIGHT_REAR, MotorType.kBrushless);

		jack_master = new WPI_TalonSRX(Ports.CAN.JACK_MASTER);

		shoulder_master = new WPI_TalonSRX(Ports.CAN.SHOULDER_MASTER);
		//shoulder_follower = new WPI_TalonSRX(Ports.CAN.SHOULDER_FOLLOWER);

		//grasperMotor = new WPI_TalonSRX(Ports.CAN.GRASPER);

		drivetrain = new Drivetrain(frontLeft, frontRight, rearLeft, rearRight, gyro, this, camera, accelerometer);	
		//drivetrain = new SparkMaxDrivetrain(frontLeft, frontRight, rearLeft, rearRight, gyro, this, camera);	

		jack = new Jack(jack_master/*, jack_follower*/, this);
		
		shoulder = new Shoulder(shoulder_master/*, shoulder_follower*/, this);
		
		//grasper = new Grasper(grasperMotor, this);

		//shooterMotor = new WPI_TalonSRX(Ports.CAN.SHOOTER);
		//shooter = new Shooter(shooterMotor, this);
		
		//feederMotor = new WPI_TalonSRX(Ports.CAN.FEEDER);
		//feeder = new Feeder(feederMotor, this);

		//spinnerMotor = new WPI_TalonSRX(Ports.CAN.SPINNER);
		//spinnerWheel = new Spinner(spinnerMotor,this); 

		arm_master = new WPI_TalonSRX(Ports.CAN.ARM_MASTER);
		//arm_follower = new WPI_TalonSRX(Ports.CAN.ARM_FOLLOWER);

		arm = new Arm(arm_master, /*arm_follower,*/ this);

		rotator_master = new WPI_TalonSRX(Ports.CAN.ROTATOR_MASTER);
		//rotator_follower = new WPI_TalonSRX(Ports.CAN.ROTATOR_FOLLOWER);

		rotator = new Rotator(rotator_master, /*rotator_follower,*/ this);
		

		// pneumatic devices

		compressor = new Compressor();
		compressor.checkCompressor();
   
		//shooterPusher = new ShooterPusher();

		gearbox = new Gearbox();

		claw = new Claw();

		brake = new Brake();

		//winchStopperControl = new WinchStopper();
		//winchLockControl = new WinchLock();


		// misc

		indicator = new Indicator(camera);

		//gameData = new GameData();
		
		// OI must be constructed after subsystems. If the OI creates Commands
		//(which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		//gamepadRumbleUsingCameraCommand = new GamepadRumbleUsingCamera(); // must be created after OI
		//gamepadRumbleUsingCameraCommand.start();

		indicatorTimedScrollRainbow = new IndicatorTimedScrollRainbow(1);
		indicatorTimedScrollRainbow.schedule();
	} 

	/**
	 * This function is called every robot packet, no matter the mode. Use
	 * this for items like diagnostics that you want ran during disabled,
	 * autonomous, teleoperated and test.
	 *
	 * <p>This runs after the mode specific periodic functions, but before
	 * LiveWindow and SmartDashboard integrated updating.
	 */
	@Override
	public void robotPeriodic() {
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings and commands.
	 */
	@Override
	public void autonomousInit() {
		autonSelected = autonChooser.getSelected();
		System.out.println("Auton selected: " + autonSelected);	

		gamePieceSelected = gamePieceChooser.getSelected();
		System.out.println("Game piece selected: " + gamePieceSelected);		

		startPosition = startPositionChooser.getSelected();
		System.out.println("Start position: " + startPosition);

		mainTarget = mainTargetChooser.getSelected();
		System.out.println("Main target: " + mainTarget);
		
		cameraOption = cameraOptionChooser.getSelected();
		System.out.println("Camera option: " + cameraOption);
		
		sonarOption = sonarOptionChooser.getSelected();
		System.out.println("Sonar option: " + sonarOption);
		
		releaseSelected = releaseChooser.getSelected();
		System.out.println("Release chosen: " + releaseSelected);

		autonOption = autonOptionChooser.getSelected();
		System.out.println("Auton option: " + autonOption);
		
		//At this point we should know what auto run, where we started, and where our plates are located.
		//So we are ready for autonomousPeriodic to be called.
		updateToSmartDash();

		switch (autonSelected) {
			case Robot.AUTON_CUSTOM:
				m_autonomousCommand = new CustomAuton(gamePieceSelected, startPosition, mainTarget, cameraOption, sonarOption, autonOption);
				break;

			case Robot.AUTON_DO_NOTHING:
				m_autonomousCommand = null;
			
				break;
				
			default:
				// nothing
				break;
		} // end switch

		LimelightCamera.setLedMode(ICamera.LedMode.PIPELINE);

		SwitchedCamera.setUsbCamera(Ports.UsbCamera.BOTTOM_CAMERA);
	
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.schedule();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		CommandScheduler.getInstance().run();

		//camera.acquireTargets(false);
		updateToSmartDash();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}

		LimelightCamera.setLedMode(ICamera.LedMode.PIPELINE);

		SwitchedCamera.setUsbCamera(Ports.UsbCamera.TOP_CAMERA);

		//gamepadRumbleUsingCameraCommand.start();

		
		gearbox.setGear(Gearbox.Gear.LOW);
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		camera.acquireTargets(false);
		
		CommandScheduler.getInstance().run();
		
		camera.acquireTargets(false);

		// colorSensor.updateColorSensor();

		updateToSmartDash();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		//gamepadRumbleUsingCameraCommand.cancel();

		LimelightCamera.setLedMode(ICamera.LedMode.FORCE_OFF);

		SwitchedCamera.setUsbCamera(Ports.UsbCamera.BOTTOM_CAMERA);
	}

	@Override
	public void disabledPeriodic() {	
		camera.acquireTargets(false);

		CommandScheduler.getInstance().run();
		
		camera.acquireTargets(false);

		// colorSensor.updateColorSensor();

		updateToSmartDash();

		indicator.updateRainbow();

		// https://www.chiefdelphi.com/t/commandxboxcontroller-lefttrigger-and-righttrigger-not-triggering/427504/31
		if (!DriverStation.getJoystickIsXbox(Ports.USB.GAMEPAD)) {
        
			DriverStation.reportError("XBOX 360 CONTROLLER NOT DETECTED! RESCAN USB DEVICES (F1)", false);
		}		
	}
	
	public void updateToSmartDash()
	{
		// Send Gyro val to Dashboard
		SmartDashboard.putNumber("Gyro Value", gyro.getAngle());
		SmartDashboard.putNumber("Pressure", pressureSensor.getPressurePSI());
			
		SmartDashboard.putNumber("Right Value", drivetrain.getRightPosition());
		SmartDashboard.putNumber("Left Value", drivetrain.getLeftPosition());
		SmartDashboard.putNumber("Right Enc Value", drivetrain.getRightEncoderPosition());
		SmartDashboard.putNumber("Left Enc Value", drivetrain.getLeftEncoderPosition());
		SmartDashboard.putNumber("Right Enc Velocity", drivetrain.getRightEncoderVelocity());
		SmartDashboard.putNumber("Left Enc Velocity", drivetrain.getLeftEncoderVelocity());
		SmartDashboard.putBoolean("isMoving?", drivetrain.isMoving());
		SmartDashboard.putBoolean("isTurning?", drivetrain.isTurning());
		SmartDashboard.putBoolean("isStalled?", drivetrain.isStalled());
		SmartDashboard.putBoolean("isMovingUsingCamera?", drivetrain.isMovingUsingCamera());
		SmartDashboard.putBoolean("isTurningUsingCamera?", drivetrain.isTurningUsingCamera());
		
		SmartDashboard.putBoolean("isCompromised?", DriverStation.isDisabled());
		
		SmartDashboard.putNumber("Distance to Target", camera.getDistanceToCompositeTargetUsingVerticalFov());
		SmartDashboard.putNumber("Angle to Target", camera.getAngleToTurnToCompositeTarget());
		SmartDashboard.putNumber("Distance to Target Using Horizontal FOV", camera.getDistanceToCompositeTargetUsingHorizontalFov());
		SmartDashboard.putNumber("Filtered Distance to Target", camera.getFilteredDistanceToCompositeTarget());
		SmartDashboard.putNumber("Vertical Offset to Target", camera.getVerticalOffsetToCompositeTarget());
		SmartDashboard.putNumber("Filtered Vertical Offset to Target", camera.getFilteredVerticalOffsetToCompositeTarget());
		
		SmartDashboard.putBoolean("Jack Limit Switch", jack.getLimitSwitchState());
		SmartDashboard.putBoolean("Jack Forward Limit Switch", jack.getForwardLimitSwitchState());
		SmartDashboard.putNumber("Jack Position", jack.getPosition());
		SmartDashboard.putNumber("Jack Enc Position", jack.getEncoderPosition());
		SmartDashboard.putBoolean("Jack IsMoving?", jack.isMoving());
		SmartDashboard.putNumber("Jack Target", jack.getTarget());
		SmartDashboard.putBoolean("Jack isStalled?", jack.isStalled());
		SmartDashboard.putBoolean("Jack isDown", jack.isDown());
		SmartDashboard.putBoolean("Jack isMidway", jack.isMidway());
		SmartDashboard.putBoolean("Jack isUp", jack.isUp());
		SmartDashboard.putBoolean("Jack isDangerous", jack.isDangerous());

		SmartDashboard.putBoolean("Shoulder Limit Switch", shoulder.getLimitSwitchState());
		SmartDashboard.putBoolean("Shoulder Forward Limit Switch", shoulder.getForwardLimitSwitchState());
		SmartDashboard.putNumber("Shoulder Position", shoulder.getPosition());
		SmartDashboard.putNumber("Shoulder Enc Position", shoulder.getEncoderPosition());
		SmartDashboard.putBoolean("Shoulder IsMoving?", shoulder.isMoving());
		SmartDashboard.putNumber("Shoulder Target", shoulder.getTarget());
		SmartDashboard.putBoolean("Shoulder isStalled?", shoulder.isStalled());
		SmartDashboard.putBoolean("Shoulder isDown", shoulder.isDown());
		SmartDashboard.putBoolean("Shoulder isMidway", shoulder.isMidway());
		SmartDashboard.putBoolean("Shoulder isUp", shoulder.isUp());
		SmartDashboard.putBoolean("Shoulder isDangerous", shoulder.isDangerous());
		
		SmartDashboard.putBoolean("Gyro Manually Calibrated?",hasGyroBeenManuallyCalibratedAtLeastOnce);
		
		SmartDashboard.putNumber("AccelZ", accelerometer.getAccelZ());
		SmartDashboard.putNumber("FilteredAccelZ", accelerometer.getFilteredAccelZ());
		SmartDashboard.putNumber("Tilt", accelerometer.getTilt());
		SmartDashboard.putNumber("FilteredTilt", accelerometer.getFilteredTilt());
		SmartDashboard.putNumber("AccurateTilt", accelerometer.getAccurateTilt());
		SmartDashboard.putBoolean("isFlat", accelerometer.isFlat());
		
		SmartDashboard.putNumber("Range to target", sonar.getRangeInInches());
		SmartDashboard.putNumber("Sonar Voltage", sonar.getVoltage()); 
		
		//SmartDashboard.putBoolean("Grasper IsGrasping?", grasper.isGrasping());
		//SmartDashboard.putBoolean("Grasper IsReleasing?", grasper.isReleasing());

		//SmartDashboard.putBoolean("Spinner IsSpinning?", spinnerWheel.isSpinning());

		//SmartDashboard.putBoolean("Feeder IsFeeding?", feeder.isFeeding());

		//SmartDashboard.putBoolean("Shooter IsShooting?", shooter.isShooting());
		//SmartDashboard.putNumber("Shooter Enc Velocity", shooter.getEncoderVelocity());
		//SmartDashboard.putNumber("Shooter Rpm", shooter.getRpm());
		//SmartDashboard.putNumber("Shooter Preset Rpm", shooter.getPresetRpm());

		//double verticalOffset = Robot.camera!=null?Robot.camera.getFilteredVerticalOffsetToCompositeTarget():10;

		//double magic_rpm = Magic.getRpm(verticalOffset);
		
		//SmartDashboard.putNumber("Shooter Magic Rpm", magic_rpm);
		
		SmartDashboard.putString("Auton selected", autonChooser.getSelected());	
		SmartDashboard.putString("Game piece", gamePieceChooser.getSelected());
		SmartDashboard.putString("Start position", startPositionChooser.getSelected());
		SmartDashboard.putString("Main target", mainTargetChooser.getSelected());
		SmartDashboard.putString("Camera option", cameraOptionChooser.getSelected());
		SmartDashboard.putString("Sonar option", sonarOptionChooser.getSelected());
		SmartDashboard.putString("Release chosen", releaseChooser.getSelected());
		SmartDashboard.putString("Auton option", autonOptionChooser.getSelected());

		/*SmartDashboard.putNumber("Color Sensor Red", colorSensor.getRed());
		SmartDashboard.putNumber("Color Sensor Blue", colorSensor.getBlue());
		SmartDashboard.putNumber("Color Sensor Green", colorSensor.getGreen());
		SmartDashboard.putString("Color Detected", colorSensor.getDetectedColor().toString());
		*/
		//SmartDashboard.putString("COLOR TO DETECT",gameData.convertSpecifiedColorToColorToDetect().toString());

		//SmartDashboard.putNumber("Winch Encoder Value", winchControl.getEncoderPosition());
		//SmartDashboard.putBoolean("Winch Forward Limit Switch", winchControl.getForwardLimitSwitchState());

		SmartDashboard.putString("Gearbox Position", gearbox.getGear().toString());

		SmartDashboard.putString("Claw Position", claw.getPosition().toString());

		SmartDashboard.putString("Brake Position", brake.getPosition().toString());

		SmartDashboard.putBoolean("Arm Limit Switch", arm.getLimitSwitchState());
		SmartDashboard.putBoolean("Arm Reverse Limit Switch", arm.getReverseLimitSwitchState());
		SmartDashboard.putNumber("Arm Enc Position", arm.getEncoderPosition());
		SmartDashboard.putBoolean("Arm IsMoving?", arm.isMoving());
		SmartDashboard.putNumber("Arm Target", arm.getTarget());
		SmartDashboard.putBoolean("Arm isStalled?", arm.isStalled());
		SmartDashboard.putBoolean("Arm isRetracted", arm.isRetracted());
		SmartDashboard.putBoolean("Arm isMidway", arm.isMidway());
		SmartDashboard.putBoolean("Arm isExtended", arm.isExtended());
		SmartDashboard.putBoolean("Arm isDangerous", arm.isDangerous());
		SmartDashboard.putBoolean("Arm isDangerousForFloor", arm.isDangerousForShoulderAtFloor());

		SmartDashboard.putBoolean("Rotator Limit Switch", rotator.getLimitSwitchState());
		SmartDashboard.putBoolean("Rotator Reverse Limit Switch", rotator.getReverseLimitSwitchState());
		SmartDashboard.putNumber("Rotator Enc Position", rotator.getEncoderPosition());
		SmartDashboard.putBoolean("Rotator IsMoving?", rotator.isMoving());
		SmartDashboard.putNumber("Rotator Target", rotator.getTarget());
		SmartDashboard.putBoolean("Rotator isStalled?", rotator.isStalled());
		SmartDashboard.putBoolean("Rotator isFlipped", rotator.isFlipped());
		SmartDashboard.putBoolean("Rotator isSideway", rotator.isSideway());
		SmartDashboard.putBoolean("Rotator isRested", rotator.isRested());
		SmartDashboard.putBoolean("Rotator isDangerous", rotator.isDangerous());
		SmartDashboard.putBoolean("Rotator isDangerousForFloor", rotator.isDangerous());
	}

	public static void setGyroHasBeenManuallyCalibratedAtLeastOnce(boolean flag) {
		hasGyroBeenManuallyCalibratedAtLeastOnce = flag;
	}
}