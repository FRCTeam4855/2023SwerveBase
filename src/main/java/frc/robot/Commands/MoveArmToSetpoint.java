// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.ArmSetpoint;
import frc.robot.Subsystems.ArmExtend;
import frc.robot.Subsystems.ArmPivot;

public class MoveArmToSetpoint extends CommandBase {
  private final ArmExtend armExtend;
  private final ArmPivot armPivot;
  private final Constants.ArmSetpoint setpointToUse;

  public MoveArmToSetpoint(ArmExtend extendToUse, ArmPivot pivotToUse, ArmSetpoint setpoint) {
    armExtend = extendToUse;
    armPivot = pivotToUse;
    setpointToUse = setpoint;
    addRequirements(pivotToUse, extendToUse);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    armExtend.setExtendSetpoint(setpointToUse);
    armPivot.setPivotSetpoint(setpointToUse);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    armExtend.extendDaArm();
    armPivot.pivotDaArm();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }
  // TODO joystick interrupts

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
