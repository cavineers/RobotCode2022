package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter.FeederStatus;

public class ReverseFeeder extends CommandBase {

    public ReverseFeeder() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to REVERSED / OFF
    @Override
    public void initialize() {
        if (Robot.shooter.getCurrentAngleMotorPosition() > -2 && Robot.shooter.getCurrentAngleMotorPosition() < 2) {
            if (Robot.shooter.feederState == FeederStatus.ENABLED || Robot.shooter.feederState == FeederStatus.REVERSED) {
                Robot.shooter.disableFeeder();
            } else {
                Robot.shooter.reverseFeeder();
            }
        }
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
