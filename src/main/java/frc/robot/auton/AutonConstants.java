package frc.robot.auton;

public class AutonConstants {

    // Fixed auton distances

    // Starting Position One + Six(?) Distances
    public static final double DISTANCE_FROM_CONE_NODE_TO_AREA_BEFORE_FIRST_TURN = 37;
    public static final double DISTANCE_FROM_AREA_AFTER_FIRST_TURN_TO_AREA_BEFORE_SECOND_TURN = 29;
    public static final double DISTANCE_FROM_AREA_AFTER_SECOND_TURN_TO_AREA_BEFORE_CONE_PICKUP = 120;
    public static final double DISTANCE_FROM_AREA_BEFORE_CONE_PICKUP_TO_CONE_PICKUP = 30;
    public static final double DISTANCE_FROM_AREA_AFTER_SECOND_TURN_TO_CONE_PICKUP = 144.5; // the total of previous two constants
    public static final double DISTANCE_FROM_CONE_PICKUP_TO_AREA_BEFORE_THIRD_TURN = 134.5;
    public static final double DISTANCE_FROM_AREA_AFTER_THIRD_TURN_TO_AREA_BEFORE_FOURTH_TURN = 21;
    public static final double DISTANCE_FROM_AREA_AFTER_FOURTH_TURN_TO_CONE_NODE = 8;

    public static final double DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY = 134+24;
    public static final double DISTANCE_FROM_OUTSIDE_COMMUNITY_TO_SECOND_CONE_PICKUP = 20;

    // Starting Position Three + Four Distances
    public static final double DISTANCE_FROM_NODE_TO_DOCK = 30; // todo put proper value
    public static final double DISTANCE_FROM_START_OF_CHARGING_STATION_TO_DOCKED_AT_CHARGING_STATION = 10; // todo put proper value
    public static final double DISTANCE_FROM_DOCK_TO_OUTSIDE_COMMUNITY = 100; // todo put proper value
    public static final double DISTANCE_FROM_OUTSIDE_COMMUNITY_TO_BEFORE_DOCK = 90; // todo put proper value
    public static final double DISTANCE_FROM_DOCK_NEAR_NODE_TO_AREA_BEFORE_CONE_PICKUP = 70; // todo put proper value
    public static final double DISTANCE_FROM_CONE_PICKUP_TO_AREA_BEFORE_DOCK = 85; // todo put proper value
    public static final double DISTANCE_FROM_DOCK_NEAR_CONE_PICKUP_TO_AREA_NEAR_CONE_NODE = 60;
    public static final double DISTANCE_FROM_AREA_NEAR_CONE_NODE_TO_CONE_NODE = 30;


    // Fixed auton angles

    // Starting Position One + Six(?) Angles
    public static final int ANGLE_BETWEEN_CONE_NODE_AND_AREA_AFTER_FIRST_TURN = 180;
    public static final int ANGLE_BETWEEN_AREA_AFTER_FIRST_TURN_AND_CONE_PICKUP = 15;
    public static final int ANGLE_BETWEEN_AREA_BEFORE_THIRD_TURN_AND_TOWARDS_CONE_NODE_FIRST_PART = 90;
    public static final int ANGLE_BETWEEN_AREA_BEFORE_THIRD_TURN_AND_TOWARDS_CONE_NODE_SECOND_PART = 115; // the total of this and previous angle is supposed to be 205
    public static final int ANGLE_BETWEEN_AREA_BEFORE_FOURTH_TURN_AND_CONE_NODE = 25;
    public static final int ANGLE_BETWEEN_CONE_NODE_AND_CONE_PICKUP = 160;
    public static final int ANGLE_BETWEEN_RIGHT_CONE_NODE_AND_CONE_PICKUP = 153;



    // Old auton constants 
    /*public static final double DISTANCE_FROM_CUBE_NODE_TO_LEFT_TURNING_BEFORE_CONE_PICKUP = 157.67; // todo adjust as needed ; for P1B
    public static final double DISTANCE_FROM_LEFT_TURNING_TO_CONE_PICKUP = 80; // todo adjust as needed ; for Path 1B
    public static final double DISTANCE_FROM_CONE_PICKUP_TO_CONE_NODE = 248.14; // todo adjust as needed ; for Path 1B , could be  248.14 - 256

    public static final double DISTANCE_FROM_CUBE_NODE_TO__LEFT_TURNING_BEFORE_CUBE_PICKUP = 183.33; // todo adjust as needed ; for P2B, P8B, could be 183.33-200
    public static final double DISTANCE_FROM_LEFT_TURNING_TO_CUBE_PICKUP_SP2 = 48; // todo adjust as needed ; for P2B
    //public static final double DISTANCE_FROM_CUBE_PICKUP_SP2_TO_CUBE_NODE = 248.13; // todo adjust as needed ; for P2B

    //public static final double DISTANCE_FROM_NODE_TO_OUTSIDE_COMMUNITY = 160; // todo adjust as needed ; for P3B, P4B, P10B, P11B 

    public static final double DISTANCE_FROM_CUBE_NODE_TO_CHARGING_STATION = 96.75; // todo adjust as needed ; for P5B, P6B, P7B , could be 60.69-96.75

    public static final double DISTANCE_FROM_RIGHT_CUBE_PICKUP_TO_CUBE_NODE_SP6 = 256; // todo adjust as needed ; for P8B
    public static final double DISTANCE_FROM_RIGHT_CUBE_PICKUP_TO_CUBE_NODE_SP5 = 252; // todo adjust as needed ; for P9B , could be 248.142-252
   */ 
}
