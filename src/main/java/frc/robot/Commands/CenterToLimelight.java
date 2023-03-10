// package frc.robot.Commands;

// import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Subsystems.Limelight;
// import frc.robot.Subsystems.TempMotor;

// public class CenterToLimelight extends CommandBase {

//     private TempMotor tempMotor;
//     double Kp = -0.05;
//     double min_command = 0.05;
//     double heading_setpoint = 3;
//     double initTime;
//     double heading_error;

    // public CenterToLimelight(TempMotor motorToUse) {
    //     super();
    //     tempMotor = motorToUse;
    //     addRequirements(motorToUse);
    // }

//     @Override
//     public void initialize() {
//         Limelight.setLimelightLampOn();
//         Limelight.setLimelightPipeToAprilTag();
//         NetworkTableInstance.getDefault().getTable("limelight-rams").getEntry("ledMode").setNumber(3);
//     }

//     @Override
//     public void execute() {
//         double tx = NetworkTableInstance.getDefault().getTable("limelight-rams").getEntry("tx").getDouble(0);
//         heading_error = -tx;
//         if (Math.abs(heading_error) > heading_setpoint) {
//             if (heading_error < 0) {
//                 tempMotor.setTempMotorSpeed(Kp * heading_error + min_command);
//             } else {
//                 tempMotor.setTempMotorSpeed(Kp * heading_error - min_command);
//             }
//         }
//         SmartDashboard.putNumber("CenterToLimelight Output Speed", (tempMotor.getTempMotorSpeed()));
//     }

//     @Override
//     public boolean isFinished() { // specifies end conditions
//         if (Math.abs(heading_error) < heading_setpoint) {
//             tempMotor.setTempMotorSpeed(0);
//             return true;
//         }
//         return false;
//     }
// }