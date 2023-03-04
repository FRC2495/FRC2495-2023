package frc.robot.auton.blue;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.brake.BrakeSetReleased;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.auton.AutonConstants;
import frc.robot.auton.common.DropConeOnTopNode;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneB1Co extends SequentialCommandGroup {

    public StartingPositionOneB1Co(){

        addCommands(
            
            new BrakeSetReleased(),
            // makes sure that the brake is not on the floor before match begins

            new DropConeOnTopNode(),
            // drops cone in highest level

            new WaitCommand(1),

            new ClawSetClosed(),
            // closes claw

            new ArmRetractWithStallDetection(),
            // retracts arm

            new ShoulderSafeMoveDownWithStallDetection(),
            // brings shoulder into frame perimeter

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY) // todo change distance if needed
            // drives backward to outside community

        ); 
  
    }

    
   

}