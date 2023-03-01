// package frc.robot.Commands;

<<<<<<< Updated upstream
public class Balancing {
    
}
=======
// import com.kauailabs.navx.frc.AHRS;

// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.interfaces.Gyro;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Subsystems.SwerveDriveSystem;
// import frc.robot.Subsystems.Wheel;
// import edu.wpi.first.wpilibj.SPI;

// public class Balancing extends CommandBase {

//     boolean isBalancing = false;
//     double initTime;
//     AHRS gyro = new AHRS(SPI.Port.kMXP); //defines the gyro
//         public void robotPeriodic() {
//     SmartDashboard.putBoolean("Balancing", isBalancing); //shows true if robot is attempting to balance
//     }
//    public Balancing(boolean isBalancing) {
//    };
//    @Override
//    public void initialize(){
    
//    }

//    @Override
//    public void execute(){
// if (Math.abs(gyro.getPitch()) > 2) {
//     SwerveDriveSystem.moveManual(0, (gyro.getPitch()/-40), 0 , 0, Wheel.SpeedSetting.PRECISE);
    
// }
//    }

//    @Override
//    public boolean isFinished(){
// if (isBalancing = false){
//     return true;
// }
// return false;
//    }
//     // if (xboxDriver.getRawButton(TEST_PID_ROTATE_A) && Math.abs(gyro.getPitch()) > 2){
//     //     isBalancing = true;
//     //     driveSystem.moveManual(0, (gyro.getPitch()/-40), 0 , theta_radians, driveSpeed);
//     //   } else {
//     //     isBalancing = false;
//     //   }    
//     //TODO move balance lazy code from robot to a command here
// }
>>>>>>> Stashed changes
