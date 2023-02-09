package frc.robot.Subsystems;
//2 neos, 1 through bour encoder, 1 IMU
//2 limit switches, one for each end

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class ArmRotation {
    //TODO assign device id
    CANSparkMax armRotationOne = new CANSparkMax(0, MotorType.kBrushless);
    CANSparkMax armRotationTwo = new CANSparkMax(0, MotorType.kBrushless);
//TODO assign channel numbers
    DigitalInput armRotationLimitOne = new DigitalInput(0);
    DigitalInput armRotationLimitTwo = new DigitalInput(0);
}
