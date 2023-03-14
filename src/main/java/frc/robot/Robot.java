/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import static frc.robot.Constants.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Subsystems.ArmExtend;
import frc.robot.Subsystems.ArmPivot;
import frc.robot.Subsystems.IntakePaws;
import frc.robot.Subsystems.PrettyLights;
import frc.robot.Subsystems.SwerveDriveSystem;
import frc.robot.Subsystems.Wheel;
import frc.robot.Subsystems.Limelight;
import frc.robot.Commands.Balancing;
import frc.robot.Commands.ClosePaws;
// import frc.robot.Commands.CenterToLimelight;
import frc.robot.Commands.LightsOnCommand;
import frc.robot.Commands.MoveArmToSetpoint;
import frc.robot.Commands.OpenPaws;
import frc.robot.Commands.SwerveDriveMoveBackward;
import frc.robot.Commands.SwerveDriveMoveForward;
import frc.robot.Commands.SwerveDriveMoveLeft;
import frc.robot.Commands.SwerveDriveMoveRight;
import frc.robot.Commands.SwerveDriveTurnLeft;
import frc.robot.Commands.SwerveDriveTurnRight;

public class Robot extends TimedRobot {

  boolean fieldOriented; // robot is in field oriented or robot oriented
  double theta_radians; // gyro angle offset field oriented zero vs robot zero (front) for swerve calcs
  Wheel.SpeedSetting driveSpeed = Wheel.SpeedSetting.NORMAL;

  private static final String kAuton1 = "Leave Tarmac";
  private static final String kAuton2 = "Shoot High Leave Tarmac";
  private static final String kAuton3 = "Shoot High Intake Cargo";
  private static final String kAuton4 = "Shoot 2 High Leave Tarmac";

  private String m_autoSelected; // This selects between the two autonomous
  public SendableChooser<String> m_chooser = new SendableChooser<>(); // creates the ability to switch between autons on
                                                                      // SmartDashboard
  XboxController xboxDriver = new XboxController(0);
  XboxController xboxOperator = new XboxController(1);
  AHRS gyro = new AHRS(SerialPort.Port.kUSB); // defines the gyro
  SwerveDriveSystem driveSystem = new SwerveDriveSystem();
  IntakePaws intakePaws = new IntakePaws();
  private Limelight limelight = new Limelight();
  double autox1 = 0; // defines left and right movement for auton
  double autox2 = 0; // defines spinning movement for auton
  double autoy1 = 0; // defines forward and backward movement for auton
  ArmPivot armPivot = new ArmPivot();
  public ArmExtend armExtend = new ArmExtend();

  // command related declarations
  private PrettyLights prettyLights1 = new PrettyLights();
  Command moveForward = new SwerveDriveMoveForward(driveSystem, 10);
  Command setupInitialLights = new LightsOnCommand(prettyLights1, PrettyLights.BPM_PARTYPALETTE);
  Command setupPostMoveLights = new LightsOnCommand(prettyLights1, PrettyLights.LARSONSCAN_RED);
  Command middleLights = new LightsOnCommand(prettyLights1, PrettyLights.BLUE_GREEN);
  Command lightsAtTheEnd = new LightsOnCommand(prettyLights1, PrettyLights.HEARTBEAT_BLUE);
  Command moveArmToOne = new MoveArmToSetpoint(armExtend, armPivot, ArmSetpoint.One);
  Command moveArmToTwo = new MoveArmToSetpoint(armExtend, armPivot, ArmSetpoint.Two);
  Command moveArmToThree = new MoveArmToSetpoint(armExtend, armPivot, ArmSetpoint.Three);
  Command moveArmToFour = new MoveArmToSetpoint(armExtend, armPivot, ArmSetpoint.Four);
  Command openPaws = new OpenPaws(intakePaws);
  Command closePaws = new ClosePaws(intakePaws);
  

  

  // *************************
  // ********robotInit********
  // *************************
  // This function is run when the robot is first started up and should be
  // used for any initialization code.

