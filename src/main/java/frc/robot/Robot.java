/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import static frc.robot.Constants.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Subsystems.PrettyLights;
import frc.robot.Subsystems.SwerveDriveSystem;
import frc.robot.Subsystems.Wheel;
import frc.robot.Commands.LightsOnCommand;
import frc.robot.Commands.Pidtest;
import frc.robot.Commands.SwerveDriveMoveForward;
import edu.wpi.first.cameraserver.CameraServer;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;





public class Robot extends TimedRobot { 
  // Creates UsbCamera and MjpegServer [1] and connects them
CameraServer.startAutomaticCapture();

// Creates the CvSink and connects it to the UsbCamera
CvSink cvSink = CameraServer.getVideo();

// Creates the CvSource and MjpegServer [2] and connects them
CvSource outputStream = CameraServer.putVideo("Blur", 640, 480);


  
 
  double theta_radians; //theta_radians is difference the angle the robot is at, and the zerod angle
  boolean driverOriented = true; //where the robot is in driver oriented or not

  private static final String kAuton1 = "Leave Tarmac"; 
  private static final String kAuton2 = "Shoot High Leave Tarmac"; 
  private static final String kAuton3 = "Shoot High Intake Cargo"; 
  private static final String kAuton4 = "Shoot 2 High Leave Tarmac";

  private String m_autoSelected; //This selects between the two autonomous
  public SendableChooser<String> m_chooser = new SendableChooser<>(); //creates the ability to switch between autons on SmartDashboard

  XboxController xboxDriver = new XboxController(0);
  XboxController xboxOperator = new XboxController(1); 
  AHRS gyro = new AHRS(SPI.Port.kMXP); //defines the gyro
  SwerveDriveSystem driveSystem = new SwerveDriveSystem();
  private PrettyLights prettyLights1 = new PrettyLights();
  
  double autox1 = 0; //defines left and right movement for auton
  double autox2 = 0; //defines spinning movement for auton
  double autoy1 = 0; //defines forward and backward movement for auton
  boolean isBalancing = false;

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    m_chooser.setDefaultOption("Leave Tarmac", kAuton1);
    m_chooser.addOption("Shoot High Leave Tarmac", kAuton2);
    m_chooser.addOption("Shoot High Intake Cargo", kAuton3);
    m_chooser.addOption("Shoot 2 High Leave Tarmac", kAuton4);
    SmartDashboard.putData(m_chooser); //displays the auton options in shuffleboard
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {

    SmartDashboard.putNumber("Encoder FL", driveSystem.wheelFL.getAbsoluteValue()); //Displays Front Left Wheel Encoder Values
    //SmartDashboard.putNumber("Encoder BL", driveSystem.wheelBL.getAbsoluteValue()); //Displays Back Left Wheel Encoder Values
    //SmartDashboard.putNumber("Encoder BR", driveSystem.wheelBR.getAbsoluteValue()); //Displays Back Right Wheel Encoder Values
    //SmartDashboard.putNumber("Encoder FR", driveSystem.wheelFR.getAbsoluteValue()); //Displays Front Right Wheel Encoder Values
    SmartDashboard.putNumber("DriveEncoder FL", driveSystem.getEncoderFL());
    SmartDashboard.putBoolean("Driver Oriented", driverOriented); //shows true/false for driver oriented
    SmartDashboard.putNumber("Gyro Get Yaw", gyro.getYaw()); //pulls yaw value
    SmartDashboard.putNumber("Gyro Get Pitch", gyro.getPitch()); //pulls Pitch value
    SmartDashboard.putNumber("Encoder FL FT", driveSystem.getRelativeEncoderFT());
    SmartDashboard.putBoolean("Balancing", isBalancing); //shows true if robot is attempting to balance

    CommandScheduler.getInstance().run(); // must be called from the robotPeriodic() method Robot class or the scheduler will never run, and the command framework will not work
  }

