package frc.robot.Commands;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.SwerveDriveSystem;
import frc.robot.Subsystems.Wheel;
import edu.wpi.first.wpilibj.SPI;

public class Balancing extends CommandBase {

    private SwerveDriveSystem driveSystem;
    boolean isBalancing = false;
    double initTime;
    private AHRS gyro; // defines the gyro
    private long balanceTime = -1;

    public void robotPeriodic() {
        SmartDashboard.putBoolean("Balancing", isBalancing); // shows true if robot is attempting to balance
    }

    public Balancing(SwerveDriveSystem initialDriveSystem, AHRS initialGyro) {
        driveSystem = initialDriveSystem;
        gyro = initialGyro;
    };

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        if (gyro.getPitch() > 2) {
            driveSystem.moveManual(0, (gyro.getPitch() / 30 + .1), 0, 0, Wheel.SpeedSetting.PRECISE);
        }
        if (gyro.getPitch() < -2) {
            driveSystem.moveManual(0, (gyro.getPitch() / -30 - .1), 0, 0, Wheel.SpeedSetting.PRECISE);
        }
    }

    @Override
    public boolean isFinished() {
        if (Math.abs(gyro.getPitch()) <= 2) {
            if (balanceTime < 0) {
                balanceTime = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - balanceTime > 1000) {
                driveSystem.stop();
                return true;
            }
        } else {
            balanceTime = -1;
        }
        return false;
    }
    // if (xboxDriver.getRawButton(TEST_PID_ROTATE_A) && Math.abs(gyro.getPitch()) >
    // 2){
    // isBalancing = true;
    // driveSystem.moveManual(0, (gyro.getPitch()/-40), 0 , theta_radians,
    // driveSpeed);
    // } else {
    // isBalancing = false;
    // }
}
