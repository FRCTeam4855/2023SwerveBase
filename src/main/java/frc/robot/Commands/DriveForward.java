package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.BasicMovements;

 //looks like a even simpley version of SwerveDriveMoveForward.java, likely to get rolled into a more advanced command

public class DriveForward extends CommandBase{
    private final BasicMovements swerveDriveSystem;
       //SwerveDriveSystem //could be the subsystem
    public DriveForward(BasicMovements initialSwerveDriveSystem) {
        //driveSystem.moveManual(autox1, autoy1, autox2, 0);
        swerveDriveSystem = initialSwerveDriveSystem;
        addRequirements(swerveDriveSystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        swerveDriveSystem.moveForward();
    }

    @Override
    public boolean isFinished() {
        return true; //can change to be something like time > 2    
    }

}
