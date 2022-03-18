package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleReverseIntake extends CommandBase {
    public ToggleReverseIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to REVERSED / OFF
    @Override
    public void initialize() {
        if (Robot.intake.getIntakeMotorState() == Intake.IntakeMotorState.OFF) {
            Robot.intake.setMotorState(Intake.IntakeMotorState.REVERSED);
            Robot.shooter.enableFeeder(-0.4);
        } else {
            Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
            Robot.shooter.disableFeeder();
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
