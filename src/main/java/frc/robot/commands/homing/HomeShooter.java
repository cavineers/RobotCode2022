package frc.robot.commands.homing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class HomeShooter extends CommandBase {
    
    boolean isHomed = false;

    public HomeShooter() {
        addRequirements(Robot.shooter);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.shooter.m_shooterAngleMotor.getEncoder().getPosition() > 0.01) {
            Robot.shooter.m_shooterAngleMotor.set(-0.1);
        } else {
            Robot.shooter.m_shooterAngleMotor.set(0.0);
            this.isHomed = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.shooter.m_shooterAngleMotor.set(0.0);
    }

    @Override
    public boolean isFinished() {
        return this.isHomed;
    }

}
