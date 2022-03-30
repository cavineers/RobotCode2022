package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class RaiseIntake extends CommandBase {
    private boolean inPosition = false;
    
    public RaiseIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if (Robot.intake.getDropMotor().getEncoder().getPosition() <= Constants.Intake.RevolutionsToLower) {
            Robot.intake.setDropMotorState(Intake.IntakeDropMotorState.REVERSED);
        } else if (Robot.intake.getDropMotor().getEncoder().getPosition() >= -1) {
            Robot.intake.setDropMotorState(Intake.IntakeDropMotorState.OFF);
            this.inPosition = true;
        }
    }

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return this.inPosition;
    }
}
