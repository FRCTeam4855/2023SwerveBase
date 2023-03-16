package frc.robot.Subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ArmSetpoint;
import static frc.robot.Constants.*;

public class ArmExtend extends SubsystemBase {
  double extendSetpoint;
  CANSparkMax armExtend = new CANSparkMax(14, MotorType.kBrushless);
  SparkMaxPIDController extendPIDController = armExtend.getPIDController();

  public void initExtend() {
    // PID coefficients
    armExtend.restoreFactoryDefaults();
    armExtend.setIdleMode(IdleMode.kBrake);
    double kP = 0.2; // slot 0 static setpoints
    double kP2 = 0.2; // slot 2 manual adjustment
    double kI = 0; // .0004;
    double kD = 0; // 1.2;
    double kIz = 0;
    double kFF = 0;
    double kMaxOutput = .4; // slot 0 extend speed max
    double kMaxOutput2 = .4; // slot 2 extend speed max
    double kMinOutput = -.9; // slot 0 retract speed max
    double kMinOutput2 = -.9; // slot 2 retract speed max
    extendPIDController.setFeedbackDevice(armExtend.getEncoder());
    extendPIDController.setP(kP);
    extendPIDController.setP(kP2, 2);
    extendPIDController.setI(kI);
    extendPIDController.setD(kD);
    extendPIDController.setIZone(kIz);
    extendPIDController.setFF(kFF);
    extendPIDController.setOutputRange(kMinOutput, kMaxOutput);
    extendPIDController.setOutputRange(kMinOutput2, kMaxOutput2, 2);
  }

  public void resetExtendEncoderZero() {
    armExtend.getEncoder().setPosition(0);
  }

  public void resetExtendEncoderVariable(double value) {
    armExtend.getEncoder().setPosition(value);
  }

  public void setExtendStop() {
    armExtend.set(0);
  }

  public void armExtendVariable(double speed) {
    armExtend.set(-speed); // negative here to compensate for reverse motor direction
  }

  public double getExtensionPostion() {
    return armExtend.getEncoder().getPosition();
  }

  // Old manual control, but it resets the zero point so DO NOT USE
  // public void setExtendPositionVariable(){
  // extendPIDController.setReference(armExtend.getEncoder().getPosition(),
  // CANSparkMax.ControlType.kPosition);
  // }

  // set reference encoder position manually BUT uses PID slot 2 on sparkmax
  // (static setpoints use slot 0)
  public void setExtendPositionVariable(double position) {
    extendPIDController.setReference(position, CANSparkMax.ControlType.kPosition, 2);

  }

  public void setExtendSetpoint(ArmSetpoint armSetpoint) {
    if (armSetpoint == ArmSetpoint.One)
      extendSetpoint = ARM_EXTEND_CENTER_1;
    if (armSetpoint == ArmSetpoint.Two)
      extendSetpoint = ARM_EXTEND_CENTER_2;
    if (armSetpoint == ArmSetpoint.Three)
      extendSetpoint = ARM_EXTEND_CENTER_3;
    if (armSetpoint == ArmSetpoint.Four)
      extendSetpoint = ARM_EXTEND_CENTER_4;
    if (armSetpoint == ArmSetpoint.Five)
      extendSetpoint = ARM_EXTEND_CENTER_5;
      if (armSetpoint == ArmSetpoint.Six)
      extendSetpoint = ARM_EXTEND_CENTER_6;
  }

  public boolean isPivotAtSetpoint() {
    return getExtensionPostion() - extendSetpoint <= ARM_EXTEND_SLOP;
  }

  public void extendDaArm() {
    // set PID coefficients
    extendPIDController.setReference(extendSetpoint, CANSparkMax.ControlType.kPosition);
    // SmartDashboard.putNumber("ExtendSetPoint", extendSetpoint);
    // SmartDashboard.putNumber("ExtendVariable", getExtensionPostion());

  }

}
