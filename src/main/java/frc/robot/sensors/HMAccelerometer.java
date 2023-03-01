package frc.robot.sensors;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

import edu.wpi.first.math.filter.LinearFilter;


/**
 * The {@code HMAccelerometer} class contains fields and methods pertaining to the function of the accelerometer.
 */
public class HMAccelerometer {
	
	static final double TILT_THRESH_DEGREES = 15.0;
	
	private BuiltInAccelerometer accel;
	private LinearFilter filter;
	
	public HMAccelerometer(){
		accel = new BuiltInAccelerometer(Accelerometer.Range.k4G);
		
		filter = LinearFilter.movingAverage(10); // filter over n iterations
	}
	
	public double getAccelZ(){
		return accel.getZ();
	}

	public double getFilteredAccelZ(){
		return filter.calculate(accel.getZ());
	}
	
	public double getTilt() {
		//return Math.toDegrees(Math.acos(Math.min(getAccelZ(),1.0)/1.0)); // assumes getAccelZ() returns 1.0 when straight
		return Math.toDegrees(Math.acos(Math.min(getFilteredAccelZ(),1.0)/1.0)); // assumes getAccelZ() returns 1.0 when straight
	}
	
	/**
	 * Indicates if the support onto which the accelerometer is attached is flat
	 * 
	 * @return true if the support onto which the accelerometer is attached is flat, false otherwise
	 */
	public boolean isFlat() {
		return getTilt() < TILT_THRESH_DEGREES;
	}
	
}
