// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Forward;
import frc.robot.commands.MoveForward;
import frc.robot.commands.Turning;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.OnBoardIO;
import frc.robot.subsystems.OnBoardIO.ChannelMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.button.Button;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  //Defining the drivetrain
  public final Chassis drive = new Chassis();

  private final XboxController Driver = new XboxController(Constants.Driver);
  private final XboxController Operator = new XboxController(Constants.Operator);
  private final OnBoardIO onboardIO = new OnBoardIO(ChannelMode.INPUT, ChannelMode.INPUT);

  // Create SmartDashboard chooser for autonomous routines
  private final SendableChooser<Command> Autons = new SendableChooser<>();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }
  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Default command is arcade drive. This will run unless another command
    // is scheduled over it.
    drive.setDefaultCommand(getArcadeDriveCommand());
    
   // Example of how to use the onboard IO
    Button onboardButtonA = new Button(onboardIO::getButtonAPressed);
    onboardButtonA
        .whenActive(new PrintCommand("Button A Pressed"))
        .whenInactive(new PrintCommand("Button A Released"));

    //Applying the dashboard within code using this implementation
    Autons.addOption("MoveForward", new MoveForward(drive));
    Autons.setDefaultOption("Forward",new Forward(.5,5,drive));
    Autons.addOption("Turn", new Turning(0.50,180,drive));
   
    SmartDashboard.putData("moveForward", new MoveForward(drive));
    SmartDashboard.putData("Forward",new Forward(1,10,drive));
    SmartDashboard.putData("Turn", new Turning(0.50,180,drive));
    SmartDashboard.putData(Autons);
  }
 
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return Autons.getSelected();
  }
  public Command getArcadeDriveCommand() {
    return new ArcadeDrive(
      drive, () -> -Driver.getRawAxis(1), () -> Driver.getRawAxis(2));
  }
}
