package frc.robot.Subsystems;
//2 double solenoids, 1 compressor, 1 PCH
//2 actuators, both work when pickup up a cone, one works when picking up a cube

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Actuators {
    //TODO assign channel numbers
    DoubleSolenoid actuatorOne = new DoubleSolenoid(0, PneumaticsModuleType.REVPH, 0, 0);
    DoubleSolenoid actuatorTwo = new DoubleSolenoid(0, PneumaticsModuleType.REVPH, 0, 0);
}