  @Override
  public void robotInit() {
    prettyLights1.setLEDs(PrettyLights.BPM_RAINBOWPALETTE);
    fieldOriented = false;
    armExtend.initExtend();
    armPivot.initPivot();
    intakePaws.setRightPawOpen();
    intakePaws.setLeftPawOpen();
    m_chooser.setDefaultOption("Leave Tarmac", kAuton1);
    m_chooser.addOption("Shoot High Leave Tarmac", kAuton2);
    m_chooser.addOption("Shoot High Intake Cargo", kAuton3);
    m_chooser.addOption("Shoot 2 High Leave Tarmac", kAuton4);
    SmartDashboard.putData(m_chooser); // displays the auton options in shuffleboard, put in init block
    armPivot.resetPivotEncoderZero();
    armExtend.resetExtendEncoderVariable(50);

  }

  // *************************
  // ******robotPeriodic******
  // *************************
  // This function is called every robot packet, no matter the mode. Use
  // this for items like diagnostics that you want ran during disabled,
  // autonomous, teleoperated and test.This runs after the mode specific
  // periodic functions, but before LiveWindow and SmartDashboard
  // integrated updating.

  @Override
  public void robotPeriodic() {
    limelight.updateDashboard(); // runs block in limeight subsystem for periodic update
    SmartDashboard.putNumber("Encoder FL", driveSystem.wheelFL.getAbsoluteValue()); // Front Left Wheel Encoder Values
    SmartDashboard.putNumber("Encoder BL", driveSystem.wheelBL.getAbsoluteValue()); // Back Left Wheel Encoder Values
    SmartDashboard.putNumber("Encoder BR", driveSystem.wheelBR.getAbsoluteValue()); // Back Right Wheel Encoder Values
    SmartDashboard.putNumber("Encoder FR", driveSystem.wheelFR.getAbsoluteValue()); // Front Right Wheel Encoder Values
    SmartDashboard.putNumber("DriveEncoder FL", driveSystem.getEncoderFL());

    SmartDashboard.putBoolean("Field Oriented", fieldOriented); // shows true/false for driver oriented
    SmartDashboard.putNumber("Gyro Get Yaw", gyro.getYaw()); // pulls yaw value
    SmartDashboard.putNumber("Gyro Get Pitch", gyro.getPitch()); // pulls Pitch value
    SmartDashboard.putNumber("Encoder FL FT", driveSystem.getRelativeEncoderFT());
    SmartDashboard.putBoolean("Balancing", Balancing.isBalancing); // shows true if robot is attempting to balance
    SmartDashboard.putNumber("ExtendEncoderRead", armExtend.getExtensionPostion());
    SmartDashboard.putNumber("PivotEncoderRead", armPivot.getPivotPostion());

    CommandScheduler.getInstance().run(); // must be called from the robotPeriodic() method Robot class or the scheduler
                                          // will never run, and the command framework will not work

  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  // *************************
  // *****autonomousInit******
  // *************************

  @Override
  public void autonomousInit() {
    driveSystem.resetRelativeEncoders();
    m_autoSelected = m_chooser.getSelected(); // pulls auton option selected from shuffleboard
    SmartDashboard.putString("Current Auton:", m_autoSelected); // displays which auton is currently running
    gyro.reset();
  }

  // *************************
  // ***autonomousPeriodic****
  // *************************

  @Override
  public void autonomousPeriodic() {
    // driveSystem.moveManual(autox1, autoy1, autox2, 0, 1);

    // driveSystem.resetRelativeEncoders();

    switch (m_autoSelected) {
      case kAuton1:

         CommandScheduler.getInstance().schedule(
          setupInitialLights
          .andThen(new MoveArmToSetpoint(armExtend, armPivot, ArmSetpoint.One))
          .andThen(closePaws)
          .andThen(new SwerveDriveMoveForward(driveSystem, 3))
         );
        //     setupInitialLights, moveForward, 
        // .andThen(new WaitCommand(2))
        // .andThen(setupPostMoveLights)
        // .andThen(new WaitCommand(2))
        // .andThen(middleLights)
        // .andThen(new WaitCommand(2))
        // .andThen(floridaMansLights)
        // .andThen(new WaitCommand(5))
        // .andThen(lightsAtTheEnd)
        // );
      default:
        break;
      case kAuton2:
        CommandScheduler.getInstance().schedule(
         setupInitialLights
          .andThen( new MoveArmToSetpoint(armExtend, armPivot, ArmSetpoint.One))
          .andThen(closePaws)
          //dropping cone
          .andThen( new SwerveDriveMoveBackward(driveSystem, ATON_DIST_TWO))
          .andThen( new MoveArmToSetpoint(armExtend, armPivot, ArmSetpoint.Four))
          .andThen(openPaws)
          //moving out of community
          .andThen( new SwerveDriveMoveForward(driveSystem, ATON_DIST_ONE))
        );
        break;
      case kAuton3:
        CommandScheduler.getInstance().schedule(
         setupInitialLights
          .andThen( new MoveArmToSetpoint(armExtend, armPivot, ArmSetpoint.One))
          .andThen(closePaws)
          //dropping cone
          .andThen( new SwerveDriveMoveBackward(driveSystem, ATON_DIST_TWO))
          .andThen( new MoveArmToSetpoint(armExtend, armPivot, ArmSetpoint.Four))
          .andThen(openPaws)
          //moving out of community
          .andThen( new SwerveDriveMoveForward(driveSystem, ATON_DIST_ONE))
          //moving onto platform
          .andThen( new SwerveDriveMoveBackward(driveSystem, ATON_DIST_TWO))
          // // or, depending on where we are...
          // .andThen( new SwerveDriveMoveLeft(driveSystem, 20))
          // .andThen( new SwerveDriveMoveBackward(driveSystem, ATON_DIST_TWO))
          // // or we could have
          // .andThen( new SwerveDriveMoveRight(driveSystem, 20))
          // .andThen( new SwerveDriveMoveBackward(driveSystem, ATON_DIST_TWO))
          // // end them all with balancing
          .andThen(new Balancing(driveSystem, gyro)) 

        );
        break;
      case kAuton4:
        break;
    }
    // driveSystem.moveManual(autox1, autoy1, autox2, 0);
  }

  @Override
  public void teleopInit() {
    driveSystem.resetRelativeEncoders();
    gyro.reset();
    gyro.zeroYaw();
    prettyLights1.setLEDs(PrettyLights.C1_AND_C2_SINELON);
    armExtend.setExtendSetpoint(ArmSetpoint.One);
    armPivot.setPivotSetpoint(ArmSetpoint.One);
  }

  // *************************
  // *****teleopPeriodic******
  // *************************
  // This function is called periodically during operator control.

  @Override
  public void teleopPeriodic() {

    double x1 = -xboxDriver.getRawAxis(0); // set left and right drive movements to drive controller left x-axis
    double x2 = -xboxDriver.getRawAxis(4); // set rotate drive movements to drive controller right x-axis
    double y1 = -xboxDriver.getRawAxis(1); // set forwards and backwards drive movements to drive controller left y-axis

    // *************************
    // *****Driver Controls*****
    // *************************

    // this calculation is used for swerve depending on fieldOriented or robotOriented
    if (fieldOriented) {
      theta_radians = gyro.getYaw() * Math.PI / 180; // FieldOriented (whatever encoder 0 value is = forward)
    } else
      theta_radians = 0; // RobotOriented (robot front is always forward)

    // Drive the robot
    if ((!xboxDriver.getRawButton(DRV_SPD_TURBO_RB)) && (!xboxDriver.getRawButton(DRV_SPD_PRECISE_LB))) {
      driveSpeed = Wheel.SpeedSetting.NORMAL;
     } // sets speed back to normal every 20ms
    if (xboxDriver.getRawButton(DRV_SPD_TURBO_RB)){
      driveSpeed = Wheel.SpeedSetting.TURBO;
    }
    if (xboxDriver.getRawButton(DRV_SPD_PRECISE_LB)){
      driveSpeed = Wheel.SpeedSetting.PRECISE;
    }
    driveSystem.moveManual(x1, y1, x2, theta_radians, driveSpeed);

    // Reset the relative encoders if you press B button
    if (xboxDriver.getRawButtonPressed(ENCODER_RESET_B)) {
      driveSystem.resetRelativeEncoders();
    }

    // zeros the gyro if you press the Y button
    if (xboxDriver.getRawButtonPressed(GYRO_RESET_Y)) {
      gyro.reset();
    }

    // This toggles field oriented on or off when x is pressed
    if (xboxDriver.getRawButtonPressed(ORIENTATION_TOGGLE_X)) {
      fieldOriented = !fieldOriented;
    }

    // *************************
    // ****Operator Controls****
    // *************************

    // toggle paws open and closed
    if (xboxOperator.getRawButtonPressed(PAWS_TOGGLEBOTH_SELECT)) {
      if (intakePaws.isRightPawOpen() && intakePaws.isLeftPawOpen()) {
        intakePaws.setRightPawClose();
        intakePaws.setLeftPawClose();
      } else {
        if (intakePaws.isRightPawClose() && intakePaws.isLeftPawClose()) {
          intakePaws.setRightPawOpen();
          intakePaws.setLeftPawOpen();
        }
      }
    }

    // if (xboxOperator.getRawButton(TOGGLE_LL_PIPELINE_RB)) {
    //   if (limelight.isLimelightOnAprilTagMode() == true) {
    //     limelight.setLimelightPipeToRetroTape();
    //   } else {
    //     limelight.setLimelightPipeToAprilTag();
    //   }
    // }

    if (xboxOperator.getRawButton(ARM_SETPOINT1_A)) {
      armExtend.setExtendSetpoint(ArmSetpoint.One);
      armPivot.setPivotSetpoint(ArmSetpoint.One);
      armExtend.extendDaArm();
      armPivot.pivotDaArm();
    }

    if (xboxOperator.getRawButton(ARM_SETPOINT2_B)) {
      armExtend.setExtendSetpoint(ArmSetpoint.Two);
      armPivot.setPivotSetpoint(ArmSetpoint.Two);
      armExtend.extendDaArm();
      armPivot.pivotDaArm();
    }

    if (xboxOperator.getRawButton(ARM_SETPOINT3_X)) {
      armExtend.setExtendSetpoint(ArmSetpoint.Three);
      armPivot.setPivotSetpoint(ArmSetpoint.Three);
      armExtend.extendDaArm();
      armPivot.pivotDaArm();
    }

    if (xboxOperator.getRawButton(ARM_SETPOINT4_Y)) {
      armExtend.setExtendSetpoint(ArmSetpoint.Four);
      armPivot.setPivotSetpoint(ArmSetpoint.Four);
      armExtend.extendDaArm();
      armPivot.pivotDaArm();
    }

    if (xboxOperator.getRawButtonPressed(5)){ //Not used
      armExtend.setExtendSetpoint(ArmSetpoint.Five);
      armPivot.setPivotSetpoint(ArmSetpoint.Five);
      armExtend.extendDaArm();
      armPivot.pivotDaArm(); 
    }

    // if (xboxOperator.getRawButton(8)) {
    //   armExtend.extendDaArm();
    //   armPivot.pivotDaArm();
    // }
    
      if (Math.abs((xboxOperator.getRawAxis(5))) > JOYSTK_DZONE) {
        armExtend.resetExtendEncoderVariable(armExtend.getExtensionPostion()+2);
      }
    
      if (Math.abs((xboxOperator.getRawAxis(1))) > JOYSTK_DZONE) {
        armPivot.resetPivotEncoderVariable(armPivot.getPivotPostion()+.14);
      } 

    //*******OLD JOYSTICK COMMANDS
    // if (xboxOperator.getRawButton(6)) {
    //   if (Math.abs((xboxOperator.getRawAxis(1))) > JOYSTK_DZONE) {
    //     armPivot.armPivotVariable(xboxOperator.getRawAxis(1) / -8);
    //   } else {
    //     armPivot.setPivotStop();
    //   }

    //   if (Math.abs((xboxOperator.getRawAxis(5))) > JOYSTK_DZONE) {
    //     armExtend.armExtendVariable(xboxOperator.getRawAxis(5));
    //   } else {
    //     armExtend.setExtendStop();
    //   }
    // }


    if (xboxDriver.getRawButtonPressed(9)) {
      // Command moveLeft = new SwerveDriveMoveLeft(driveSystem, 3);
      // Command moveFwd = new SwerveDriveMoveForward(driveSystem, 3);
      // Command moveRight = new SwerveDriveMoveRight(driveSystem, 3);
      // Command moveBack = new SwerveDriveMoveBackward(driveSystem, 3);
      // Command turnRight = new SwerveDriveTurnRight(driveSystem, 90);
      // Command turnLeft = new SwerveDriveTurnLeft(driveSystem, 180);
      // Command balance = new Balancing(driveSystem, gyro);
      Command setupPostMoveLights = new LightsOnCommand(prettyLights1, PrettyLights.LARSONSCAN_RED);
      // Command middleLights = new LightsOnCommand(prettyLights1, PrettyLights.BLUE_GREEN);
      // Command floridaMansLights = new LightsOnCommand(prettyLights1, PrettyLights.C1_AND_C2_END_TO_END_BLEND);
      // Command lightsAtTheEnd = new LightsOnCommand(prettyLights1, PrettyLights.HEARTBEAT_BLUE);
      CommandScheduler.getInstance().schedule(
          setupPostMoveLights
          
           );

              /*        figure eight, with fancy lights
               * .andThen(new SwerveDriveTurnLeft(driveSystem, 45))
               * .andThen(new SwerveDriveMoveForward(driveSystem, 10))
               * .andThen(new SwerveDriveMoveRight(driveSystem, 6))
               * .andThen(new SwerveDriveTurnRight(driveSystem, 240))
               * .andThen(new LightsOnCommand(prettyLights1,
               * PrettyLights.C1_AND_C2_END_TO_END_BLEND))
               * .andThen(new SwerveDriveMoveForward(driveSystem, 16))
               * .andThen(new SwerveDriveMoveLeft(driveSystem, 8))
               * .andThen(new SwerveDriveMoveBackward(driveSystem, 8))
               * .andThen(new LightsOnCommand(prettyLights1, PrettyLights.HEARTBEAT_BLUE))
               * .andThen(new SwerveDriveMoveRight(driveSystem, 14))
               */
              /*       auton balance
              .andThen(new SwerveDriveMoveForward(driveSystem, 15))
              .andThen(new SwerveDriveTurnLeft(driveSystem, 140))
              .andThen(new SwerveDriveMoveBackward(driveSystem, 7))
              .andThen(new MoveArmToSetpoint(armExtend, armPivot, ArmSetpoint.Five))
              .andThen(balance));
              */
    }
    ;
    // Command moveForward = new SwerveDriveMoveForward(driveSystem, 10);
    // Command setupInitialLights = new LightsOnCommand(prettyLights1,
    // PrettyLights.BPM_PARTYPALETTE);
    // Command setupPostMoveLights = new LightsOnCommand(prettyLights1,
    // PrettyLights.LARSONSCAN_RED);
    // Command middleLights = new LightsOnCommand(prettyLights1,
    // PrettyLights.BLUE_GREEN);
    // Command floridaMansLights = new LightsOnCommand(prettyLights1,
    // PrettyLights.C1_AND_C2_END_TO_END_BLEND);
    // Command lightsAtTheEnd = new LightsOnCommand(prettyLights1,
    // PrettyLights.HEARTBEAT_BLUE);

    // CommandScheduler.getInstance().schedule(
    // setupInitialLights
    // .andThen(new WaitCommand(2))
    // .andThen(setupPostMoveLights)
    // .andThen(new WaitCommand(2))
    // .andThen(middleLights)
    // .andThen(new WaitCommand(2))
    // .andThen(floridaMansLights)
    // .andThen(new WaitCommand(5))
    // .andThen(lightsAtTheEnd)
    // );
  }

  // This function is called periodically during test mode.
  @Override
  public void testPeriodic() {

  }

}