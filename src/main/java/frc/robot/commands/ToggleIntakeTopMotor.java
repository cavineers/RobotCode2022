   package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.commands.ToggleIntakeTopMotor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;



public class ToggleIntakeTopMotor extends CommandBase {
   
public ToggleIntakeTopMotor() {
    this.addRequirements(Robot.intake); 
}

@Override
public void initialize() {
        if (Robot.intake.getBottomMotorState() == Intake.IntakeBottomMotorState.OFF) {
        Robot.intake.setTopMotorState(Intake.IntakeTopMotorState.ON);
    } else {
        Robot.intake.setTopMotorState(Intake.IntakeTopMotorState.OFF);
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