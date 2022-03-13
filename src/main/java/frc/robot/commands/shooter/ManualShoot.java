package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Shooter.ShooterAngle;

public class ManualShoot extends CommandBase {

    private Shooter shooter;

    private double m_timestamp;

    private boolean m_finished = false;

    public ManualShoot(Shooter shoot) {
        this.addRequirements(Robot.shooter);
        this.shooter = shoot;
    }

    @Override
    public void initialize() {
        shooter.turnToAngle(ShooterAngle.MEDIUM);

        shooter.enableManualShooter(3000); // Safe bounds 0 - 5400 RPM

        this.m_timestamp = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 3.5) {
            shooter.enableFeeder();
            this.m_finished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.disableFeeder();
        shooter.disableShooter();
    }

    @Override
    public boolean isFinished() {
        return Timer.getFPGATimestamp() - this.m_timestamp >= 7.0 || this.m_finished;
    }
}
