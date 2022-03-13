// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.LowerIntake;
import frc.robot.commands.ManualShoot;
import frc.robot.commands.RaiseIntake;
import frc.robot.commands.ReverseFeeder;
import frc.robot.commands.SwitchMode;
import frc.robot.commands.ToggleIntake;
import frc.robot.commands.ToggleReverseIntake;
import frc.robot.commands.homing.HomeAngle;
import frc.robot.commands.homing.HomeElevator;
import frc.robot.commands.homing.HomeShooter;
import frc.robot.commands.shooter.AutoShoot;
import frc.robot.commands.shooter.AutoShootNoAdjust;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.DriveTrain;
import frc.robot.commands.Autonomous;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  public DriveTrain drivetrain = new DriveTrain(this.joy);
  public Dashboard dashboard = new Dashboard(this);

  public Command m_autoCommand;
  // public SequentialCommandGroup m_autoShoot;
  public Command m_autoShoot;
  public Command m_autoShootNoDrive;
  public Command m_manualShoot;
  public Command m_intakeDropLower;
  public Command m_intakeDropRaise;
  public Command m_intake;

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

    this.m_autoCommand = new Autonomous(this);
    // this.m_autoShoot = new SequentialCommandGroup(new AutoShoot(Robot.shooter, Robot.limelight), new HomeShooter());
    this.m_autoShoot = new AutoShoot(Robot.shooter, Robot.limelight).andThen(new HomeShooter());
    this.m_autoShootNoDrive = new AutoShootNoAdjust(Robot.shooter, Robot.limelight).andThen(new HomeShooter());
    this.m_manualShoot = new ManualShoot(Robot.shooter).andThen(new HomeShooter());

    this.m_intakeDropLower = new LowerIntake();
    this.m_intakeDropRaise = new RaiseIntake();
    this.m_intake = new ToggleIntake();
  }

  private void configureButtonBindings() {
    this.left_menu.whenPressed(new ParallelCommandGroup(new HomeAngle(), new HomeElevator()));
    this.povUp.whenPressed(new SwitchMode(this));
    this.b_button.whenPressed(new ToggleIntake());

    this.r_bump.whenPressed(new InstantCommand() {
      @Override
      public void initialize() {
        if (m_intakeDropRaise.isScheduled()) {
          m_intakeDropRaise.cancel();
        }
        m_intakeDropLower = new LowerIntake();
        m_intakeDropLower.schedule();
      }
    });

    this.r_bump.whenReleased(new InstantCommand() {
      @Override
      public void initialize() {
        if (m_intakeDropLower.isScheduled()) {
          m_intakeDropLower.cancel();
        }
        m_intakeDropRaise = new RaiseIntake();
        m_intakeDropRaise.schedule();
      }
    });

    this.right_menu.whenPressed(new ToggleReverseIntake());
    this.povLeft.whenPressed(new ReverseFeeder());

    //Shoot
    this.a_button.whenPressed(new InstantCommand() {
      @Override
      public void initialize() {
        // Test this!
        // m_autoShoot = new AutoShoot(Robot.shooter, Robot.limelight).andThen(new HomeShooter());
        if (m_autoShoot.isScheduled()) {
          m_autoShoot.cancel();
        } else {
          m_autoShoot = new AutoShoot(Robot.shooter, Robot.limelight).andThen(new HomeShooter());
          m_autoShoot.schedule(false);
        }
      }
    });

    this.x_button.whenPressed(new InstantCommand() {
      @Override
      public void initialize() {
        if (m_autoShootNoDrive.isScheduled()) {
          m_autoShootNoDrive.cancel();
        } else {
          m_autoShootNoDrive = new AutoShootNoAdjust(Robot.shooter, Robot.limelight).andThen(new HomeShooter());
          m_autoShootNoDrive.schedule(false);
        }
      }
    });

    this.y_button.whenPressed(new InstantCommand() {
      @Override
      public void initialize() {
        if (m_manualShoot.isScheduled()) {
          m_manualShoot.cancel();
        } else {
          m_manualShoot = new ManualShoot(Robot.shooter).andThen(new HomeShooter());
          m_manualShoot.schedule(false);
        }
      }
    });
  }

  private void configureButtonBindingsClimb() {
    this.povUp.whenPressed(new SwitchMode(this));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return this.m_autoCommand;
  }
}
