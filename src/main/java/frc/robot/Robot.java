// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Intake;
import frc.robot.Limelight.LedMode;
import frc.robot.RobotContainer.CurrentMode;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.climber.ClimberDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  // Limelight targeting sensor
  public static Limelight limelight;
  // Subsystems
  public static Shooter shooter;
  public static Climber climber;
  public static Intake intake;
  public static DriveTrain drive;

  public static Object logger;

  public Robot() {
    super(0.02); // 50Hz run rate

    limelight = new Limelight();
    shooter = new Shooter();
    intake = new Intake();
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    limelight.setLightMode(LedMode.OFF);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    limelight.setLightMode(LedMode.OFF);
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = this.m_robotContainer.getAutonomousCommand();

    // schedule the autonomous commands
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
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

    this.drivingSystem = new TeleopDrive(this.m_robotContainer.drivetrain, this.m_robotContainer.joy);
    this.climbingSystem = new ClimberDrive(this.m_robotContainer, this.m_robotContainer.joy);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
      if(this.m_robotContainer.mode == CurrentMode.DRIVE) {
      if(this.climbingSystem.isScheduled()) {
        this.climbingSystem.cancel();
      }
      this.drivingSystem.schedule();
    } else if(this.m_robotContainer.mode == CurrentMode.CLIMB) {
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
