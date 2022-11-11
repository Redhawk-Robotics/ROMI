// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Sensor.RomiGyro;

public class Chassis extends SubsystemBase {

  private final Spark leftMotor, rightMotor;
  private static Encoder leftEncoder, rightEncoder;
  private static DifferentialDrive differentialDrive;

   // Set up the RomiGyro
   private final RomiGyro gyro = new RomiGyro();

   // Set up the BuiltInAccelerometer
   private final BuiltInAccelerometer accelerometer = new BuiltInAccelerometer();
   

  public Chassis() {
    //Init Motor
    leftMotor = new Spark(Constants.LEFT_MOTOR_PORT);
    rightMotor = new Spark(Constants.RIGHT_MOTOR_PORT);

    //Init the differentialDrive
    differentialDrive = new DifferentialDrive(leftMotor, rightMotor);

    //Invert the motor to go forward with the left motor
    rightMotor.setInverted(true);

    //The ROMI might have loop errors so, we tell the robot to turn off the safety warnings
    differentialDrive.setSafetyEnabled(false);

    //Init the encoders
    leftEncoder = new Encoder(Constants.LEFT_ENCODER_A, Constants.LEFT_ENCODER_B);
    rightEncoder = new Encoder(Constants.RIGHT_ENCODER_A, Constants.RIGHT_ENCODER_B);

    //The function of setDistancePerPulse to see how much the chasis has move
    leftEncoder.setDistancePerPulse(Constants.INCHES_PER_PULSE);
    rightEncoder.setDistancePerPulse(Constants.INCHES_PER_PULSE);
    reset();
    //Creates sliders within the Simulator to move ROMI
    addChild("leftEncoder", leftEncoder);
    addChild("rightEncoder", rightEncoder);

  }
//constructor to drive the robot 
public void drive(double speed, double direction) {
  //creating the differentialDriveTrain with speed and direction
  differentialDrive.arcadeDrive(speed, direction);
}

//constructor to reset the chasis
public void reset() {
  //reset the encoders
  leftEncoder.reset();
  rightEncoder.reset();
}

public int getLeftEncoderCount() {
  return leftEncoder.get();
}

public int getRightEncoderCount() {
  return rightEncoder.get();
}
//Constructor to turn off the motors
public void stop() {
  //to stop the motors
  drive(0, 0);
}
//Constructor to recieve data for distance of the leftEncoder/RightEncoder/Average
public double getLeftDistance() {
  return leftEncoder.getDistance();
}
public double getRightDistance() {
  return rightEncoder.getDistance();
}

public double getAverageDistanceInch() {
  return (getLeftDistance() + getRightDistance()) / 2.0;
}
/**
   * The acceleration in the X-axis.
   *
   * @return The acceleration of the Romi along the X-axis in Gs
   */
  public double getAccelX() {
    return accelerometer.getX();
  }

  /**
   * The acceleration in the Y-axis.
   *
   * @return The acceleration of the Romi along the Y-axis in Gs
   */
  public double getAccelY() {
    return accelerometer.getY();
  }

  /**
   * The acceleration in the Z-axis.
   *
   * @return The acceleration of the Romi along the Z-axis in Gs
   */
  public double getAccelZ() {
    return accelerometer.getZ();
  }

  /**
   * Current angle of the Romi around the X-axis.
   *
   * @return The current angle of the Romi in degrees
   */
  public double getGyroAngleX() {
    return gyro.getAngleX();
  }

  /**
   * Current angle of the Romi around the Y-axis.
   *
   * @return The current angle of the Romi in degrees
   */
  public double getGyroAngleY() {
    return gyro.getAngleY();
  }

  /**
   * Current angle of the Romi around the Z-axis.
   *
   * @return The current angle of the Romi in degrees
   */
  public double getGyroAngleZ() {
    return gyro.getAngleZ();
  }

  /** Reset the gyro. */
  public void resetGyro() {
    gyro.reset();
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(Double double1, Double double2) {
  }
}
