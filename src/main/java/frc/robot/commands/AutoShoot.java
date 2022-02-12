package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.ShooterTargeting;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class AutoShoot extends CommandBase {

    private Shooter shooter;

    // Timestamp
    private double m_timestamp;

    // Finished Command
    private boolean m_finished = false;

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
        this.shooter.turnToAngle(this.shooter.setShooterAngle(ShooterTargeting.findZ()));
        this.shooter.enableShooter();
        this.shooter.enableFeeder();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - this.m_timestamp >= 15.0 || this.m_finished;
    }
}
