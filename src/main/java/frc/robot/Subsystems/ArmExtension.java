package frc.robot.Subsystems;
//1 neo
//2 limit switches(physical), one for max and one for min

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;

public class ArmExtension {
    //TODO assign device id
    CANSparkMax armExtension = new CANSparkMax(0, MotorType.kBrushless);
//TODO assign channel numbers
    DigitalInput armExtentionLimitOne = new DigitalInput(0);
    DigitalInput armExtentionLimitTwo = new DigitalInput(0);
}
