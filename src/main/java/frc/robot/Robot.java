// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Intake;
import frc.robot.RobotContainer.CurrentMode;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.climber.ClimberDrive;
import frc.robot.commands.Autonomous;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;

public class Robot extends TimedRobot {

  // RobotContainer
  public static RobotContainer m_robotContainer;
  
  // Sensors
  public static Limelight limelight;

  // Subsystems
  public static Shooter shooter;
  public static Climber climber;
  public static Elevator elevator;
  public static Intake intake;
  public static DriveTrain drive;

  // Autonomous Commands
  public Command m_autonomousCommand;

  public Robot() {
    super(0.02); // 50Hz run rate

    limelight = new Limelight();

    // Subsystems
    shooter = new Shooter();
    climber = new Climber();
    intake = new Intake();
    elevator = new Elevator();

    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {}

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   */
  @Override
  public void robotPeriodic() {
    Shuffleboard.update();
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous commands
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

    new Autonomous(m_robotContainer).schedule();
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  public TeleopDrive drivingSystem;
  public ClimberDrive climbingSystem;

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

    this.drivingSystem = new TeleopDrive(m_robotContainer.drivetrain, m_robotContainer.joy);
    this.climbingSystem = new ClimberDrive(m_robotContainer, m_robotContainer.joy);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
      if(m_robotContainer.mode == CurrentMode.DRIVE) {
      if(this.climbingSystem.isScheduled()) {
        this.climbingSystem.cancel();
      }
      this.drivingSystem.schedule();
    } else if(m_robotContainer.mode == CurrentMode.CLIMB) {
      this.drivingSystem.cancel();
      this.climbingSystem.schedule();
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
