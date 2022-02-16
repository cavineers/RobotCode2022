   package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.commands.ToggleIntakeTopMotor;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;



public class ToggleIntakeTopMotor extends CommandBase {
   
public ToggleIntakeTopMotor() {
    this.addRequirements(Robot.intake); 
}
public void initialize() {
    if (Robot.intake.getIntakeTopMotorState() == Intake.IntakeTopMotorState.OFF){
        Robot.intake.setTopMotorState(Intake.IntakeTopMotorState.ON);
    } else {
        Robot.intake.setTopMotorState(Intake.IntakeTopMotorState.OFF);
    }
}

@Override
public boolean isFinished() {
    return true;
}
}