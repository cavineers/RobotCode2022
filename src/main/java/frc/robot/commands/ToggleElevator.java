package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;

/**
 * Toggle the intake.
 */
public class ToggleElevator extends CommandBase {

    /**
     * Toggle Intake.
     */
    public ToggleElevator() {
        this.addRequirements(Robot.climber);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        if (Robot.climber.getElevState() == Climber.ClimberMotorState.OFF) {
            Robot.climber.setElevMotorState(Climber.ClimberMotorState.ON);
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