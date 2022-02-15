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
        if (this.getRawButtonPressed(1)) {
        Robot.intake.setTopMotorState(Intake.IntakeTopMotorState.ON);
    }
}

@Override
public boolean isFinished() {
    if (this.getRawButtonPressed(2)){
        Robot.intake.setTopMotorState(Intake.IntakeTopMotorState.OFF);
    }
}
}