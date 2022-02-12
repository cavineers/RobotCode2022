   package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.commands.ToggleIntakespinMotor;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;     
import edu.wpi.first.wpilibj.DigitalInput;



public class ToggleIntakespinMotor extends CommandBase {
   
public ToggleIntakespinMotor() {
    this.addRequirements(Robot.intake); 
}

@Override
public void initialize() {
    if (Robot.intake.getDropMotorState() == Intake.IntakeMotorState.OFF) {
        Robot.intake.setMotorState(Intake.IntakeMotorState.ON);
    } else {
        Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
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