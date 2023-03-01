package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmExtension extends SubsystemBase{
    CANSparkMax armExtension = new CANSparkMax(9, MotorType.kBrushless);
    double LIDAR_RANGE_MAX = 0; // maximum limit for arm extension TODO find value on 2023 bot
    double LIDAR_RANGE_MIN = 1; // minimum limit for arm extension TODO find value on 2023 bot

//     // TODO add methods for controling (basically what we have for arm pivot)
// public void armExtender() {
//     if (LidarSensor value == number && armEntend <= armExtendLimit) {
//         armExtend.set(1);
//      };
//  }

//     public void armRetracter() {
//         if (LidarSensor.value == number && armRetract <= armRetractLimit) {
//             armRetract.set(-1);
//         }
//         ;
//     }

//     public void armStopper() {
//         if (LidarSensor.value == number) {
//             armStop.set(0);
//         }
//         ;
//     }

//     public void armExtensionVariable(double speed) {
//         if (LidarSensor.value == number) {
//             armExtVar.set(speed);
//         }
//         ;
//     }

    // TODO modify methods to only work within certain lidar range

    // TODO make boolean methods for setpoints

}
