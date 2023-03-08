package frc.robot.Subsystems;
//1 neo

//2 limit switches(physical), one for max and one for min

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class ArmExtension extends SubsystemBase {
    CANSparkMax armExtend = new CANSparkMax(14, MotorType.kBrushless);

// // }

// // public void armExtender() {
// //     if (LidarSensor value == number && armEntend <= armExtendLimit) {
// //         armExtend.set(1);
// //      };
// //  }

// //   public void armRetracter() {
// //     if (LidarSensor.value == number && armRetract <= armRetractLimit) {
// //         armRetract.set(-1);
// //     };
// //   }
// //   public void armStopper() {
// //     if (LidarSensor.value == number) {
// //         armStop.set(0);
// //     };
// //   }
public RelativeEncoder getExtensionEncoder(){
  return armExtend.getEncoder();
  }

public double getExtensionPostion(){
  return getExtensionEncoder().getPosition();
}


public void armExtendVariable(double speed) {
    armExtend.set(speed);
  }

}
// // TODO make boolean methods for setpoints (???done???)
//   public void setPointOne() {
//     if (armExtender().value && ArmPivot.value != number)
//         armExtension.value + increaseValue
//         ArmPivot + increaseValue
    
//   } else {
//     armStopper();
//   };
