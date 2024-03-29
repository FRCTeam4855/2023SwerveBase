package frc.robot.Subsystems;
//2 neos, 1 through bour encoder, 1 IMU

//2 limit switches, one for each end

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmSetpoint;
import static frc.robot.Constants.*;

public class ArmPivot extends SubsystemBase {
  double pivotSetpoint;
  CANSparkMax armPivotOne = new CANSparkMax(11, MotorType.kBrushless);
  CANSparkMax armPivotTwo = new CANSparkMax(12, MotorType.kBrushless);
  SparkMaxPIDController pivotPIDController = armPivotOne.getPIDController();

  public void setPivotDirectionForward() {
    armPivotOne.set(-1);
    armPivotTwo.set(-1);
  }

  public void setPivotDirectionBackward() {
    armPivotOne.set(1);
    armPivotTwo.set(1);
  }

  public void setPivotStop() {
    armPivotOne.set(0);
    armPivotTwo.set(0);
  }

  public void armPivotVariable(double speed) {
    armPivotOne.set(speed);
    // armPivotTwo.set(speed);
  }

  public double getPivotPostion() {
    return armPivotOne.getEncoder().getPosition();
  }

  public void resetPivotEncoderZero() {
    armPivotOne.getEncoder().setPosition(0);
  }

  public void resetPivotEncoderVariable(double value, double slotID) {
    armPivotOne.getEncoder().setPosition(value);
  }

  // Old manual control, but it resets the zero point so DO NOT USE
  // public void setPivotPositionVariable(){
  // pivotPIDController.setReference(armPivotOne.getEncoder().getPosition(),
  // CANSparkMax.ControlType.kPosition);
  // }

  // set reference encoder position manually BUT uses PID slot 2 on sparkmax
  // (static setpoints use slot 0)
  public void setPivotPositionVariable(double position) {
    pivotPIDController.setReference(position, CANSparkMax.ControlType.kPosition, 2);
  }

  public void initPivot() {
    // PID coefficients
    armPivotOne.restoreFactoryDefaults();
    armPivotOne.restoreFactoryDefaults();
    armPivotOne.setIdleMode(IdleMode.kBrake);
    armPivotTwo.setIdleMode(IdleMode.kBrake);
    armPivotTwo.follow(armPivotOne);
    double kP = 0.1; // slot 0 static setpoints
    double kP2 = 0.2; // slot 2 manual adjustment
    double kI = 0; // .0004;
    double kD = 0; // 1.2;
    double kIz = 0;
    double kFF = 0;
    double kMaxOutput = .20; // slot 0 pivot speed max
    double kMaxOutput2 = .4; // slot 2 pivot speed max
    double kMinOutput = -.20;
    double kMinOutput2 = -.4;
    pivotPIDController.setFeedbackDevice(armPivotOne.getEncoder());
    pivotPIDController.setP(kP);
    pivotPIDController.setP(kP2, 2);
    pivotPIDController.setI(kI);
    pivotPIDController.setD(kD);
    pivotPIDController.setIZone(kIz);
    pivotPIDController.setFF(kFF);
    pivotPIDController.setOutputRange(kMinOutput, kMaxOutput);
    pivotPIDController.setOutputRange(kMinOutput2, kMaxOutput2, 2);
  }

  public void setPivotSetpoint(ArmSetpoint armSetpoint) {
    if (armSetpoint == ArmSetpoint.One)
      pivotSetpoint = ARM_PIVOT_CENTER_1;
    if (armSetpoint == ArmSetpoint.Two)
      pivotSetpoint = ARM_PIVOT_CENTER_2;
    if (armSetpoint == ArmSetpoint.Three)
      pivotSetpoint = ARM_PIVOT_CENTER_3;
    if (armSetpoint == ArmSetpoint.Four)
      pivotSetpoint = ARM_PIVOT_CENTER_4;
    if (armSetpoint == ArmSetpoint.Five)
      pivotSetpoint = ARM_PIVOT_CENTER_5;
    if (armSetpoint == ArmSetpoint.Six)
      pivotSetpoint = ARM_PIVOT_CENTER_6;
  }

  public double getPivotSetpointPosition() {
    return pivotSetpoint;
  }

  public boolean isPivotAtSetpoint() {
    return getPivotPostion() - pivotSetpoint <= ARM_PIVOT_SLOP;
  }

  public void pivotDaArm() {
    // set PID coefficients
    pivotPIDController.setReference(pivotSetpoint, CANSparkMax.ControlType.kPosition);
    // SmartDashboard.putNumber("PivotSetPoint", pivotSetpoint);
    // SmartDashboard.putNumber("PivotVariable", getPivotPostion());

  }
}
