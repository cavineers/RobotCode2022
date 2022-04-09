package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.homing.HomeShooter;
import frc.robot.commands.shooter.AutoShoot;

public class Autonomous extends CommandBase {
    private RobotContainer rc;

    private double startTime = 0;
    private double middleTime = 0;

    private double kP = 0.0005;
    private double heading;
    private boolean scheduledInitalShoot = false;
    private boolean scheduledSecondShoot = false;
    private boolean scheduledIntake = false;
    private boolean scheduledIntakeDrop = false;

    public Autonomous(RobotContainer rc) {
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

        if (Timer.getFPGATimestamp() - this.startTime >= 3) {
            if (this.scheduledInitalShoot == false) {
                this.scheduledInitalShoot = true;
                this.rc.drivetrain.drive(0, 0, true);
                this.rc.m_autoShoot = new AutoShoot(Robot.shooter, Robot.limelight).andThen(new HomeShooter());
                this.rc.m_autoShoot.schedule();
            } else if (this.rc.m_autoShoot.isScheduled() == false) {
                if (this.scheduledIntakeDrop == false) {
                    this.scheduledIntakeDrop = true;
                    this.rc.m_intakeDropLower = new LowerIntake();
                    this.rc.m_intakeDropLower.schedule();
                } else if (this.rc.m_intakeDropLower.isScheduled() == false) {
                    if (this.scheduledIntake == false) {
                        this.scheduledIntake = true;
                        this.middleTime = Timer.getFPGATimestamp();
                        this.rc.m_intake = new ToggleIntake();
                        this.rc.m_intake.schedule();
                    } else if (Robot.shooter.getSensorBallState() == true) {
                        this.rc.drivetrain.drive(0, -0.09, true);
                        if (this.scheduledSecondShoot == false) {
                            this.scheduledSecondShoot = true;
                            this.rc.m_intakeDropRaise = new RaiseIntake();
                            this.rc.m_intakeDropRaise.schedule();
                            this.rc.m_autoShoot = new AutoShoot(Robot.shooter, Robot.limelight).andThen(new HomeShooter());
                            this.rc.m_autoShoot.schedule();
                        }
                    } else {
                        if (Timer.getFPGATimestamp() - this.middleTime >= 5) {
                            this.rc.drivetrain.drive(0, 0.0, true);
                        } else {
                            this.rc.drivetrain.drive(0, 0.1, true);
                        }
                    }
                }
            }
        } else {
            this.rc.drivetrain.drive(0.0, 0.14 + kP * error, true);
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (this.rc.m_intake.isScheduled() == true) {
            this.rc.m_intake.cancel();
            this.rc.m_intakeDropRaise = new RaiseIntake();
            this.rc.m_intakeDropRaise.schedule();
        }
        this.rc.drivetrain.drive(0, 0, true);
        // this.rc.m_autoShoot.cancel();
    }

    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp()-this.startTime > 15);
    }
}
