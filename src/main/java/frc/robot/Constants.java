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
    
    
}
