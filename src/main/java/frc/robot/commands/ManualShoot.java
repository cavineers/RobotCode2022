package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Shooter.ShooterStatus;

public class ManualShoot extends CommandBase{
    private Shooter shooter;

    public ManualShoot(Shooter shoot){
        this.addRequirements(shoot);
        this.shooter = shoot;
    }

    @Override
    public void initialize() {
        if (Robot.shooter.getCurrentState() == ShooterStatus.DISABLED) {
            Robot.shooter.enableShooter();
            Robot.shooter.setSpeed(5000); // Change to update speed. (200 - 5500 safe bounds)
        } else if (Robot.shooter.getCurrentState() == ShooterStatus.ENABLED) {
            Robot.shooter.disableShooter();
        } 

    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
