package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends CommandBase {
    public ToggleIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        if (Robot.intake.getIntakeMotorState() == Intake.IntakeMotorState.OFF) {
            Robot.intake.setMotorState(Intake.IntakeMotorState.ON);
        } else {
            Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
        }
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        if (Robot.intake.getSensorOneState() == true) {
            return true;
        } else {
            return false;
        }
    }
}
