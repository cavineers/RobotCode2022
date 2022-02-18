package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.ShooterTargeting;
import frc.robot.Constants;
import frc.robot.Limelight;
import frc.robot.Limelight.LedMode;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase {

    private Shooter shooter;
    private PIDController pidController = new PIDController(Constants.Shooter.kP, Constants.Shooter.kI, Constants.Shooter.kD);
    private Limelight limelight;

    // Timestamp
    private double m_timestamp;

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
       

        this.pidController.setSetpoint(shooter.getCurrentSpeedSetpoint());
        this.pidController.setTolerance(1);
        this.achievedSetpoint = false;
        this.limelight.setLightMode(LedMode.ON);
        
    }

    @Override
    public void execute() {
        double pid = pidController.calculate(shooter.getShooterMotorPosition(), shooter.getCurrentSpeedSetpoint());
        Shuffleboard.getTab("Shooter").add("Shooter PID", pid);

        if (this.pidController.atSetpoint()) {
            this.achievedSetpoint = true;
            if (shooter.getCurrentState() != Shooter.ShooterStatus.ENABLED) { 
                this.shooter.turnToAngle(this.shooter.setShooterAngle(ShooterTargeting.findZ()));
                this.shooter.enableShooter();
    
                if(this.shooter.atSetpoint()) {
                    this.shooter.enableFeeder();
                }
            }

            Shuffleboard.getTab("Shooter").add("distanceD", limelight.getDistance());
            Shuffleboard.getTab("Shooter").add("check_shooter", shooter.atSetpoint());
            Shuffleboard.getTab("Shooter").add("Z", ShooterTargeting.findZ());
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.disableShooter();
        shooter.disableFeeder();
    }

    @Override
    public boolean isFinished() {
        // End if command takes longer than 15 seconds or finished firing
        return Timer.getFPGATimestamp() - this.m_timestamp >= 15.0 || this.m_finished;
    }
}
