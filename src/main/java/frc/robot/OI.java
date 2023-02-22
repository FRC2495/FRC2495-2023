
package frc.robot;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController; 
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.PrintCommand;

import frc.robot.commands.drivetrain.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.gearbox.*;
import frc.robot.commands.claw.*;
import frc.robot.commands.rotator.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.indicator.*;
//import frc.robot.commands.grasper.*;
//import frc.robot.commands.feeder.*;
//import frc.robot.commands.shooter.*;
//import frc.robot.commands.conditional.*;
import frc.robot.commands.groups.*;
import frc.robot.commands.camera.*;
//import frc.robot.Ports;
//import frc.robot.ControllerBase;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Trigger joyLeftBtn1;
	public Trigger joyLeftBtn2;
	public Trigger joyLeftBtn3;
	public Trigger joyLeftBtn4;
	public Trigger joyLeftBtn5;
	public Trigger joyLeftBtn6;
	public Trigger joyLeftBtn7;
	public Trigger joyLeftBtn8;
	public Trigger joyLeftBtn9;
	public Trigger joyLeftBtn10;
	public Trigger joyLeftBtn11;
	public CommandJoystick joyLeft;

	public Trigger joyRightBtn1;
	public Trigger joyRightBtn2;
	public Trigger joyRightBtn3;
	public Trigger joyRightBtn4;
	public Trigger joyRightBtn5;
	public Trigger joyRightBtn6;
	public Trigger joyRightBtn7;
	public Trigger joyRightBtn8;
	public Trigger joyRightBtn9;
	public Trigger joyRightBtn10;
	public Trigger joyRightBtn11;
	public CommandJoystick joyRight;

	public Trigger gamepadA;
	public Trigger gamepadB;
	public Trigger gamepadX;
	public Trigger gamepadY;
	public Trigger gamepadLBumper;
	public Trigger gamepadRBumper;
	public Trigger gamepadBack;
	public Trigger gamePadStart;
	public Trigger gamepadLStick;
	public Trigger gamepadRStick;
	public Trigger gamepadLXp;
	public Trigger gamepadLXn;
	public Trigger gamepadLYp;
	public Trigger gamepadLYn;
	public Trigger gamepadLTrigger;
	public Trigger gamepadRTrigger;
	public Trigger gamepadRXp;
	public Trigger gamepadRXn;
	public Trigger gamepadRYp;
	public Trigger gamepadRYn;
	public CommandXboxController gamepad;

	public Trigger dpadUp;
	public Trigger dpadDown;
	public Trigger dpadLeft;
	public Trigger dpadRight;


	public OI() {
		gamepad = new CommandXboxController(Ports.USB.GAMEPAD);

		gamepadRYp = gamepad.axisGreaterThan(ControllerBase.GamepadAxes.RY,0.1);
		gamepadRYp.whileTrue(new ArmGamepadControl());

		gamepadRYn = gamepad.axisLessThan(ControllerBase.GamepadAxes.RY,-0.1);
		gamepadRYn.whileTrue(new ArmGamepadControl());

		gamepadRXp = gamepad.axisGreaterThan(ControllerBase.GamepadAxes.RX,0.1);
		gamepadRXp.whileTrue(new RotatorGamepadControl());

		gamepadRXn = gamepad.axisLessThan(ControllerBase.GamepadAxes.RX,-0.1);
		gamepadRXn.whileTrue(new RotatorGamepadControl());

		gamepadRTrigger = gamepad.rightTrigger();
		//gamepadRT.whenPressed(new CameraSetLedMode(ICamera.LedMode.FORCE_ON));
		//gamepadRTrigger.onTrue(new ShoulderSafeMoveDown());
		gamepadRTrigger.onTrue(new PrintCommand("Right trigger triggered!"));

		gamepadLTrigger = gamepad.leftTrigger();
		//gamepadLT.whenPressed(new CameraSetLedMode(ICamera.LedMode.FORCE_OFF));
		//gamepadLT.whileHeld(new FeederFeed());
		gamepadLTrigger.onTrue(new PrintCommand("Left trigger triggered!"));

		gamepadLYp = gamepad.axisGreaterThan(ControllerBase.GamepadAxes.LY,0.5);
		gamepadLYp.onTrue(new ArmRetractWithStallDetection());
		//gamepadLYp.whenPressed(new SpinnerColorMatch()); // pulling back towards operator

		gamepadLYn = gamepad.axisLessThan(ControllerBase.GamepadAxes.LY,-0.5);
		gamepadLYn.onTrue(new ArmSafeExtendWithStallDetection());
		//gamepadLYn.whenPressed(new SpinnerSpinThrice()); // pushing forward

		gamepadLXp = gamepad.axisGreaterThan(ControllerBase.GamepadAxes.LX,0.5);
		//gamepadLXp.onTrue(new RotatorSafeRestWithStallDetection());

		gamepadLXn = gamepad.axisLessThan(ControllerBase.GamepadAxes.LX,-0.5);
		//gamepadLXn.onTrue(new RotatorSafeFlipWithStallDetection());
		
		gamepadRStick = gamepad.rightStick();
		//gamepadRStick.onTrue(new RotatorSafeFlipWithStallDetection()); // temp

		gamepadLStick = gamepad.leftStick();
		//gamepadLStick.onTrue(new RotatorSafeRestWithStallDetection()); // temp

		gamePadStart = gamepad.start();
		//gamePadStart.whenPressed(new ShoulderAndGrasperAndSpinnerStop());
		//gamePadStart.whenPressed(new ShoulderAndGrasperStop());
		//gamePadStart.whenPressed(new ShoulderAndGrasperAndFeederAndShooterStop());
		gamePadStart.onTrue(new AlmostEverythingStop());

		gamepadBack = gamepad.back();
		gamepadBack.onTrue(new FullCalibrateAndReset());

		gamepadRBumper = gamepad.rightBumper();
		//gamepadRB.whenPressed(new ShoulderMoveMidway());
		//gamepadRBumper.onTrue(new ShoulderMoveUp());
		gamepadRBumper.onTrue(new RotatorSafeFlipWithStallDetection());

		gamepadLBumper = gamepad.leftBumper();
		//gamepadLB.whileHeld(new SpinnerSpin());
		//gamepadLB.whenPressed(new SpinnerRaiserUp());
		//gamepadLB.whenPressed(new IfNuclearOptionEnabled(new Climb(), new DoNothing()));
		//gamepadLBumper.onTrue(new ShoulderSafeMoveDown()); // temporary assignment
		gamepadLBumper.onTrue(new RotatorSafeRestWithStallDetection());
		
		gamepadY = gamepad.y();
		//gamepadY.whenPressed(new CameraSetLedMode(ICamera.LedMode.FORCE_ON));	
		//gamepadY.whileHeld(new WinchWinchStopperMagicWinchUp());
		//gamepadY.whileHeld(new ShooterShootHigh());
		//gamepadY.onTrue(new ArmSafeExtendWithStallDetection());

		gamepadX = gamepad.x();
		//gamepadX.whileHeld(new WinchWinchStopperMagicWinchDown());
		//gamepadX.whileHeld(new FeederFeed());
		//gamepadX.whileHeld(new ShooterShootLow());

		gamepadB = gamepad.b();
		//gamepadB.whileHeld(new GrasperRelease());
		gamepadB.onTrue(new ClawSetOpen());

		gamepadA = gamepad.a();
		//gamepadA.whileHeld(new GrasperGrasp());
		gamepadA.onTrue(new ClawSetClosed());


		joyRight = new CommandJoystick(Ports.USB.RIGHT);

		joyRightBtn11 = joyRight.button(ControllerBase.JoystickButtons.BTN11); 
		//joyRightBtn11.whileTrue(new ArmJoystickControl());
	
		joyRightBtn10 = joyRight.button(ControllerBase.JoystickButtons.BTN10);
		//joyRightBtn10.whileTrue(new ArmJoystickControl());

		joyRightBtn9 = joyRight.button(ControllerBase.JoystickButtons.BTN9);
		//joyRightBtn9.whenPressed(new WinchStopperSetStop());
		//joyRightBtn9.whenPressed(new WinchLockWinchStopperSetLockedAndStop());
		joyRightBtn9.whileTrue(new RotatorJoystickControl());

		joyRightBtn8 = joyRight.button(ControllerBase.JoystickButtons.BTN8);
		//joyRightBtn8.whenPressed(new WinchStopperSetFree());
		//joyRightBtn8.whenPressed(new WinchLockWinchStopperSetUnlockedAndFree());
		joyRightBtn8.whileTrue(new ArmJoystickControl());
		
		joyRightBtn7 = joyRight.button(ControllerBase.JoystickButtons.BTN7);
		joyRightBtn7.onTrue(new DrivetrainStop());

		joyRightBtn6 = joyRight.button(ControllerBase.JoystickButtons.BTN6);
		joyRightBtn6.onTrue(new DrivetrainResetEncoders());

		joyRightBtn5 = joyRight.button(ControllerBase.JoystickButtons.BTN5);
		//joyRightBtn5.whenPressed(new DrivetrainMoveUsingCameraPidController(LimelightCamera.OFFSET_CAMERA_TARGET_INCHES));
		//final int MAGIC_DISTANCE_INCHES = 40;
		//joyRightBtn5.whenPressed(new DrivetrainDriveUsingCamera(Robot.camera.getOffsetBetweenCameraAndTarget() + MAGIC_DISTANCE_INCHES));
		//joyRightBtn5.whileHeld(new ShooterShootCustom(3200.0*1.2));
		//joyRightBtn5.whileHeld(new ShooterShootUsingCamera());

		joyRightBtn4 = joyRight.button(ControllerBase.JoystickButtons.BTN4);
		joyRightBtn4.onTrue(new DrivetrainTurnUsingCameraPidController());
		//joyRightBtn4.whenPressed(new DrivetrainTurnAngleFromCameraUsingPidController());

		joyRightBtn3 = joyRight.button(ControllerBase.JoystickButtons.BTN3);

		joyRightBtn2 = joyRight.button(ControllerBase.JoystickButtons.BTN2);
		joyRightBtn2.onTrue(new SwitchedCameraSetUsbCamera(Ports.UsbCamera.SHOOTER_CAMERA));

		joyRightBtn1 = joyRight.button(ControllerBase.JoystickButtons.BTN1);
		joyRightBtn1.onTrue(new GearboxSetGearHigh());


		joyLeft = new CommandJoystick(Ports.USB.LEFT);

		joyLeftBtn11 = joyLeft.button(ControllerBase.JoystickButtons.BTN11);
		//joyLeftBtn11.whileHeld(new WinchJoystickControl());
		//joyLeftBtn11.whileHeld(new WinchWinchStopperJoystickControl());
		//joyLeftBtn11.whileHeld(new ShooterJoystickControl());
		
		joyLeftBtn10 = joyLeft.button(ControllerBase.JoystickButtons.BTN10);
		//joyLeftBtn10.whileHeld(new GrasperJoystickControl());
		//joyLeftBtn10.whileHeld(new FeederJoystickControl());

		joyLeftBtn9 = joyLeft.button(ControllerBase.JoystickButtons.BTN9);
		joyLeftBtn9.whileTrue(new ShoulderJoystickControl());

		joyLeftBtn8 = joyLeft.button(ControllerBase.JoystickButtons.BTN8);
		//joyLeftBtn8.whileHeld(new GrasperJoystickControl());
		joyLeftBtn8.whileTrue(new ShoulderJoystickControl());

		joyLeftBtn7 = joyLeft.button(ControllerBase.JoystickButtons.BTN7);
		joyLeftBtn7.onTrue(new DrivetrainStop());

		joyLeftBtn6 = joyLeft.button(ControllerBase.JoystickButtons.BTN6);
		joyLeftBtn6.onTrue(new DrivetrainAndGyroReset());

		joyLeftBtn5 = joyLeft.button(ControllerBase.JoystickButtons.BTN5);
		joyLeftBtn5.onTrue(new DrivetrainTurnAngleUsingPidController(90));

		joyLeftBtn4 = joyLeft.button(ControllerBase.JoystickButtons.BTN4);
		joyLeftBtn4.onTrue(new DrivetrainTurnAngleUsingPidController(-90));

		joyLeftBtn3 = joyLeft.button(ControllerBase.JoystickButtons.BTN3);
		joyLeftBtn3.onTrue(new DrivetrainMoveDistance(50));

		joyLeftBtn2 = joyLeft.button(ControllerBase.JoystickButtons.BTN2);
		joyLeftBtn2.onTrue(new SwitchedCameraSetUsbCamera(Ports.UsbCamera.GRASPER_CAMERA));

		joyLeftBtn1 = joyLeft.button(ControllerBase.JoystickButtons.BTN1);
		joyLeftBtn1.onTrue(new GearboxSetGearLow());


		dpadUp = gamepad.povUp();
		//dpadUp.whileHeld(new ShooterShootUsingCamera());
		dpadUp.onTrue(new ShoulderMoveUpWithStallDetection());

		dpadDown = gamepad.povDown();
		//dpadDown.whileHeld(new ShooterShootPreset());
		dpadDown.onTrue(new ShoulderSafeMoveDownWithStallDetection());

		dpadLeft = gamepad.povLeft();
		//dpadLeft.whenPressed(new ShooterDecreasePresetRpm());
		dpadLeft.onTrue(new ShoulderSafeMoveFloorWithStallDetection());

		dpadRight = gamepad.povRight();
		//dpadRight.whenPressed(new ShooterIncreasePresetRpm());
		dpadRight.onTrue(new ShoulderMoveLevelTwoWithStallDetection());


		// DEFAULT COMMANDS

		Robot.drivetrain.setDefaultCommand(new DrivetrainJoystickControl());

		Robot.indicator.setDefaultCommand(new IndicatorIndicateUsingCamera());


		// SmartDashboard Buttons

	}

	public Joystick getLeftJoystick() {
		return joyLeft.getHID();
	}

	public Joystick getRightJoystick() {
		return joyRight.getHID();
	}

	public XboxController getGamepad() {
		return gamepad.getHID();
	}
}

