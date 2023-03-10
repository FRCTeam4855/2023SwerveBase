package frc.robot.Commands;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.SwerveDriveSystem;
import frc.robot.Subsystems.Wheel;

public class Balancing extends CommandBase {

    private SwerveDriveSystem driveSystem;
    public static boolean isBalancing;
    private AHRS gyro; // defines the gyro
    private long balanceTime = -1;

    public Balancing(SwerveDriveSystem initialDriveSystem, AHRS initialGyro) {
        driveSystem = initialDriveSystem;
        gyro = initialGyro;
    };

    @Override
    public void initialize() {
    isBalancing = true;
    }

    @Override
    public void execute() {
        if (Math.abs(gyro.getPitch()) > 2) {
            driveSystem.moveManual(0, (gyro.getPitch() / -30 + (gyro.getPitch()/Math.abs(gyro.getPitch()) *-.1)), 0, 0, Wheel.SpeedSetting.PRECISE);
        }
        SmartDashboard.putBoolean("Balancing", isBalancing);
        // if (gyro.getPitch() < -2) {
        //     driveSystem.moveManual(0, (gyro.getPitch() / -30 - .1), 0, 0, Wheel.SpeedSetting.PRECISE);
        // }
    }

    @Override
    public boolean isFinished() {
        if (Math.abs(gyro.getPitch()) <= 2) {
            if (balanceTime < 0) {
                balanceTime = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - balanceTime > 1000) {
                driveSystem.stop();  //if balanced, start counting. Reset if becomes unbalanced. finished if able to remain balanced for one full second. 
                isBalancing = false;
                return true;
            }
        } else {
            balanceTime = -1;
        }
        isBalancing = true;
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
