package frc.robot.Subsystems;

import frc.robot.Subsystems.Actuators;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

//Pick up - floor and HP station
//swing through
//arm position inside of the robot

public class ArmPositions {
    //TODO: write code for arm positions
    Actuators actuators = new Actuators();
    /* 
    if (button == cone-button):
        highConePosition()
        move.arm to postion2
    else:
        pass;
*/
    public void highConePosition() {
        actuators.actuatorOne.set(Value.kreverse);
    }

    
}
