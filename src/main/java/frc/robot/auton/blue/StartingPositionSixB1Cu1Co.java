package frc.robot.auton.blue;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.auton.AutonConstants;

public class StartingPositionSixB1Cu1Co extends SequentialCommandGroup {

    public StartingPositionSixB1Cu1Co(){

        addCommands(
            new ShoulderMoveUpWithStallDetection(),
            // lifts shoulder up out of frame perimeter        

            new ArmSafeExtendWithStallDetection(),
            // extends arm up 

            new ClawSetOpen(),
            // opens claw to put cube on cube node

            new WaitCommand(1),

            new ArmRetractWithStallDetection(),
            // retracts arm to put shoulder into frame perimeter

            new ShoulderSafeMoveDownWithStallDetection(),
            // lowers shoulder into frame perimeter
            
            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_CUBE_NODE_TO_LEFT_TURNING_BEFORE_CONE_PICKUP),
            // moves from starting position one to turning point before cone pickup

            new DrivetrainTurnAngleUsingPidController(+180),
            // turns from facing cube node to cone pickup

            new ShoulderSafeMoveFloorWithStallDetection(),
            // lowers shoulder from up to pickup

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_LEFT_TURNING_TO_CUBE_PICKUP_SP2),
            // moves from turning point to cone pickup 

            new ClawSetClosed(),
            // closes claw to pick up cone (may be changed to sensor picking up cube automatically)

            new WaitCommand(1),

            new ArmRetractWithStallDetection(),
            // retracts arm to avoid dragging on floor
        
            new DrivetrainTurnAngleUsingPidController(-180),
            // turns from cone pickup towards cone node

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_RIGHT_CUBE_PICKUP_TO_CUBE_NODE_SP6),
            // moves from cone pickup to cone node

            new DrivetrainTurnUsingCameraPidControllerWithStallDetection(),
            // turns to cone node

            new ShoulderMoveUpWithStallDetection(),
            // move shoulder up for claw to face cone node

            new ArmSafeExtendWithStallDetection(),
            // extend arm to have cone over node

            new ClawSetOpen(),
            // opens claw to place cone on cone node

            new ArmRetractWithStallDetection()
            // retracts arm to get ready for teleop mode
        );
    }

    
   

}