package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.homing.HomeShooter;
import frc.robot.commands.shooter.AutoShoot;

public class Autonomous1 extends CommandBase {
    private RobotContainer rc;

    private double startTime = 0;

    private double kP = 0.0005;
    private double heading;
    private boolean scheduledInitalShoot = false;
    public Autonomous1(RobotContainer rc) {
        this.rc = rc;
    }

    @Override
    public void initialize() {
        this.startTime = Timer.getFPGATimestamp();
        this.heading = Robot.gyro.getAngle();
    }

    @Override
    public void execute() {
        // Get GyroSphere heading to keep at 0 and account for errors in drive chain
        double error = this.heading - Robot.gyro.getAngle();

        if (Timer.getFPGATimestamp() - this.startTime >= 4.3) {
            if (this.scheduledInitalShoot == false) {
                this.scheduledInitalShoot = true;
                this.rc.drivetrain.drive(0, 0, true);
                this.rc.m_autoShoot = new AutoShoot(Robot.shooter, Robot.limelight).andThen(new HomeShooter());
                this.rc.m_autoShoot.schedule();
            }
        } else {
            this.rc.drivetrain.drive(0.0, 0.14 + kP * error, true);
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
