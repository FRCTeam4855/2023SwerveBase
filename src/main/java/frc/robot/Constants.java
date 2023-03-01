package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;

public class Constants {
    //Constants ++++++++++++
    public static final double JOYSTK_DZONE = .17;                  // global deadzone for all controllers
    public static final double DRIVE_INIT_SPD = 0.50;               // multiplier
    public static final double DRIVE_SLOW_SPD = 0.35;               // multiplier

    // Conversions +++++++++
    public static final double RELATIVE_ENC_TO_FT = 7.4; //0.660715;//7.928591; //0.03666658; // converts relative encoder units on neo to inches

<<<<<<< Updated upstream
=======
    //Sensor Ranges/Setpoints

    public static final double LIDAR_SET_MAX = 1 ; //TODO find value on 2023 bot
    public static final double LIDAR_SET_MIN = 1; //TODO find value on 2023 bot

    public static final double ARM_EXTEND_CENTER_1 = 1; //center point for arm extension setpoints
    public static final double ARM_EXTEND_CENTER_2 = 1; //TODO find these values on 2023 bot
    public static final double ARM_EXTEND_CENTER_3 = 1;
    public static final double ARM_EXTEND_CENTER_4 = 1;
    public static final double ARM_EXTEND_CENTER_5 = 1;
    public static final double ARM_EXTEND_SLOP = 1;    //acceptable range for arm extension setpoints

    public static final double ARM_PIVOT_CENTER_1 = 1; //center point for arm extension setpoints
    public static final double ARM_PIVOT_CENTER_2 = 1; //TODO find these values on 2023 bot
    public static final double ARM_PIVOT_CENTER_3 = 1;
    public static final double ARM_PIVOT_CENTER_4 = 1;
    public static final double ARM_PIVOT_CENTER_5 = 1;
    public static final double ARM_PIVOT_SLOP = 1;    //acceptable range for arm extension setpoints

    public static enum ArmSetpoint {
        One, Two, Three, Four, Five
    }

    //One = Interior bot, holding + traveling position
    //Two = floor pickup and place
    //Three = low cube
    //Four = Low Cone, High Cube, Player Window
    //Five = High Cone




>>>>>>> Stashed changes
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
    public static final int DRV_SPD_LIMITER_RB = 6;
    public static final int TEST_PID_ROTATE_A = 1;

    public static final int SCHEDULE_INITIAL_COMMAND_LB = 5;
    //Operator
    public static final int PAWS_TOGGLEBOTH_A = 1;
    public static final int PAW_TOGGLELEFT_B = 2;
    public static final int PAW_TOGGLERIGHT_X = 3;
<<<<<<< Updated upstream
=======
    public static final int PLACEHOLDER_BALANCE_LB = 5;
    public static final int TEST_TOGGLE_LL_CAM_MODE_Y = 4;
>>>>>>> Stashed changes
    
    
}
