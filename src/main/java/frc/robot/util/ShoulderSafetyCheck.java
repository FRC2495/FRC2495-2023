// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import java.util.function.BooleanSupplier;

import frc.robot.Robot;

/** Add your docs here. */
public class ShoulderSafetyCheck implements BooleanSupplier
{
    @Override
    public boolean getAsBoolean() {
        return !Robot.shoulderControl.isDangerous();
    }
}
