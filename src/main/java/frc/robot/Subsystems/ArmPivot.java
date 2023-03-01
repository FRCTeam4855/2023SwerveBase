package frc.robot.Subsystems;
//2 neos, 1 through bour encoder, 1 IMU
//2 limit switches, one for each end

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

<<<<<<< Updated upstream
public class ArmPivot {
=======
public class ArmPivot extends SubsystemBase{
  static double pivotSetpoint;
>>>>>>> Stashed changes
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
<<<<<<< Updated upstream
}
=======

  public double getCurrentPivot(){
    return armAbsEncoder.getAbsolutePosition(); //TODO need to do some tests on 2023 robot to find positions and negatives to work with below boolean
  }

  public static void setPivotSetpoint(ArmSetpoint armSetpoint) {
    if (armSetpoint == ArmSetpoint.One) pivotSetpoint = ARM_PIVOT_CENTER_1;
    if (armSetpoint == ArmSetpoint.Two) pivotSetpoint = ARM_PIVOT_CENTER_2;
    if (armSetpoint == ArmSetpoint.Three) pivotSetpoint = ARM_PIVOT_CENTER_3;
    if (armSetpoint == ArmSetpoint.Four) pivotSetpoint = ARM_PIVOT_CENTER_4;
    if (armSetpoint == ArmSetpoint.Five) pivotSetpoint = ARM_PIVOT_CENTER_5;
    
  } 
  public boolean isPivotAtSetpoint(){
    return ((getCurrentPivot() - pivotSetpoint)) <= ARM_PIVOT_SLOP; //TODO verify working on 2023 robot
  }

  }

>>>>>>> Stashed changes
