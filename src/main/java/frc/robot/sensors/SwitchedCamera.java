// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.sensors;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/** Add your docs here. */
public class SwitchedCamera {

	public synchronized static void setUsbCamera(int camera) {

		// assumes you used "/PiSwitch" as the NT key on the Pi
		NetworkTableEntry cameraSelect = NetworkTableInstance.getDefault().getEntry("/PiSwitch");

		if (cameraSelect != null) {
			cameraSelect.setDouble(camera);  // or setString("My Pi Camera Name")
		}
	}

}
