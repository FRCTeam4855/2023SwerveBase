// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ArmSetpoint;
import frc.robot.Subsystems.ArmExtend;
import frc.robot.Subsystems.ArmPivot;

public class MoveArmToSetpoint extends CommandBase {

  private ArmExtend armExtend;
  private ArmPivot armPivot;
  private double startExtendEncoderSetpoint; 
  private double goalExtendEncoderSetpoint; 
  private double startPivotEncoderSetpoint; 
  private double goalPivotEncoderSetpoint;
  private ArmSetpoint startArmSetpoint;
  private ArmSetpoint goalArmSetpoint; 

  public MoveArmToSetpoint(ArmExtend armExtend, ArmPivot armPivot, ArmSetpoint goalArmSetpoint, ArmSetpoint startArmSetpoint) {
    this.armExtend = armExtend;
    this.armPivot = armPivot;
    this.startArmSetpoint = startArmSetpoint;
    this.goalArmSetpoint = goalArmSetpoint;
    addRequirements(armExtend, armPivot);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startExtendEncoderSetpoint = armExtend.getExtensionPostion();
    // goalExtendEncoderSetpoint = armExtend.getExtendSetpointPosition();
    startPivotEncoderSetpoint = armPivot.getPivotPostion();
    // goalPivotEncoderSetpoint = armPivot.getPivotSetpointPosition();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    //New timer version
    if (goalArmSetpoint == ArmSetpoint.One) {
      armExtend.setExtendSetpoint(goalArmSetpoint);
      armExtend.extendDaArm();
      goalExtendEncoderSetpoint = armExtend.getExtendSetpointPosition();
      if (Math.abs(startExtendEncoderSetpoint - armExtend.getExtensionPostion()) > Math.abs(startExtendEncoderSetpoint - goalExtendEncoderSetpoint)/2)  {
        armPivot.setPivotSetpoint(goalArmSetpoint);
        armPivot.pivotDaArm();
      }
    }

    if (goalArmSetpoint != ArmSetpoint.One) {
      armPivot.setPivotSetpoint(goalArmSetpoint);
      armPivot.pivotDaArm();
      if (Math.abs(startPivotEncoderSetpoint - armPivot.getPivotPostion()) > Math.abs(startPivotEncoderSetpoint - goalPivotEncoderSetpoint)/2)  {
        armExtend.setExtendSetpoint(goalArmSetpoint);
        armExtend.extendDaArm();
      }
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (armPivot.isPivotAtSetpoint() == true && armExtend.isExtendAtSetpoint() == true){
      return true;
    }
      return false;
  }
}
