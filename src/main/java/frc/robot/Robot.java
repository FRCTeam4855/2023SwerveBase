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
import frc.robot.Subsystems.ArmPivot;
import frc.robot.Subsystems.IntakePaws;
import frc.robot.Subsystems.PrettyLights;
import frc.robot.Subsystems.SwerveDriveSystem;
import frc.robot.Subsystems.TempMotor;
import frc.robot.Subsystems.Wheel;
import frc.robot.Subsystems.Limelight;
import frc.robot.Commands.CenterToLimelight;
import frc.robot.Commands.LightsOnCommand;
import frc.robot.Commands.SwerveDriveMoveForward;

public class Robot extends TimedRobot { 

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
  IntakePaws intakePaws = new IntakePaws();
  private Limelight limelight = new Limelight();
  double autox1 = 0; //defines left and right movement for auton
  double autox2 = 0; //defines spinning movement for auton
  double autoy1 = 0; //defines forward and backward movement for auton
  boolean isBalancing = false;
  ArmPivot armPivot = new ArmPivot();

  //command related declarations
  private PrettyLights prettyLights1 = new PrettyLights();
  Command moveForward = new SwerveDriveMoveForward(driveSystem, 10);
  Command setupInitialLights = new LightsOnCommand(prettyLights1, PrettyLights.BPM_PARTYPALETTE);
  Command setupPostMoveLights = new LightsOnCommand(prettyLights1, PrettyLights.LARSONSCAN_RED);
  Command middleLights = new LightsOnCommand(prettyLights1, PrettyLights.BLUE_GREEN);
  Command floridaMansLights = new LightsOnCommand(prettyLights1, PrettyLights.C1_AND_C2_END_TO_END_BLEND);
  Command lightsAtTheEnd = new LightsOnCommand(prettyLights1, PrettyLights.HEARTBEAT_BLUE);
  private TempMotor tempMotor1 = new TempMotor();
  Command setupCenterToLimelight = new CenterToLimelight(tempMotor1);
  

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    intakePaws.setRightPawOpen();
    intakePaws.setLeftPawOpen();
    m_chooser.setDefaultOption("Leave Tarmac", kAuton1);
    m_chooser.addOption("Shoot High Leave Tarmac", kAuton2);
    m_chooser.addOption("Shoot High Intake Cargo", kAuton3);
    m_chooser.addOption("Shoot 2 High Leave Tarmac", kAuton4);
    SmartDashboard.putData(m_chooser); //displays the auton options in shuffleboard, put in init block
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
    limelight.updateDashboard(); //runs block in limeight subsystem for periodic update
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
    SmartDashboard.putBoolean("LimelightLamp", limelight.isLimelightLampOn());
    

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
  }

  // final double kP = 0.3;
  // double encoderSetpoint = 0;

  @Override
  public void autonomousPeriodic() {
    // driveSystem.moveManual(autox1, autoy1, autox2, 0, 1);

    //   driveSystem.resetRelativeEncoders();
    //   encoderSetpoint = 1;  
    // double encoderPositionFT = driveSystem.getRelativeEncoderFT();
    // double driverError = encoderSetpoint - encoderPositionFT;
    // double outputSpeed = kP * driverError;
    // driveSystem.moveManual(autox1, outputSpeed, autox2, 0);
    // SmartDashboard.putNumber("outputSpeed", outputSpeed);
    switch (m_autoSelected) {      
      case kAuton1: 
      
      CommandScheduler.getInstance().schedule(
        
        setupInitialLights,moveForward
          // .andThen(new WaitCommand(2))
          // .andThen(setupPostMoveLights)
          // .andThen(new WaitCommand(2))
          // .andThen(middleLights)
          // .andThen(new WaitCommand(2))
          // .andThen(floridaMansLights)
          // .andThen(new WaitCommand(5))
          // .andThen(lightsAtTheEnd) 
        );
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
    prettyLights1.setLEDs(PrettyLights.BPM_RAINBOWPALETTE);
  }


  /**
   * This function is called periodically during operator control.
   */


  @Override
  public void teleopPeriodic() { 
    double x1 = xboxDriver.getRawAxis(0); //connects the left and right drive movements to the drive controllers left x-axis
    double x2 = xboxDriver.getRawAxis(4); //connects the spinning drive movements to the drive controllers right x-axis
    double y1 = xboxDriver.getRawAxis(1); //connects the forwards and backwards drive movements to the drive controllers left y-axis

    if (Math.abs((xboxOperator.getRawAxis(0))) > JOYSTK_DZONE){
      armPivot.armPivotVariable(xboxOperator.getRawAxis(0)/8);
    }else {
      armPivot.setPivotStop();
    }
    SmartDashboard.getNumber("pivotpower", xboxOperator.getRawAxis(0));


    //*******Driver Controls*********

    //this tells the robot when it should be driverOriented or robotOriented
    if (driverOriented) {
      theta_radians = gyro.getYaw() * Math.PI / 180; //FieldOriented (whatever encoder 0 value is = forward)
    } else theta_radians = 0; //RobotOriented (robot front=forward)

    // Drive the robot
    Wheel.SpeedSetting driveSpeed = Wheel.SpeedSetting.NORMAL; //sets speed back to normal every 20ms
    if (xboxDriver.getRawButton(DRV_SPD_LIMITER_RB)) driveSpeed = Wheel.SpeedSetting.TURBO;
    if (xboxDriver.getRawAxis(2) > .5) driveSpeed = Wheel.SpeedSetting.PRECISE;
    driveSystem.moveManual(x1, y1, x2, theta_radians, driveSpeed);

    if (xboxDriver.getRawButton(TEST_PID_ROTATE_A) && Math.abs(gyro.getPitch()) > 2){
      isBalancing = true;
      driveSystem.moveManual(0, (gyro.getPitch()/-40), 0 , theta_radians, driveSpeed);
    } else {
      isBalancing = false;
    }    

    if (xboxDriver.getRawButton(TEST_TOGGLE_LL_CAM_MODE_Y)){
      CommandScheduler.getInstance().schedule(setupCenterToLimelight);
    }
      //stealing this button
    //   if (limelight.isLimelightLampOn()){
    //     limelight.setLimelightLampOff();
    //   } else {
    //     limelight.setLimelightLampOn();
    //   }
    // }

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

    //*******Operator Controls********

    //toggle paws open and closed
    if (xboxOperator.getRawButtonPressed(PAWS_TOGGLEBOTH_A)) {
      if (intakePaws.isRightPawOpen() && intakePaws.isLeftPawOpen()) {
        intakePaws.setRightPawClose();
        intakePaws.setLeftPawClose();
        prettyLights1.setLEDs(PrettyLights.SKY_BLUE);
      } else {
        if (intakePaws.isRightPawClose() && intakePaws.isLeftPawClose()) {
          intakePaws.setRightPawOpen();
          intakePaws.setLeftPawOpen();
          prettyLights1.setLEDs(PrettyLights.LAWN_GREEN);
        }
      }
    }

    if (xboxOperator.getRawButtonPressed(PAW_TOGGLELEFT_B)) {
intakePaws.setLeftPawToggle();
    }
    if (xboxOperator.getRawButtonPressed(PAW_TOGGLERIGHT_X)) {
intakePaws.setRightPawToggle();
    }

    // if (xboxOperator.getRawButtonPressed(SCHEDULE_INITIAL_COMMAND_LB)) {
     
      // Command moveForward = new SwerveDriveMoveForward(driveSystem, 10);
      // Command setupInitialLights = new LightsOnCommand(prettyLights1, PrettyLights.BPM_PARTYPALETTE);
      // Command setupPostMoveLights = new LightsOnCommand(prettyLights1, PrettyLights.LARSONSCAN_RED);
      // Command middleLights = new LightsOnCommand(prettyLights1, PrettyLights.BLUE_GREEN);
      // Command floridaMansLights = new LightsOnCommand(prettyLights1, PrettyLights.C1_AND_C2_END_TO_END_BLEND);
      // Command lightsAtTheEnd = new LightsOnCommand(prettyLights1, PrettyLights.HEARTBEAT_BLUE);
      
      // CommandScheduler.getInstance().schedule(
      //   setupInitialLights
      //     .andThen(new WaitCommand(2))
      //     .andThen(setupPostMoveLights)
      //     .andThen(new WaitCommand(2))
      //     .andThen(middleLights)
      //     .andThen(new WaitCommand(2))
      //     .andThen(floridaMansLights)
      //     .andThen(new WaitCommand(5))
      //     .andThen(lightsAtTheEnd) 
      //   );
    }

  //This function is called periodically during test mode.
  @Override
  public void testPeriodic() {

  }
  
}