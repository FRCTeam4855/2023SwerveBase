package frc.robot.Subsystems;
//1 neo
//2 limit switches(physical), one for max and one for min

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogInput;

public class ArmExtension {
    CANSparkMax armExtension = new CANSparkMax(9, MotorType.kBrushless);
    AnalogInput lidarsSensor = new AnalogInput(0);
    double LIDAR_RANGE_MAX = 0; //TODO find value on 2023 bot
    double LIDAR_RANGE_MIN = 1; //TODO find value on 2023 bot


    //TODO add methods for controling (basically what we have for arm pivot)
    //TODO modify methods to only work within certain lidar range

    //TODO make boolean methods for setpoints

} 
