package frc.robot.commands.climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

/**
 * Toggle the elevator into reverse mode.
 */
public class ToggleElevatorReverse extends CommandBase {
    
    // Constructor
    public ToggleElevatorReverse() {
        this.addRequirements(Robot.climber);
    }

    // Set Motor State to OFF / REVERSED
    @Override
    public void initialize() {
        if (Robot.climber.getElevState() == Climber.ClimberMotorState.OFF) {
            Robot.climber.setElevMotorState(Climber.ClimberMotorState.REVERSED);
        } else {
            Robot.climber.setElevMotorState(Climber.ClimberMotorState.OFF);
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