package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class LowerIntake extends CommandBase {
    private boolean inPosition = false;
    
    public LowerIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        Robot.intake.setDropMotorState(Intake.IntakeMotorState.OFF);
    }

    @Override
    public void execute() {
        if (Robot.intake.getDropMotor().getEncoder().getPosition() > Constants.Intake.RevolutionsToLower) {
            Robot.intake.setDropMotorState(Intake.IntakeMotorState.ON);
        } else if (Robot.intake.getDropMotor().getEncoder().getPosition() < Constants.Intake.RevolutionsToLower) {
            this.inPosition = true;
            Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        return this.inPosition;
    }
}
