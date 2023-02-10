package frc.robot.Subsystems;
//2 neos, 1 through bour encoder, 1 IMU
//2 limit switches, one for each end

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;

public class ArmRotation {
    CANSparkMax armRotationOne = new CANSparkMax(11, MotorType.kBrushless);
    CANSparkMax armRotationTwo = new CANSparkMax(12, MotorType.kBrushless);
    DutyCycleEncoder armAbsEncoder = new DutyCycleEncoder(99); // placeholder number
    //TODO ADD A NUMBER TO CHANNEL
}
