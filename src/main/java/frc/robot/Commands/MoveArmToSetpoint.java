// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.Constants.ArmSetpoint;
import frc.robot.Subsystems.ArmExtend;
import frc.robot.Subsystems.ArmPivot;

public class MoveArmToSetpoint extends CommandBase {
  private final ArmExtend armExtend;
  private final ArmPivot armPivot;
  private final Constants.ArmSetpoint setpointToUse;
  private final Constants.ArmSetpoint currentSetpoint;
  // WaitCommand pivotDelay = new WaitCommand(3);

  public MoveArmToSetpoint(ArmExtend extendToUse, ArmPivot pivotToUse, ArmSetpoint newArmSetpoint, ArmSetpoint oldArmSetpoint) {
    armExtend = extendToUse;
    armPivot = pivotToUse;
    currentSetpoint = oldArmSetpoint;
    setpointToUse = newArmSetpoint;
    addRequirements(pivotToUse, extendToUse);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

  if (setpointToUse == ArmSetpoint.One){
    if (currentSetpoint == ArmSetpoint.Five)
        armPivot.setPivotPositionVariable(21);
        Timer.delay(.5);
    armExtend.setExtendSetpoint(setpointToUse);    
    armExtend.extendDaArm();
    Timer.delay(.5);
    armPivot.setPivotSetpoint(setpointToUse);
    armPivot.pivotDaArm();
  }



  if (setpointToUse != ArmSetpoint.One){
    armPivot.setPivotSetpoint(setpointToUse);
    armPivot.pivotDaArm();
    Timer.delay(.5);
    armExtend.setExtendSetpoint(setpointToUse);    
    armExtend.extendDaArm();
  }


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
