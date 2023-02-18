package frc.robot.auton.blue;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.auton.AutonConstants;

public class StartingPositionSixB1Cu1Co extends SequentialCommandGroup {

    public StartingPositionSixB1Cu1Co(){

        addCommands(
            new ShoulderMoveUp());
            // lifts shoulder up out of frame perimeter        

            new ArmSafeExtendWithStallDetection();
            // extends arm up

            new ClawSetOpen();
            // opens claw to put cube on cube node

            new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_CUBE_NODE_TO_LEFT_TURNING_BEFORE_CONE_PICKUP);
            // moves from starting position one to turning point before cone pickup

            new DrivetrainTurnAngleUsingPidController(+180);
            // turns from facing cube node to cone pickup

            new ShoulderSafeMoveDown();
            // lowers shoulder from up to near the ground

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_LEFT_TURNING_TO_CUBE_PICKUP_SP2);
            // moves from turning point to cone pickup 

            new ClawSetClosed();
            // closes claw to pick up cone (may be changed to sensor picking up cube automatically)

            new ShoulderMoveUp();
            // brings shoulder up to avoid bumping into anything
        
            new DrivetrainTurnAngleUsingPidController(-180);
            // turns from cube pickup towards cone node

            // uses limelight to adjust drivetrain to apriltag of cube node

            new DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_RIGHT_CUBE_PICKUP_TO_CUBE_NODE_SP6);
            // moves from cube pickup to cube node

            // uses limelight to adjust claw to apriltag of cube node

            new ClawSetOpen();
            // opens claw to place cube on cube node
  
    }

    
   

}