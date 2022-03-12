package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Autonomous extends CommandBase {
    private RobotContainer rc;

    private double startTime = 0;
    private double kP = 1;
    private boolean scheduled = false;

    public Autonomous(RobotContainer rc) {
        this.rc = rc;
    }

    @Override
    public void initialize() {
        this.startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() {
        // Get GryoSphere heading to keep at 0 and account for errors in drive chain
        double error = -Robot.gyro.getRate();

        if (Timer.getFPGATimestamp() - this.startTime >= 3) {
            this.rc.drivetrain.drive(0, 0, true);
            if (this.scheduled == false) {
                this.scheduled = true;
                this.rc.m_autoShoot.schedule();
            }
        } else {
            this.rc.drivetrain.drive(0, 0.1 + kP * error, true);
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.rc.drivetrain.drive(0, 0, true);
        // this.rc.m_autoShoot.cancel();
    }

    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp()-this.startTime > 15);
    }
}
