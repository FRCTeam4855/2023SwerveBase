package frc.robot.Subsystems;
//2 double solenoids, 1 compressor, 1 PCH
//2 actuators, both work when pickup up a cone, one works when picking up a cube
//Auto position for game piece placement, high (cone/cube), mid (cone/cube)

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Subsystems.PneumaticsBase.*;

public class IntakePaws extends SubsystemBase {

    DoubleSolenoid pawLeft = new DoubleSolenoid(REV_PH_MODULE, PneumaticsModuleType.REVPH, 1, 2);
    DoubleSolenoid pawRight = new DoubleSolenoid(REV_PH_MODULE, PneumaticsModuleType.REVPH, 3, 4);

    public void setLeftPawOpen() {
        pawLeft.set(Value.kForward);        
    }

    public void setLeftPawClose() {
        pawLeft.set(Value.kReverse);
    }

    public boolean isLeftPawOpen() {
        return pawLeft.get() == Value.kForward;
    }

    public boolean isLeftPawClose() {
        return pawLeft.get() == Value.kReverse;
    }

//TODO JK: do the same thing for rightPaw
}
