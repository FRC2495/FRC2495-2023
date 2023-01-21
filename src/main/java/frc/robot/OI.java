// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.*;
import frc.robot.commands.drivetrain.*;
import frc.robot.commands.hinge.*;
//import frc.robot.commands.grasper.*;
//import frc.robot.commands.feeder.*;
//import frc.robot.commands.shooter.*;
//import frc.robot.commands.arms.*;
//import frc.robot.commands.elbows.*;
//import frc.robot.commands.conditional.*;
import frc.robot.commands.groups.*;
import frc.robot.commands.camera.*;
import frc.robot.Ports;
import frc.robot.util.DPadButton;
//import frc.robot.ControllerBase;
import frc.robot.util.GamepadAxis;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released  and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());


	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public JoystickButton joyLeftBtn1;
	public JoystickButton joyLeftBtn2;
	public JoystickButton joyLeftBtn3;
	public JoystickButton joyLeftBtn4;
	public JoystickButton joyLeftBtn5;
	public JoystickButton joyLeftBtn6;
	public JoystickButton joyLeftBtn7;
	public JoystickButton joyLeftBtn8;
	public JoystickButton joyLeftBtn9;
	public JoystickButton joyLeftBtn10;
	public JoystickButton joyLeftBtn11;
	public Joystick joyLeft;

	public JoystickButton joyRightBtn1;
	public JoystickButton joyRightBtn2;
	public JoystickButton joyRightBtn3;
	public JoystickButton joyRightBtn4;
	public JoystickButton joyRightBtn5;
	public JoystickButton joyRightBtn6;
	public JoystickButton joyRightBtn7;
	public JoystickButton joyRightBtn8;
	public JoystickButton joyRightBtn9;
	public JoystickButton joyRightBtn10;
	public JoystickButton joyRightBtn11;
	public Joystick joyRight;

	public JoystickButton gamepadA;
	public JoystickButton gamepadB;
	public JoystickButton gamepadX;
	public JoystickButton gamepadY;
	public JoystickButton gamepadLB;
	public JoystickButton gamepadRB;
	public JoystickButton gamepadBack;
	public JoystickButton gamePadStart;
	public JoystickButton gamepadLS;
	public JoystickButton gamepadRS;
	public GamepadAxis gamepadLXp;
	public GamepadAxis gamepadLXn;
	public GamepadAxis gamepadLYp;
	public GamepadAxis gamepadLYn;
	public GamepadAxis gamepadLT;
	public GamepadAxis gamepadRT;
	public GamepadAxis gamepadRXp;
	public GamepadAxis gamepadRXn;
	public GamepadAxis gamepadRYp;
	public GamepadAxis gamepadRYn;
	public Joystick gamepad;

	public DPadButton dpadUp;
	public DPadButton dpadDown;
	public DPadButton dpadLeft;
	public DPadButton dpadRight;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


	public OI() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

		gamepad = new Joystick(Ports.USB.GAMEPAD);

		gamepadRYp = new GamepadAxis(gamepad, ControllerBase.GamepadAxes.RY);
		//gamepadRYp.whenPressed(new RearArmsExtend());
		//gamepadRYp.whenPressed(new RearArmsRetractWithStallDetection());

		gamepadRYn = new GamepadAxis(gamepad, ControllerBase.GamepadAxes.RY,false);
		//gamepadRYn.whenPressed(new RearArmsRetract());
		//gamepadRYn.whenPressed(new RearArmsExtendWithStallDetection());

		gamepadRXp = new GamepadAxis(gamepad, ControllerBase.GamepadAxes.RX);
		//gamepadRXp.whenPressed(new RearElbowsOpen());
		//gamepadRXp.whenPressed(new RearElbowsMidwayWithStallDetection());

		gamepadRXn = new GamepadAxis(gamepad, ControllerBase.GamepadAxes.RX,false);
		//gamepadRXn.whenPressed(new RearElbowsCloseWithStallDetection());

		gamepadRT = new GamepadAxis(gamepad, ControllerBase.GamepadAxes.RT);
		//gamepadRT.whenPressed(new CameraSetLedMode(ICamera.LedMode.FORCE_ON));
		gamepadRT.onTrue(new HingeMoveDown());

		gamepadLT = new GamepadAxis(gamepad, ControllerBase.GamepadAxes.LT);
		//gamepadLT.whenPressed(new CameraSetLedMode(ICamera.LedMode.FORCE_OFF));
		//gamepadLT.whenPressed(new FrontArmsRetract());
		//gamepadLT.whileHeld(new FeederFeed());

		gamepadLYp = new GamepadAxis(gamepad, ControllerBase.GamepadAxes.LY);
		//gamepadLYp.whenPressed(new FrontArmsExtend());
		//gamepadLYp.whenPressed(new FrontArmsRetractWithStallDetection());
		//gamepadLYp.whenPressed(new SpinnerColorMatch()); // pulling back towards operator

		gamepadLYn = new GamepadAxis(gamepad, ControllerBase.GamepadAxes.LY,false);
		//gamepadLYn.whenPressed(new FrontArmsRetract());
		//gamepadLYn.whenPressed(new FrontArmsExtendWithStallDetection());
		//gamepadLYn.whenPressed(new SpinnerSpinThrice()); // pushing forward

		gamepadLXp = new GamepadAxis(gamepad, ControllerBase.GamepadAxes.LX);
		//gamepadLXp.whenPressed(new FrontElbowsCloseWithStallDetection());

		gamepadLXn = new GamepadAxis(gamepad, ControllerBase.GamepadAxes.LX,false);
		//gamepadLXn.whenPressed(new FrontElbowsOpen());
		//gamepadLXn.whenPressed(new FrontElbowsMidwayWithStallDetection());

		
		gamepadRS = new JoystickButton(gamepad, ControllerBase.GamepadButtons.RS);

		gamepadLS = new JoystickButton(gamepad, ControllerBase.GamepadButtons.LS);

		gamePadStart = new JoystickButton(gamepad, ControllerBase.GamepadButtons.START);
		//gamePadStart.whenPressed(new HingeAndGrasperAndSpinnerStop());
		//gamePadStart.whenPressed(new HingeAndGrasperStop());
		//gamePadStart.whenPressed(new HingeAndGrasperAndFeederAndShooterStop());
		gamePadStart.onTrue(new AlmostEverythingStop());

		gamepadBack = new JoystickButton(gamepad, ControllerBase.GamepadButtons.BACK);
		gamepadBack.onTrue(new FullCalibrateAndReset());

		gamepadRB = new JoystickButton(gamepad, ControllerBase.GamepadButtons.RB);
		//gamepadRB.whenPressed(new HingeMoveMidway());
		gamepadRB.onTrue(new HingeMoveUp());

		gamepadLB = new JoystickButton(gamepad, ControllerBase.GamepadButtons.LB);
		//gamepadLB.whileHeld(new SpinnerSpin());
		//gamepadLB.whenPressed(new SpinnerRaiserUp());
		//gamepadLB.whenPressed(new IfNuclearOptionEnabled(new Climb(), new DoNothing()));
		
		gamepadY = new JoystickButton(gamepad, ControllerBase.GamepadButtons.Y);
		//gamepadY.whenPressed(new CameraSetLedMode(ICamera.LedMode.FORCE_ON));	
		//gamepadY.whileHeld(new WinchWinchStopperMagicWinchUp());
		//gamepadY.whileHeld(new ShooterShootHigh());

		gamepadX = new JoystickButton(gamepad, ControllerBase.GamepadButtons.X);
		//gamepadX.whileHeld(new WinchWinchStopperMagicWinchDown());
		//gamepadX.whileHeld(new FeederFeed());
		//gamepadX.whileHeld(new ShooterShootLow());

		gamepadB = new JoystickButton(gamepad, ControllerBase.GamepadButtons.B);
		//gamepadB.whileHeld(new GrasperRelease());

		gamepadA = new JoystickButton(gamepad, ControllerBase.GamepadButtons.A);
		//gamepadA.whileHeld(new GrasperGrasp());


		joyRight = new Joystick(Ports.USB.RIGHT);

		joyRightBtn11 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN11); 
		//joyRightBtn11.whileHeld(new RearArmsJoystickControl());
	
		joyRightBtn10 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN10);
		//joyRightBtn10.whileHeld(new FrontArmsJoystickControl());

		joyRightBtn9 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN9);
		//joyRightBtn9.whenPressed(new WinchStopperSetStop());
		//joyRightBtn9.whenPressed(new WinchLockWinchStopperSetLockedAndStop());
		//joyRightBtn9.whileHeld(new RearElbowsJoystickControl());

		joyRightBtn8 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN8);
		//joyRightBtn8.whenPressed(new WinchStopperSetFree());
		//joyRightBtn8.whenPressed(new WinchLockWinchStopperSetUnlockedAndFree());
		//joyRightBtn8.whileHeld(new FrontElbowsJoystickControl());
		
		joyRightBtn7 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN7);
		joyRightBtn7.onTrue(new DrivetrainStop());

		joyRightBtn6 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN6);
		joyRightBtn6.onTrue(new DrivetrainResetEncoders());

		joyRightBtn5 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN5);
		//joyRightBtn5.whenPressed(new DrivetrainMoveUsingCameraPidController(LimelightCamera.OFFSET_CAMERA_TARGET_INCHES));
		//final int MAGIC_DISTANCE_INCHES = 40;
		//joyRightBtn5.whenPressed(new DrivetrainDriveUsingCamera(Robot.camera.getOffsetBetweenCameraAndTarget() + MAGIC_DISTANCE_INCHES));
		//joyRightBtn5.whileHeld(new ShooterShootCustom(3200.0*1.2));
		//joyRightBtn5.whileHeld(new ShooterShootUsingCamera());

		joyRightBtn4 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN4);
		joyRightBtn4.onTrue(new DrivetrainTurnUsingCameraPidController());
		//joyRightBtn4.whenPressed(new DrivetrainTurnAngleFromCameraUsingPidController());

		joyRightBtn3 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN3);

		joyRightBtn2 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN2);
		joyRightBtn2.onTrue(new SwitchedCameraSetUsbCamera(Ports.UsbCamera.SHOOTER_CAMERA));

		joyRightBtn1 = new JoystickButton(joyRight, ControllerBase.JoystickButtons.BTN1);
		//joyRightBtn1.whenPressed(new GearboxSetGearHigh());


		joyLeft = new Joystick(Ports.USB.LEFT);

		joyLeftBtn11 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN11);
		//joyLeftBtn11.whileHeld(new WinchJoystickControl());
		//joyLeftBtn11.whileHeld(new WinchWinchStopperJoystickControl());
		//joyLeftBtn11.whileHeld(new ShooterJoystickControl());
		
		joyLeftBtn10 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN10);
		//joyLeftBtn10.whileHeld(new GrasperJoystickControl());
		//joyLeftBtn10.whileHeld(new FeederJoystickControl());

		joyLeftBtn9 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN9);
		joyLeftBtn9.whileTrue(new HingeJoystickControl());

		joyLeftBtn8 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN8);
		//joyLeftBtn8.whileHeld(new GrasperJoystickControl());

		joyLeftBtn7 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN7);
		joyLeftBtn7.onTrue(new DrivetrainStop());

		joyLeftBtn6 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN6);
		joyLeftBtn6.onTrue(new DrivetrainAndGyroReset());

		joyLeftBtn5 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN5);
		joyLeftBtn5.onTrue(new DrivetrainTurnAngleUsingPidController(90));

		joyLeftBtn4 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN4);
		joyLeftBtn4.onTrue(new DrivetrainTurnAngleUsingPidController(-90));

		joyLeftBtn3 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN3);
		joyLeftBtn3.onTrue(new DrivetrainMoveDistance(50));

		joyLeftBtn2 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN2);
		joyLeftBtn2.onTrue(new SwitchedCameraSetUsbCamera(Ports.UsbCamera.GRASPER_CAMERA));

		joyLeftBtn1 = new JoystickButton(joyLeft, ControllerBase.JoystickButtons.BTN1);
		//joyLeftBtn1.whenPressed(new GearboxSetGearLow());


		dpadUp = new DPadButton(gamepad,DPadButton.Direction.UP);
		//dpadUp.whileHeld(new ShooterShootUsingCamera());

		dpadDown = new DPadButton(gamepad,DPadButton.Direction.DOWN);
		//dpadDown.whileHeld(new ShooterShootPreset());

		dpadLeft = new DPadButton(gamepad,DPadButton.Direction.LEFT);
		//dpadLeft.whenPressed(new ShooterDecreasePresetRpm());

		dpadRight = new DPadButton(gamepad,DPadButton.Direction.RIGHT);
		//dpadRight.whenPressed(new ShooterIncreasePresetRpm());


		// DEFAULT COMMANDS

		Robot.drivetrain.setDefaultCommand(new DrivetrainJoystickControl());


		// SmartDashboard Buttons

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
	}

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
	public Joystick getLeftJoystick() {
		return joyLeft;
	}

	public Joystick getRightJoystick() {
		return joyRight;
	}

	public Joystick getGamepad() {
		return gamepad;
	}
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}

