package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Ports;


/**
 * The {@code Gearbox} class contains fields and methods pertaining to the function of the gearbox.
 */
public class Gearbox extends SubsystemBase {
	
	static final int WAIT_MS = 1000;
	
	DoubleSolenoid highLow;
	
	public enum Gear {
		HIGH, // The gearbox is in high gear
		LOW, // The gearbox is in low gear
		UNKNOWN;
	}

	public Gearbox() {
		// the double solenoid valve will send compressed air from the tank wherever needed
		//highLow = new DoubleSolenoid(Ports.CAN.PCM, Ports.PCM.GEAR_HIGH, Ports.PCM.GEAR_LOW); // make sure ports are properly sets in Ports.java	
		highLow = new DoubleSolenoid(Ports.CAN.PCM, PneumaticsModuleType.REVPH, Ports.PCM.GEAR_HIGH, Ports.PCM.GEAR_LOW);
	}
	
	@Override
	public void periodic() {
		// Put code here to be run every loop

	}

	public void setGear(Gear gear) //Telling the piston to be in the selected position 
	{
		switch(gear)
		{
			case HIGH: //Telling the solenoid to have the piston go up
			{
				highLow.set(DoubleSolenoid.Value.kReverse); // adjust direction if needed
				break;
			}
			case LOW: //Telling the solenoid to have the piston go down
			{
				highLow.set(DoubleSolenoid.Value.kForward); // adjust direction if needed
				break;
			}
			default:
			{
				// do nothing
			}
		}
	}

	public Gear getGear() //Getting the current gear
	{
		DoubleSolenoid.Value value = highLow.get();
		
		switch(value)
		{
			case kReverse: //Checking if the piston is in the kReverse position (high)
			{
				return Gear.HIGH;
			}
			case kForward: //Checking if the piston is in the kFoward position (low)
			{
				return Gear.LOW;
			}
			default: //gear unknown
			{
				return Gear.UNKNOWN;
			}
		}
	}
}