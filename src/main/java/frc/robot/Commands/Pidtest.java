package frc.robot.Commands;
//example of PID loop, not implimented anywhere
import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Pidtest {
    int P, I, D = 1;
    int integral, previous_error, setpoint = 0;
    Gyro gyro;
    private double pidvalue;


    public void setSetpoint(int setpoint)
    {
        this.setpoint = setpoint;
    }

    public double executePID(){
        double error = setpoint - gyro.getAngle(); // Error = Target - Actual
        integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        double derivative = (error - this.previous_error) / .02;
        this.pidvalue = P*error + I*this.integral + D*derivative;
        return (pidvalue);
    }


}

