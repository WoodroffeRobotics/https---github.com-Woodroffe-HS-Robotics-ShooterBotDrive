// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.DrivetrainConstants;


/** Add your docs here. */
public class DrivetrainSubsystem extends SubsystemBase{
    
    private WPI_TalonSRX frontLeft = new WPI_TalonSRX(DrivetrainConstants.frontLeftTalonPort);
    private WPI_TalonSRX backLeft = new WPI_TalonSRX(DrivetrainConstants.backLeftTalonPort);
    private WPI_TalonSRX frontRight = new WPI_TalonSRX(DrivetrainConstants.frontRightTalonPort);
    private WPI_TalonSRX backRight = new WPI_TalonSRX(DrivetrainConstants.backRightTalonPort);

    public MecanumDrive mecDrive = new MecanumDrive(frontLeft, backLeft, frontRight, backRight);

    public DrivetrainSubsystem() {

        frontLeft.setNeutralMode(NeutralMode.Brake);
        backLeft.setNeutralMode(NeutralMode.Brake);
        frontRight.setNeutralMode(NeutralMode.Brake);
        backRight.setNeutralMode(NeutralMode.Brake);

        frontLeft.setInverted(true);
        backLeft.setInverted(true);

        frontLeft.setSelectedSensorPosition(0);
        backLeft.setSelectedSensorPosition(0);
        frontRight.setSelectedSensorPosition(0);
        backRight.setSelectedSensorPosition(0);

    }

    public void resetEncoders() {
        frontLeft.setSelectedSensorPosition(0);
        backLeft.setSelectedSensorPosition(0);
        frontRight.setSelectedSensorPosition(0);
        backRight.setSelectedSensorPosition(0);
    }

    public double distanceToTicks(double distanceInches) { // This function takes in a distance in inches
        double ticks = (distanceInches / (6 * Math.PI)) * 4096; // Take the input distance and divide by the circumference of the wheels (diameter 6 inches) then multiply by total ticks per rotation (4069 ticks)
        return ticks; // returns the distance in ticks
    }

    public double getDistance() { // This function returns distance in inches from ticks on the front left encoder
        double distance = (frontLeft.getSelectedSensorPosition() / 4096) * (6 * Math.PI); // Take the ticks and divide by the number of ticks in one rotation to get number of rotations, then multiply by circumference of wheels to get distance traveled
        return distance;
    }

    public void setMecanum(double x, double y, double rx) {
        mecDrive.driveCartesian(x, y, rx);

        SmartDashboard.putNumber("x", x);
        SmartDashboard.putNumber("y", y);
        SmartDashboard.putNumber("rx", rx);
    }

}
