package frc.robot.Subsystems;
//1 neo

//2 limit switches(physical), one for max and one for min

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
<<<<<<< Updated upstream

import edu.wpi.first.wpilibj.DigitalInput;
=======
import edu.wpi.first.wpilibj2.command.SubsystemBase;
>>>>>>> Stashed changes


public class ArmExtension extends SubsystemBase {
    CANSparkMax armExtension = new CANSparkMax(9, MotorType.kBrushless);
<<<<<<< Updated upstream
    DigitalInput armExtentionLimitOne = new DigitalInput(5);
    DigitalInput armExtentionLimitTwo = new DigitalInput(6);
    // Limit switchs
    DigitalInput armRotationLimitOne = new DigitalInput(7);
    DigitalInput armRotationLimitTwo = new DigitalInput(8);
} 
=======

}

// TODO add methods for controling (basically what we have for arm pivot)
public void armExtender() {
    if (LidarSensor value == number && armEntend <= armExtendLimit) {
        armExtend.set(1);
     };
 }

  public void armRetracter() {
    if (LidarSensor.value == number && armRetract <= armRetractLimit) {
        armRetract.set(-1);
    };
  }
  public void armStopper() {
    if (LidarSensor.value == number) {
        armStop.set(0);
    };
  }
  public void armExtensionVariable(double speed) {
    if (LidarSensor.value == number ) {
        armExtVar.set(speed);
    };
  }

// TODO modify methods to only work within certain lidar range

// TODO make boolean methods for setpoints
>>>>>>> Stashed changes
