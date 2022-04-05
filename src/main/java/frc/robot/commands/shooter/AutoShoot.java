package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.ShooterTargeting;
import frc.robot.Limelight;
import frc.robot.Robot;
import frc.robot.Limelight.LedMode;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase {

    private Shooter shooter;
    private Limelight limelight;

    // Timestamp
    private double m_timestamp;
    private double endTime;
    private double middleTime;

    // Finished Command
    private boolean m_finished = false;
    private boolean setEndTimer = false;

    public AutoShoot(Shooter shoot, Limelight limelight) {
        // Add requirements for shooter and limelight systems
        this.addRequirements(Robot.shooter);
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
        // Adjusts for X offset and turns robot automatically to align with tape
        if (this.shooter.withinXTolerance() == false && this.limelight.validTargets() == true) {
            if (ShooterTargeting.getTx() > 0) {
                Robot.m_robotContainer.drivetrain.drive(-0.1, 0, true);
            } else {
                Robot.m_robotContainer.drivetrain.drive(0.1, 0, true);
            }
        }

        // Move the angle of the Shooter
        this.shooter.turnToAngle(this.shooter.setShooterAngle(ShooterTargeting.findZ()));

        // Start spinning up shooter
        if (Timer.getFPGATimestamp() - this.endTime >= 0.1 && ShooterTargeting.findZ() > .1) {
            this.shooter.enableShooter(ShooterTargeting.findZ());
        }
    
        if(this.shooter.atAngle() == true && this.shooter.atSetpoint() == true && Timer.getFPGATimestamp() - this.middleTime >= 1.3) {
            this.shooter.enableFeeder();

            if (this.setEndTimer == false) {
                this.setEndTimer = true;
                this.endTime = Timer.getFPGATimestamp();
            }

            if (this.shooter.getSensorBallState() == false && Timer.getFPGATimestamp() - this.endTime >= 0.8) {
                this.m_finished = true;
            } else if (Timer.getFPGATimestamp() - this.endTime >= 1.3) {
                this.m_finished = true;
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.disableShooter();
        shooter.disableFeeder();
        shooter.resetPosition();
        this.limelight.setLightMode(LedMode.OFF);
    }

    @Override
    public boolean isFinished() {
        // End if command takes longer than 10 seconds or finished firing
        return Timer.getFPGATimestamp() - this.m_timestamp >= 10.0 || this.m_finished;
        // return false;
    }
}
