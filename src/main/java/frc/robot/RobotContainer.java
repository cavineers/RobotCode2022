// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.AutoShoot;
import frc.robot.commands.SwitchMode;
import frc.robot.commands.climber.ClimberDrive;
import frc.robot.commands.ToggleIntake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.ToggleIntakeSpinMotor;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final AutoShoot autoShoot = new AutoShoot();
  private final SwitchMode switchDriveMode = new SwitchMode(this);

  public DriveTrain drivetrain = new DriveTrain(this.joy);
  public Dashboard dashboard = new Dashboard(this);

  //* Driver Controller
  public Joystick joy = new Joystick(0);
  public JoystickButton a_button = new JoystickButton(joy, 1);
  public JoystickButton b_button = new JoystickButton(joy, 2);
  public JoystickButton x_button = new JoystickButton(joy, 3);
  public JoystickButton y_button = new JoystickButton(joy, 4);
  public JoystickButton l_bump = new JoystickButton(joy, 5);
  public JoystickButton r_bump = new JoystickButton(joy, 6);
  public JoystickButton left_menu = new JoystickButton(joy, 7);
  public JoystickButton right_menu = new JoystickButton(joy, 8);
  public JoystickButton left_stick = new JoystickButton(joy, 9);
  public JoystickButton right_stick = new JoystickButton(joy, 10);

  public POVButton povUp = new POVButton(joy, 0, 0);
  public POVButton povRight = new POVButton(joy, 90, 0);
  public POVButton povDown = new POVButton(joy, 180, 0);
  public POVButton povLeft = new POVButton(joy, 270, 0);

  public enum CurrentMode {
    DRIVE,
    CLIMB
  }

  public CurrentMode mode = CurrentMode.DRIVE;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    if(this.mode == CurrentMode.DRIVE) {
      configureButtonBindings();
    } else {
      configureButtonBindingsClimb();
    }
  }

  private void configureButtonBindings() {
    this.povUp.whenPressed(this.switchDriveMode);
  }

  private void configureButtonBindingsClimb() {
    this.povUp.whenPressed(this.switchDriveMode);
    new ClimberDrive(this, this.joy).schedule();
  }

  public Joystick getJoystick() {
    return this.joy;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return this.autoShoot;
  }
}
