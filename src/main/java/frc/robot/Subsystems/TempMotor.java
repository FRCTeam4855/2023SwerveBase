package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TempMotor extends SubsystemBase {
    //for testbench testing purposes, delete
    public CANSparkMax aTempMotor = new CANSparkMax(14, MotorType.kBrushless);


    public void setTempMotorSpeed(double speed) {
        aTempMotor.set(speed);
    }

    public void setTempMotorStop() {
        aTempMotor.set(0);
    }

    public double getTempMotorSpeed() {
        return aTempMotor.get();
    }
    
}
