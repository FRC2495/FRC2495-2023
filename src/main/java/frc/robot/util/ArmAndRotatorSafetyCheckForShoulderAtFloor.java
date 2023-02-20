// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import java.util.function.BooleanSupplier;

import frc.robot.Robot;

/** The purpose of this class is to check if it's okay to move the shoulder to the floor
 * 
 * It's the case when the shoulder is not in the danger zone.
 */
public class ArmAndRotatorSafetyCheckForShoulderAtFloor implements BooleanSupplier
{
    @Override
    public boolean getAsBoolean() {
        return !Robot.arm.isDangerousForShoulderAtFloor() && !Robot.rotator.isDangerous();
    }
}
