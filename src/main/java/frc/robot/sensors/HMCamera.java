package frc.robot.sensors;


/**
 * The {@code HMCamera} class contains fields and methods pertaining to the function of the camera.
 */
public class HMCamera {
	
	public static final int HORIZONTAL_CAMERA_RES_PIXELS = 320;
	public static final int VERTICAL_CAMERA_RES_PIXELS = 240;

	public static final double SAFE_DISTANCE_INCHES = 120;	

	public static final double MIN_OFFSET_CAMERA_TARGET_INCHES = 0;
	public static final double DEFAULT_OFFSET_CAMERA_TARGET_INCHES = 10; // we need to leave some space between the camera and the target
	public static final double MAX_OFFSET_CAMERA_TARGET_INCHES = 36;

	public static final double OFFSET_CAMERA_HATCH_INCHES = 10;
	public static final double OFFSET_CAMERA_PORT_INCHES = 24;
}
