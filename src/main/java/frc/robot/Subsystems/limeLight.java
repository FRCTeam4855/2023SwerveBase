
package frc.robot.Subsystems;  

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTableValue;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class limeLight extends TimedRobot{
	
	

	boolean lampOn = true;

    NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");	//creates the limelight table
	NetworkTableEntry LimeLight_x = limelightTable.getEntry("tx"); //the x "tx" offset from the limelight
	NetworkTableEntry camMode = limelightTable.getEntry("camMode");
	NetworkTableEntry LimeLight_y = limelightTable.getEntry("ty"); //the y "ty" offset from the limelight
	NetworkTableEntry ledMode = limelightTable.getEntry("ledMode");
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	NetworkTableValue test1 = limelightTable.getValue("ty");
	
	
	//creates getTargetX for getting and using the x "tx" offset from the limelight
	public double getTargetX() { 
		// return LimeLight_x.getDouble(0);
		return test1.getDouble();
	}


	//creates getTargetX for getting and using the x "tx" offset from the limelight
	public double getTargetY() { 
		return LimeLight_y.getDouble(0);
	}
	// TODO : add button options for lamps
	public void turnOnLamp() {
		camMode.setNumber(1);
		ledMode.setNumber(3);
		lampOn = true;
	}
	public void turnOffLamp() {
		ledMode.setNumber(0);
		camMode.setNumber(1);
		lampOn = false;
	}
	// public double LimeLight_Test = 5;
	// public void output (String double NetworkTableEntry) {

	@Override
	public void robotPeriodic() {
	SmartDashboard.putNumber("LimelightX", getTargetX());
	SmartDashboard.putNumber("LimelightY", getTargetY());
	}
		// }

}