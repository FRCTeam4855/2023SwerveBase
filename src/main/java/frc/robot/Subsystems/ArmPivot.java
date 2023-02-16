package frc.robot.Subsystems;
//2 neos, 1 through bour encoder, 1 IMU
//2 limit switches, one for each end

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class ArmPivot {
  CANSparkMax armPivotOne = new CANSparkMax(11, MotorType.kBrushless);
  CANSparkMax armPivotTwo = new CANSparkMax(12, MotorType.kBrushless);
  DutyCycleEncoder armAbsEncoder = new DutyCycleEncoder(1337); // placeholder number
  //TODO ADD A NUMBER TO CHANNEL

  public void setPivotDirectionForward() {
    armPivotOne.set(1);
    armPivotTwo.set(1);
  }
  public void setPivotDirectionBackward() {
    armPivotOne.set(-1);
    armPivotTwo.set(-1);
  }
  public void setPivotStop() {
    armPivotOne.set(0);
    armPivotTwo.set(0);
  }
  public void armPivotVariable(double speed) {
    armPivotOne.set(speed);
    armPivotTwo.set(speed);
  }
}
