package frc.robot.commands;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.ShooterTargeting;
import frc.robot.Constants;
import frc.robot.Limelight;
import frc.robot.Robot;
import frc.robot.Limelight.LedMode;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase {

    private Shooter shooter;
    // private PIDController pidController = new PIDController(Constants.Shooter.kP, Constants.Shooter.kI, Constants.Shooter.kD);
    private Limelight limelight;

    // Timestamp
    private double m_timestamp;

    double pid;

    // Finished Command
    private boolean m_finished = false;

    private boolean achievedSetpoint = false;

    public AutoShoot(Shooter shoot, Limelight limelight) {
        this.addRequirements(shoot);
        this.shooter = shoot;
        this.limelight = limelight;
    }

    @Override
    public void initialize() {
        // Set start timestamp
        this.m_timestamp = Timer.getFPGATimestamp();
        // Set finished to false
        this.m_finished = false;
       
        this.achievedSetpoint = false;
        this.limelight.setLightMode(LedMode.ON);
    }

    @Override
    public void execute() {
        // this.pid = pidController.calculate(shooter.getShooterMotorPosition(), shooter.getCurrentSpeedSetpoint());
        // this.shooter.m_shooterMotor.set(pidController.calculate(shooter.getShooterMotorPosition(), shooter.getCurrentSpeedSetpoint())); //this.shooter.getCurrentSpeedSetpoint()/5900

        SmartDashboard.putNumber("Motor Position Angle", this.shooter.getCurrentAngleMotorPosition());
        SmartDashboard.putNumber("TD Value", ShooterTargeting.getTD());
        SmartDashboard.putNumber("Z Value", ShooterTargeting.findZ());
        // SmartDashboard.putNumber("ShooterSetpoint", pidController.getSetpoint());
        SmartDashboard.putNumber("ActiveSpeed", this.shooter.m_shooterMotor.getEncoder().getVelocity());
        this.shooter.turnToAngle(this.shooter.setShooterAngle(ShooterTargeting.findZ()));
        this.shooter.enableShooter(ShooterTargeting.findZ());
        // if(this.pidController.atSetpoint()) {
        //     this.achievedSetpoint = true;
        //     this.shooter.enableFeeder();
        // }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.disableShooter();
        shooter.disableFeeder();
        this.limelight.setLightMode(LedMode.OFF);
    }

    @Override
    public boolean isFinished() {
        // End if command takes longer than 15 seconds or finished firing
        // return Timer.getFPGATimestamp() - this.m_timestamp >= 15.0 || this.m_finished;
        return false;
    }
}
