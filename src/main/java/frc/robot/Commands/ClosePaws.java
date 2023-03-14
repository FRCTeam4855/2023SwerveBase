// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.IntakePaws;

public class ClosePaws extends CommandBase {
  /** Creates a new TogglePaws. */
  private final IntakePaws intakePaws;

  public ClosePaws(IntakePaws thispPaws) {
    intakePaws = thispPaws;
    addRequirements(thispPaws);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

        intakePaws.setRightPawClose();
        intakePaws.setLeftPawClose();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
