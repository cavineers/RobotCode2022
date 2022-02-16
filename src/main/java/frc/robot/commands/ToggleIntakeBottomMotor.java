   package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.commands.ToggleIntakeBottomMotor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;



public class ToggleIntakeBottomMotor extends CommandBase {

    public ToggleIntakeBottomMotor() {
        this.addRequirements(Robot.intake);
    }
   
    public void initialize() {
        if (Robot.intake.getBottomMotorState() == Intake.IntakeBottomMotorState.OFF) {
            Robot.intake.setMotorState(Intake.IntakeBottomMotorState.ON);
        } else {
            Robot.intake.setMotorState(Intake.IntakeBottomMotorState.OFF);
        }
    }

    @Override
    public boolean isFinished() {
        if (Robot.intake.getSensorState() == true);
        return true;
    }
}