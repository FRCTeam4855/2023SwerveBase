package frc.robot.Subsystems;
//1 neo
//2 limit switches(physical), one for max and one for min

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class ArmExtension {
    CANSparkMax armExtension = new CANSparkMax(9, MotorType.kBrushless);
    DigitalInput armExtentionLimitOne = new DigitalInput(5);
    DigitalInput armExtentionLimitTwo = new DigitalInput(6);
    // Limit switchs
    DigitalInput armRotationLimitOne = new DigitalInput(7);
    DigitalInput armRotationLimitTwo = new DigitalInput(8);
} 
