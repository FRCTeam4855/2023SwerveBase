package frc.robot;

public class Constants {
    //Constants ++++++++++++
    public static final double JOYSTK_DZONE = .17;                  // global deadzone for all controllers
    public static final double DRIVE_DEFAULT_SPD = 0.50;               // multiplier
    public static final double DRIVE_SLOW_SPD = 0.3;               // multiplier
    public static final double DRIVE_TURBO_SPD = 0.60;               // multiplier
    //TODO arrange with drive team what these values are on 2023 bot


    public static final double DIST_BETWEEN_WHEELS = 27; // in inches  

    // Conversions +++++++++
    public static final double RELATIVE_ENC_TO_FT = 7.4; //0.660715;//7.928591; //0.03666658; // converts relative encoder units on neo to inches

    //Sensor Ranges/Setpoints

    //center point for arm extension setpoints
    public static final double ARM_EXTEND_CENTER_1 = 10; //done
    public static final double ARM_EXTEND_CENTER_2 = 180; //TODO find these values on 2023 bot
    public static final double ARM_EXTEND_CENTER_3 = 132;
    public static final double ARM_EXTEND_CENTER_4 = 333;
    public static final double ARM_EXTEND_CENTER_5 = 50;
    public static final double ARM_EXTEND_SLOP = 3;    //acceptable range for arm extension setpoints

    //center point for arm extension setpoints
    public static final double ARM_PIVOT_CENTER_1 = 0; //done
    public static final double ARM_PIVOT_CENTER_2 = 8; //TODO find these values on 2023 bot
    public static final double ARM_PIVOT_CENTER_3 = 18;
    public static final double ARM_PIVOT_CENTER_4 = 21;
    public static final double ARM_PIVOT_CENTER_5 = 0;
    public static final double ARM_PIVOT_SLOP = 3;    //acceptable range for arm extension setpoints

    public enum ArmSetpoint {
        One, Two, Three, Four, Five
    }

    //One = Interior bot, holding + traveling position
    //Two = floor pickup and place
    //Three = low cube
    //Four = Low Cone, High Cube, Player Window
    //Five = High Cone




    //@@@@@@@@@
    //COPY FROM
    // public class XboxController extends GenericHID {
    //     /** Represents a digital button on an XboxController. */
    //     public enum Button {
    //       kLeftBumper(5),
    //       kRightBumper(6),
    //       kLeftStick(9),
    //       kRightStick(10),
    //       kA(1),
    //       kB(2),
    //       kX(3),
    //       kY(4),
    //       kBack(7),
    //       kStart(8);
    //@@@@@@@@@@

    // Driver Mappings +++++
    public static final int GYRO_RESET_Y = 4; //Y
    public static final int ENCODER_RESET_B = 2; //B
    public static final int ORIENTATION_TOGGLE_X = 3; //X
    public static final int DRV_SPD_TURBO_RB = 6;
    public static final int DRV_SPD_PRECISE_LB = 5;   
    public static final int TEST_PID_ROTATE_A = 1;

    public static final int SCHEDULE_INITIAL_COMMAND_LB = 5;
    
    
    
    
    
    //Operator Mappings ++++++
    public static final int ARM_SETPOINT1_A = 1;
    public static final int ARM_SETPOINT2_B = 2;
    public static final int ARM_SETPOINT3_X = 3;
    public static final int ARM_SETPOINT4_Y = 4;
    public static final int PAWS_TOGGLEBOTH_SELECT = 7;

    // public static final int PAW_TOGGLELEFT_B = ;
    // public static final int PAW_TOGGLERIGHT_X = ;
    // public static final int TEST_TOGGLE_LL_CAM_MODE_Y = ;
    // public static final int SWITCH_LLRETROTAP_LB = 5;
    public static final int TOGGLE_LL_PIPELINE_RB = 6;
}