  @Override
  public void disabledInit() {    
  }
  
  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected(); //pulls auton option selected from shuffleboard
    SmartDashboard.putString("auton selected", m_autoSelected); //displays which auton is currently running
    driveSystem.resetRelativeEncoders();
    gyro.reset();
    // CommandScheduler.getInstance().setDefaultCommand(driveSystem, new SwerveDriveStop(driveSystem));
    // SwerveDriveMoveForward swerveDriveMoveForward = new SwerveDriveMoveForward(driveSystem);
    // swerveDriveMoveForward.schedule();
  }

  // final double kP = 0.3;
  // double encoderSetpoint = 0;

  @Override
  public void autonomousPeriodic() {
    driveSystem.moveManual(autox1, autoy1, autox2, 0, 1);

      driveSystem.resetRelativeEncoders();
      // encoderSetpoint = 1;  
    // double encoderPositionFT = driveSystem.getRelativeEncoderFT();
    // double driverError = encoderSetpoint - encoderPositionFT;
    // double outputSpeed = kP * driverError;
    // driveSystem.moveManual(autox1, outputSpeed, autox2, 0);
    // SmartDashboard.putNumber("outputSpeed", outputSpeed);
    switch (m_autoSelected) {      
      case kAuton1: 
      default: 
      break;
      case kAuton2:
        break;
      case kAuton3:
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

    prettyLights1.setLEDs(PrettyLights.BLUE_VIOLET);
  }
  /**
   * This function is called periodically during operator control.
   */
  //  final double kP = 0.1;
  //  double encoderSetpoint = 0;

  @Override
  public void teleopPeriodic() { 
    double x1 = xboxDriver.getRawAxis(0); //connects the left and right drive movements to the drive controllers left x-axis
    double x2 = xboxDriver.getRawAxis(4); //connects the spinning drive movements to the drive controllers right x-axis
    double y1 = xboxDriver.getRawAxis(1); //connects the forwards and backwards drive movements to the drive controllers left y-axis
    // if (xboxDriver.getRawButton(1)) { //button A
    //   driveSystem.resetRelativeEncoders();
    //   encoderSetpoint = 10;
    // }else if (xboxDriver.getRawButton(ENCODER_RESET)) { //button B
    //   encoderSetpoint = 0;
    // }   
    // double encoderPositionFT = driveSystem.getRelativeEncoderFT();
    // double driverError = encoderSetpoint - encoderPositionFT;
    // double outputSpeed = kP * driverError;
    // driveSystem.moveManual(x1, outputSpeed, x2, 0);
    // SmartDashboard.putNumber("outputSpeed", outputSpeed);    

    //this tells the robot when it should be driverOriented or robotOriented
    if (driverOriented) {
      theta_radians = gyro.getYaw() * Math.PI / 180; //driverOriented
    } else theta_radians = 0; //robotOriented

    // Drive the robot
    Wheel.SpeedSetting driveSpeed = Wheel.SpeedSetting.NORMAL;
    if (xboxDriver.getRawButton(DRV_SPD_LIMITER_RB)) driveSpeed = Wheel.SpeedSetting.TURBO;
    if (xboxDriver.getRawAxis(2) > .5) driveSpeed = Wheel.SpeedSetting.PRECISE;
    driveSystem.moveManual(x1, y1, x2, theta_radians, driveSpeed);

    if (xboxDriver.getRawButton(TEST_PID_ROTATE_A) && Math.abs(gyro.getPitch()) > 2){
      isBalancing = true;
      driveSystem.moveManual(0, (gyro.getPitch()/-40), 0 , theta_radians, driveSpeed);
    } else {
      isBalancing = false;
    }

    
    // Reset the relative encoders if you press B button
    if (xboxDriver.getRawButtonPressed(ENCODER_RESET_B)) {
      driveSystem.resetRelativeEncoders();
    }

    //zeros the gyro if you press the Y button
    if (xboxDriver.getRawButtonPressed(GYRO_RESET_Y)) { 
      gyro.reset();
    }

    //This toggles driver oriented on or off when x is pressed
    if (xboxDriver.getRawButtonPressed(ORIENTATION_TOGGLE_X)){
      driverOriented = !driverOriented;
    }

    if (xboxOperator.getRawButtonPressed(SCHEDULE_INITIAL_COMMAND_LB)) {
     
      Command moveForward = new SwerveDriveMoveForward(driveSystem, 10);
      Command setupInitialLights = new LightsOnCommand(prettyLights1, PrettyLights.BPM_PARTYPALETTE);
      Command setupPostMoveLights = new LightsOnCommand(prettyLights1, PrettyLights.LARSONSCAN_RED);
      Command middleLights = new LightsOnCommand(prettyLights1, PrettyLights.BLUE_GREEN);
      Command lightsAtTheEnd = new LightsOnCommand(prettyLights1, PrettyLights.HEARTBEAT_BLUE);
      
      CommandScheduler.getInstance().schedule(
        setupInitialLights
          .andThen(new WaitCommand(5))
          .andThen(setupPostMoveLights)
          .andThen(new WaitCommand(5))
          .andThen(middleLights)
          .andThen(new WaitCommand(5))
          .andThen(lightsAtTheEnd) 
        );
    }
  }
  
   
  //This function is called periodically during test mode.
  @Override
  public void testPeriodic() {

  }
  
}