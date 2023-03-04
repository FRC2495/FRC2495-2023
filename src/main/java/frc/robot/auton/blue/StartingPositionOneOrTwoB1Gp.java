package frc.robot.auton.blue;

import frc.robot.commands.claw.*;
import frc.robot.commands.shoulder.*;
import frc.robot.commands.arm.*;
import frc.robot.commands.drivetrain.*;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.auton.AutonConstants;

// GP = game piece
// Can be used to place one cube or one cone and either starting position one or two
public class StartingPositionOneOrTwoB1Gp extends SequentialCommandGroup {

    public StartingPositionOneOrTwoB1Gp(){

        addCommands(
            
         /* 
            new ShoulderMoveUpWithStallDetection(),
            // lifts shoulder up out of frame perimeter  
            
            new ArmSafeExtendWithStallDetection(),
            // extends arm above cube node

            new ClawSetOpen(),
            // opens claw to put cube onto cube node

            new WaitCommand(1),

            new ClawSetClosed(),
            // closes claw

            new ArmRetractWithStallDetection(),
            // retracts arm

            new ShoulderSafeMoveDownWithStallDetection(),
            // brings shoulder into frame perimeter
            */

            // new auton path 

            /*new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_CONE_NODE_TO_BEFORE_FIRST_TURN),
            // drives robot backward from cone node to area before the first turn

            new DrivetrainTurnAngleUsingPidControllerWithStallDetection(-20),
            // turns robot before moving forward

            DrivetrainMoveDistanceWithStallDetection(+AutonConstants.DISTANCE_FROM_AREA_AFTER_FIRST_TURN_TO_AREA_BEFORE_SECOND_TURN),
            // moves robot forward to before second turning point

            */
            
            

            new DrivetrainTurnAngleUsingPidController(-230)
            

            //new DrivetrainMoveDistanceWithStallDetection(-AutonConstants.DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY)
            // moves backwards to outside the community*/
        ); 
  
    }

    
   

}