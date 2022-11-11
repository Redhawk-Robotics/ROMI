// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  //Motor Constants
  public static final int LEFT_MOTOR_PORT = 0;
  public static final int RIGHT_MOTOR_PORT = 1;

  //Encoder Constants
  public static final int LEFT_ENCODER_A = 4;
  public static final int LEFT_ENCODER_B = 5;

  public static final int RIGHT_ENCODER_A = 6;
  public static final int RIGHT_ENCODER_B = 7;

  //Converting the encoder value to real world units (inches)
  public static final double WHEEL_DIAMETER = 2.75591;

  //The amount of encoder units in one full wheel rotation of the wheel
  public static final double PULSES_PER_REVOLUTION = 1440.0;

  //The amount of inches per pulse/ to see how much the encoder needs to spun  
  public static final double INCHES_PER_PULSE = Math.PI * WHEEL_DIAMETER / PULSES_PER_REVOLUTION;

  //The width of the wheel base
  public static final double WHEEL_TRACK = 5.25;

  //The amount of inches the wheel needs to travel for the amount of degrees the robot needs to turn
  public static final double INCHES_PER_DEGREE = Math.PI * WHEEL_DIAMETER / 360.0;

  //adding on the code
  public static final int Driver = 0;
  public static final int Operator = 1;
  //
}