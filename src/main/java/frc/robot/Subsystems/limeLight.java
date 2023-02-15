
package frc.robot.Subsystems;  

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController.Button;

public class limeLight {

	boolean lampOn = true;

    NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");	//creates the limelight table
	NetworkTableEntry x = limelightTable.getEntry("tx"); //the x "tx" offset from the limelight
	NetworkTableEntry camMode = limelightTable.getEntry("camMode");
	NetworkTableEntry y = limelightTable.getEntry("ty"); //the y "ty" offset from the limelight
	NetworkTableEntry ledMode = limelightTable.getEntry("ledMode");
	NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
	
	
	//creates getTargetX for getting and using the x "tx" offset from the limelight
	public double getTargetX() { 
		return x.getDouble(0);
	}

	//creates getTargetX for getting and using the x "tx" offset from the limelight
	public double getTargetY() { 
		return y.getDouble(0);
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
	

	SmartDashboard.putNumber("LimelightX", x);
	SmartDashboard.putNumber("LimelightY", y);
	SmartDashboard.putNumber("LimelightArea", area);
};