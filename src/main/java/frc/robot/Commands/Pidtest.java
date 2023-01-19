package frc.robot.Commands;

import edu.wpi.first.wpilibj.interfaces.Gyro;

public class Pidtest {
    int P, I, D = 1;
    int integral, previous_error, setpoint = 0;
    Gyro gyro;
    private double rcw;



    public void setSetpoint(int setpoint)
    {
        this.setpoint = setpoint;
    }

    public void PID(){
        double error = setpoint - gyro.getAngle(); // Error = Target - Actual
        this.integral += (error*.02); // Integral is increased by the error*time (which is .02 seconds using normal IterativeRobot)
        double derivative = (error - this.previous_error) / .02;
        this.rcw = P*error + I*this.integral + D*derivative;
    }

    public void execute()
    {
        PID();
    }
}

