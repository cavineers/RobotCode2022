// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

// Robot Name: Jankins
package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.Intake;
import frc.lib.ShooterTargeting;
import frc.robot.Limelight.LedMode;
import frc.robot.RobotContainer.CurrentMode;
import frc.robot.commands.TeleopDrive;
import frc.robot.commands.climber.ClimberDrive;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Shooter;

public class Robot extends TimedRobot {

  // RobotContainer
  public static RobotContainer m_robotContainer;
  
  // Sensors
  public static Limelight limelight;
  public static AHRS gyro;

  // Subsystems
  public static Shooter shooter;
  public static Climber climber;
  public static Elevator elevator;
  public static Intake intake;
  public static DriveTrain drive;

  // Autonomous Commands
  public Command m_autonomousCommand;

  public Robot() {
    super(0.02); // 0.02 - 50Hz run rate | 0.0167 - 60Hz run rate | 0.01429 - 100Hz run rate
    limelight = new Limelight();

    // Subsystems
    shooter = new Shooter();
    climber = new Climber();
    intake = new Intake();
    elevator = new Elevator();

    m_robotContainer = new RobotContainer();

    gyro = new AHRS(Port.kMXP);
  }

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    limelight.setLightMode(LedMode.OFF);
    // intake.m_intakeDropMotor.getEncoder().setPosition(0.0);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   */
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    // Logs
    // Shooter
    SmartDashboard.putNumber("Shooter SetPoint", shooter.getCurrentSpeedSetpoint());
    SmartDashboard.putNumber("Shooter Actual", shooter.m_shootEncoder.getVelocity());
    SmartDashboard.putNumber("TX Offset", ShooterTargeting.getTx());
    SmartDashboard.putBoolean("Within X Offset", shooter.withinXTolerance());
    SmartDashboard.putString("Angle Setpoint", shooter.setShooterAngle(ShooterTargeting.findZ()).toString());
    SmartDashboard.putNumber("Angle Actual", shooter.getCurrentAngleMotorPosition());
    SmartDashboard.putBoolean("Shooter Ready", (shooter.atAngle() == true && shooter.atSetpoint() == true));

    // Targeting
    SmartDashboard.putNumber("TD", ShooterTargeting.getTD());
    SmartDashboard.putNumber("Z", ShooterTargeting.findZ());

    // Sensors
    SmartDashboard.putBoolean("Intake Sensor", intake.getSensorOneState());
    SmartDashboard.putBoolean("Shooter Sensor", shooter.getSensorBallState());
    SmartDashboard.putString("Drive Mode", m_robotContainer.mode.toString());

    // Intake
    SmartDashboard.putNumber("Intake Position", intake.getDropMotor().getEncoder().getPosition());

    // Climber
    SmartDashboard.putNumber("Right Elevator Position", -elevator.getElevatorRightMotor().getEncoder().getPosition());
    SmartDashboard.putNumber("Left Elevator Position", -elevator.getElevatorLeftMotor().getEncoder().getPosition());
    SmartDashboard.putNumber("Right Angle Position", climber.getAngleMotorPositionRight());
    SmartDashboard.putBoolean("Right Switch", climber.getRightAngleSwitch());
    SmartDashboard.putNumber("Left Angle Position", climber.getAngleMotorPositionLeft());
    SmartDashboard.putBoolean("Left Switch", climber.getLeftAngleSwitch());
    
    // Drive
    SmartDashboard.putNumber("Drive Speed Right", m_robotContainer.drivetrain.getActiveRightSpeed());
    SmartDashboard.putNumber("Drive Speed Left", m_robotContainer.drivetrain.getActiveLeftSpeed());
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
    m_autonomousCommand.schedule();
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
    if (m_robotContainer.mode == CurrentMode.DRIVE) {
      if(this.climbingSystem.isScheduled()) {
        this.climbingSystem.cancel();
      }
      this.drivingSystem.schedule();
    } else if (m_robotContainer.mode == CurrentMode.CLIMB) {
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
