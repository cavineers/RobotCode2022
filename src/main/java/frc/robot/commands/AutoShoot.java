package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.ShooterTargeting;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase {

    private Shooter shooter;
    private PIDController pidController = new PIDController(Constants.Shooter.kP, Constants.Shooter.kI, Constants.Shooter.kD);

    // Timestamp
    private double m_timestamp;

    // Finished Command
    private boolean m_finished = false;

    private boolean achievedSetpoint = false;

    public AutoShoot(Shooter shoot) {
        this.addRequirements(Robot.shooter);
        this.shooter = shoot;
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
    }

    @Override
    public void execute() {
        double pid = pidController.calculate(shooter.getShooterMotorPosition(),shooter.getCurrentSpeedSetpoint());
        SmartDashboard.putNumber("Shooter PID", pid);

        if (this.pidController.atSetpoint()) {
            this.achievedSetpoint = true;
            if (shooter.getCurrentState() != Shooter.ShooterStatus.ENABLED) { 
                this.shooter.turnToAngle(this.shooter.setShooterAngle(ShooterTargeting.findZ()));
                this.shooter.enableShooter();
    
                if(this.shooter.atSetpoint()) {
                    this.shooter.enableFeeder();
                }
        }

        SmartDashboard.putBoolean("check_shooter", shooter.atSetpoint());

       
        }

    }

    @Override
    public void end(boolean interrupted) {
        shooter.disableShooter();
        shooter.disableFeeder();
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - this.m_timestamp >= 15.0 || this.m_finished;
    }
}
