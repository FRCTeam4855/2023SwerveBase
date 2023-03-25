package frc.robot.Commands;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.SwerveDriveSystem;
import frc.robot.Subsystems.Wheel;

public class Balancing extends CommandBase {

    // private SwerveDriveSystem driveSystem;
    public static boolean isBalancing;
    private AHRS initialGyro; // defines the initialGyro
    private long balanceTime = -1;
    private double pitchAngleDegrees;
    private SwerveDriveSystem driveSystem;

    public Balancing(SwerveDriveSystem driveSystem, AHRS initialGyro) {
        this.driveSystem = driveSystem;
        this.initialGyro = initialGyro;
        pitchAngleDegrees = initialGyro.getPitch();
    };

    @Override
    public void initialize() {
        isBalancing = true;
    }

    @Override
    public void execute() {
        // if (Math.abs(initialGyro.getPitch()) > 2) {
        // driveSystem.moveManual(0, (initialGyro.getPitch() / -30 +
        // (initialGyro.getPitch()/Math.abs(initialGyro.getPitch()) *-.1)), 0, 0,
        // Wheel.SpeedSetting.PRECISE);
        // }
        // SmartDashboard.putBoolean("Balancing", isBalancing);
        if (Math.abs(initialGyro.getPitch()) > 2) {
            double pitchAngleRadians = pitchAngleDegrees * (Math.PI / 180.0);
            double yAxisRate = Math.sin(pitchAngleRadians) * -1.8;
            driveSystem.moveManual(0, yAxisRate, 0, 0, Wheel.SpeedSetting.NORMAL);
        }
        SmartDashboard.putBoolean("Balancing", isBalancing);
    }

    @Override
    public boolean isFinished() {
        if (Math.abs(initialGyro.getPitch()) <= 2) {
            if (balanceTime < 0) {
                balanceTime = System.currentTimeMillis();
            } else if (System.currentTimeMillis() - balanceTime > 1000) {
                driveSystem.stop(); // if balanced, start counting. Reset if becomes unbalanced. finished if able to
                                    // remain balanced for one full second.
                isBalancing = false;
                return true;
            }
        } else {
            balanceTime = -1;
        }
        isBalancing = true;
        return false;
    }

}
