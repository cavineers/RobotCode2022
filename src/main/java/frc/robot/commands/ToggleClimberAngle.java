package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

public class ToggleClimberAngle extends CommandBase {
    
    public ToggleClimberAngle() {
        this.addRequirements(Robot.climber);
    }

    @Override
    public void initialize() {
        if (Robot.climber.getAngleMotorState() == Climber.ClimberMotorState.OFF) {
            Robot.climber.setAngleMotorState(Climber.ClimberMotorState.ON);
        } else {
            Robot.climber.setAngleMotorState(Climber.ClimberMotorState.OFF);
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
