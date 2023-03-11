
package frc.robot;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController; 
import edu.wpi.first.wpilibj2.command.button.Trigger;
//import edu.wpi.first.wpilibj2.command.PrintCommand;

import frc.robot.commands.drivetrain.*;
import frc.robot.commands.jack.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.gearbox.*;
import frc.robot.commands.claw.*;
import frc.robot.commands.rotator.*;
import frc.robot.commands.arm.*;
//import frc.robot.commands.brake.*;
import frc.robot.commands.indicator.*;
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

	public static final double GAMEPAD_AXIS_THRESHOLD = 0.2;


	public OI() {
		gamepad = new CommandXboxController(Ports.USB.GAMEPAD);

		gamepadRYp = gamepad.axisGreaterThan(ControllerBase.GamepadAxes.RY,GAMEPAD_AXIS_THRESHOLD);
		gamepadRYp.whileTrue(new ArmGamepadControl());

		gamepadRYn = gamepad.axisLessThan(ControllerBase.GamepadAxes.RY,-GAMEPAD_AXIS_THRESHOLD);
		gamepadRYn.whileTrue(new ArmGamepadControl());

		gamepadRXp = gamepad.axisGreaterThan(ControllerBase.GamepadAxes.RX,GAMEPAD_AXIS_THRESHOLD);
		gamepadRXp.whileTrue(new RotatorGamepadControl());

		gamepadRXn = gamepad.axisLessThan(ControllerBase.GamepadAxes.RX,-GAMEPAD_AXIS_THRESHOLD);
		gamepadRXn.whileTrue(new RotatorGamepadControl());

		gamepadRTrigger = gamepad.rightTrigger();
		gamepadRTrigger.onTrue(new ArmSafeExtendWithStallDetection());

		gamepadLTrigger = gamepad.leftTrigger();
		gamepadLTrigger.onTrue(new ArmRetractWithStallDetection());

		gamepadLYp = gamepad.axisGreaterThan(ControllerBase.GamepadAxes.LY,GAMEPAD_AXIS_THRESHOLD);
		gamepadLYp.onTrue(new ShoulderGamepadControl());

		gamepadLYn = gamepad.axisLessThan(ControllerBase.GamepadAxes.LY,-GAMEPAD_AXIS_THRESHOLD);
		gamepadLYn.onTrue(new ShoulderGamepadControl());

		gamepadLXp = gamepad.axisGreaterThan(ControllerBase.GamepadAxes.LX,GAMEPAD_AXIS_THRESHOLD);
		gamepadLXp.onTrue(new JackGamepadControl());

		gamepadLXn = gamepad.axisLessThan(ControllerBase.GamepadAxes.LX,-GAMEPAD_AXIS_THRESHOLD);
		gamepadLXn.onTrue(new JackGamepadControl());
		
		gamepadRStick = gamepad.rightStick();
		gamepadRStick.onTrue(new JackMoveUpWithStallDetection());

		gamepadLStick = gamepad.leftStick();
		gamepadLStick.onTrue(new JackMoveDownWithStallDetection());

		gamePadStart = gamepad.start();
		gamePadStart.onTrue(new AlmostEverythingStop());

		gamepadBack = gamepad.back();
		gamepadBack.onTrue(new FullCalibrateAndReset());

		gamepadRBumper = gamepad.rightBumper();
		gamepadRBumper.onTrue(new RotatorSafeFlipWithStallDetection());

		gamepadLBumper = gamepad.leftBumper();
		gamepadLBumper.onTrue(new RotatorSafeRestWithStallDetection());
		
		gamepadY = gamepad.y();
		gamepadY.onTrue(new DrivetrainSetCoastNeutralMode()); // temp

		gamepadX = gamepad.x();
		gamepadX.onTrue(new DrivetrainSetBrakeNeutralMode()); // temp

		gamepadB = gamepad.b();
		gamepadB.onTrue(new ClawSetOpen());

		gamepadA = gamepad.a();
		gamepadA.onTrue(new ClawSetClosed());


		joyRight = new CommandJoystick(Ports.USB.RIGHT);

		joyRightBtn11 = joyRight.button(ControllerBase.JoystickButtons.BTN11); 
		joyRightBtn11.onTrue(new Drive());
	
		joyRightBtn10 = joyRight.button(ControllerBase.JoystickButtons.BTN10);
		joyRightBtn10.onTrue(new Park());

		joyRightBtn9 = joyRight.button(ControllerBase.JoystickButtons.BTN9);
		joyRightBtn9.whileTrue(new RotatorJoystickControl());

		joyRightBtn8 = joyRight.button(ControllerBase.JoystickButtons.BTN8);
		joyRightBtn8.whileTrue(new ArmJoystickControl());
		
		joyRightBtn7 = joyRight.button(ControllerBase.JoystickButtons.BTN7);
		joyRightBtn7.onTrue(new DrivetrainStop());

		joyRightBtn6 = joyRight.button(ControllerBase.JoystickButtons.BTN6);
		joyRightBtn6.onTrue(new DrivetrainResetEncoders());

		joyRightBtn5 = joyRight.button(ControllerBase.JoystickButtons.BTN5);
		// reserved for DrivetrainMoveUsingCameraPidController

		joyRightBtn4 = joyRight.button(ControllerBase.JoystickButtons.BTN4);
		joyRightBtn4.onTrue(new DrivetrainTurnUsingCameraPidController());

		joyRightBtn3 = joyRight.button(ControllerBase.JoystickButtons.BTN3);
		//joyRightBtn3.onTrue(new DrivetrainEngageUsingAccelerometerPidControllerWithStallDetection());
		//joyRightBtn3.onTrue(new DrivetrainMoveDistanceWithFlatDetection(60));
		//joyRightBtn3.onTrue(new DrivetrainMoveDistanceWithSteepDetection(60));
		//joyRightBtn3.onTrue(new DrivetrainMoveToTop());
		//joyRightBtn3.onTrue(new DrivetrainMoveToTopInReverse());
		joyRightBtn3.onTrue(new DrivetrainEngageInReverse());

		joyRightBtn2 = joyRight.button(ControllerBase.JoystickButtons.BTN2);
		joyRightBtn2.onTrue(new SwitchedCameraSetUsbCamera(Ports.UsbCamera.BOTTOM_CAMERA));

		joyRightBtn1 = joyRight.button(ControllerBase.JoystickButtons.BTN1);
		joyRightBtn1.onTrue(new GearboxSetGearHigh());


		joyLeft = new CommandJoystick(Ports.USB.LEFT);

		joyLeftBtn11 = joyLeft.button(ControllerBase.JoystickButtons.BTN11);
		joyLeftBtn11.onTrue(new Drive());
		
		joyLeftBtn10 = joyLeft.button(ControllerBase.JoystickButtons.BTN10);
		joyLeftBtn10.onTrue(new Park());

		joyLeftBtn9 = joyLeft.button(ControllerBase.JoystickButtons.BTN9);
		joyLeftBtn9.whileTrue(new ShoulderJoystickControl());

		joyLeftBtn8 = joyLeft.button(ControllerBase.JoystickButtons.BTN8);
		joyLeftBtn8.whileTrue(new JackJoystickControl());

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
		joyLeftBtn2.onTrue(new SwitchedCameraSetUsbCamera(Ports.UsbCamera.TOP_CAMERA));

		joyLeftBtn1 = joyLeft.button(ControllerBase.JoystickButtons.BTN1);
		joyLeftBtn1.onTrue(new GearboxSetGearLow());


		dpadUp = gamepad.povUp();
		dpadUp.onTrue(new ShoulderMoveUpWithStallDetection());

		dpadDown = gamepad.povDown();
		dpadDown.onTrue(new ShoulderSafeMoveDownWithStallDetection());

		dpadLeft = gamepad.povLeft();
		dpadLeft.onTrue(new ShoulderSafeMoveFloorWithStallDetection());

		dpadRight = gamepad.povRight();
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

