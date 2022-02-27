package frc.robot.commands;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.ShooterTargeting;
import frc.robot.Constants;
import frc.robot.Limelight;
import frc.robot.Limelight.LedMode;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase {

    private Shooter shooter;
    private Limelight limelight;

    // Timestamp
    private double m_timestamp;

    private double endTime;
    private double middleTime;

    double cycleTime = Timer.getFPGATimestamp() - m_timestamp;

    // Finished Command
    private boolean m_finished = false;
    private boolean setEndTimer = false;

    public AutoShoot(Shooter shoot, Limelight limelight) {
        // Add requirements for shooter and limelight systems
        this.addRequirements(shoot);
        this.shooter = shoot;
        this.limelight = limelight;
    }

    @Override
    public void initialize() {
        // Set start timestamp
        this.m_timestamp = Timer.getFPGATimestamp();
        this.endTime = Timer.getFPGATimestamp();
        this.middleTime = Timer.getFPGATimestamp();
        
        // Set finished to false
        this.m_finished = false;

        this.limelight.setLightMode(LedMode.ON);
    }

    @Override
    public void execute() {
        SmartDashboard.putNumber("Motor Position Angle", this.shooter.getCurrentAngleMotorPosition());
        SmartDashboard.putNumber("TD Value", ShooterTargeting.getTD());
        SmartDashboard.putNumber("Z Value", ShooterTargeting.findZ());
        SmartDashboard.putNumber("ActiveSpeed", this.shooter.m_shooterMotor.getEncoder().getVelocity());
        SmartDashboard.putBoolean("IR SENSOR", this.shooter.getSensorBallState());
        SmartDashboard.putBoolean("Shooter Ready", (this.shooter.atAngle() == true && this.shooter.atSetpoint() == true));
        

        // Move the angle of the Shooter
        this.shooter.turnToAngle(this.shooter.setShooterAngle(ShooterTargeting.findZ()));

        // Start spinning up shooter
        if (Timer.getFPGATimestamp() - this.endTime >= 0.5 && ShooterTargeting.findZ() > .01) {
            this.shooter.enableShooter(ShooterTargeting.findZ());
        }

        if (this.shooter.atSetpoint() == true) {
            SmartDashboard.putNumber("Cycle Time", cycleTime);
        }
    
        if(this.shooter.atAngle() == true && this.shooter.atSetpoint() == true && Timer.getFPGATimestamp() - this.middleTime >= 3.0) {
            this.shooter.enableFeeder();

            if (this.setEndTimer == false) {
                this.setEndTimer = true;
                this.endTime = Timer.getFPGATimestamp();
            }

            if (this.shooter.getSensorBallState() == false && Timer.getFPGATimestamp() - this.endTime >= 2.0) {
                this.m_finished = true;
            }
        }
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
        return Timer.getFPGATimestamp() - this.m_timestamp >= 15.0 || this.m_finished;
        //return false;
    }
}
