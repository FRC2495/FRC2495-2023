package frc.robot.auton.blue;

import frc.robot.commands.claw.*;
import frc.robot.commands.hinge.*;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.auton.AutonConstants;

public class StartingPositionTwoB2Cu extends SequentialCommandGroup {
    
    public StartingPositionTwoB2Cu(){
        addCommands(
            new HingeMoveUp());
            // lifts hinge up out of frame perimeter        

            // extends hinge up

            // uses limelight to adjust claw onto cube node

            new ClawSetOpen();
            // opens claw to put cube on cube node

            new DrivetrainMoveDistance(-AutonConstants.DISTANCE_FROM_CUBE_NODE_TO__LEFT_TURNING_BEFORE_CUBE_PICKUP);
            // moves from starting position two to turning point before cube pickup

            new DrivetrainTurnAngleUsingPidController(-170);
            // turns from facing cone node to cube pickup

            new HingeMoveDown();
            // lowers hinge from up to near the ground

            new DrivetrainMoveDistance(+AutonConstants.DISTANCE_FROM_LEFT_TURNING_TO_CUBE_PICKUP_SP2);
            // moves from turning point to cube pickup 

            new ClawSetClosed();
            // closes claw to pick up cube (may be changed to sensor picking up cube automatically)

            new HingeMoveUp();
            // brings hinge up to avoid bumping into anything
        
            new DrivetrainTurnAngleUsingPidController(+169);
            // turns from cube pickup towards cube node/april tag

            // uses limelight to adjust drivetrain to apriltag of cube node

            new DrivetrainMoveDistance(+AutonConstants.DISTANCE_FROM_CUBE_PICKUP_SP2_TO_CUBE_NODE);
            // moves from cube pickup to cube node

            // uses limelight to adjust claw to apriltag of cube node

            new ClawSetOpen();
            // opens claw to place cube on cube node
  


    
    
    }
        

}
