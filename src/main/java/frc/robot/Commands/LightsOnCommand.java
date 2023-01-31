package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.PrettyLights;

public class LightsOnCommand extends CommandBase{
    
    private PrettyLights prettyLights;
    private double color;

    public LightsOnCommand(PrettyLights newLightsToUse, double newColorToUse){
        super();
        prettyLights = newLightsToUse;
        color = newColorToUse;
        addRequirements(newLightsToUse);
    }

    @Override
    public void initialize() {
        
    }

    @Override
    public void execute() {
       prettyLights.setLEDs(color);
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
