package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Subsystems.SwerveDriveSystem;

public class BasicMovements extends SubsystemBase implements GenericDriveSystem{

    SwerveDriveSystem driveValues = new SwerveDriveSystem();
    
    double y1 = 0;
    double x1 = 0;
    double x2 = 0;



    @Override
    public void moveForward() {
        // TODO Map very basic (and slow) movements to these for use in auton commands
        // TODO NOTE: we will need to impliment a (distance) argument to these so the robot know how far to go and commands know when to stop
    }

    @Override
    public void moveBackward() {
        
    }

    @Override
    public void moveLeft() {

        
    }

    @Override
    public void moveRight() {

        
    }

    @Override
    public void spinClockwise() {

        
    }

    @Override
    public void spinCounterclockwise() {

        
    }

    @Override
    public void stop() {

        
    }
    
}
