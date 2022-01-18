package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoShoot extends CommandBase {

    // Timestamp
    private double m_timestamp;

    // Finished Command
    private boolean m_finished = false;

    public AutoShoot() {
        // TODO: Add requirements.
    }

    @Override
    public void initialize() {
        // Set start timestamp
        this.m_timestamp = Timer.getFPGATimestamp();

        // Set finished to false
        this.m_finished = false;
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
