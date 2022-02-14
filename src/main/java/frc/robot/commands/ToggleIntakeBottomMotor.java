   package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.commands.ToggleIntakeBottomMotor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;



public class ToggleIntakeBottomMotor extends CommandBase {
   
 

    @Override
    public void initialize() {
        if (this.getRawButtonPressed(1)) {
            Robot.intake.setMotorState(Intake.IntakeBottomMotorState.ON);
        } else {
            Robot.intake.setMotorState(Intake.IntakeBottomMotorState.OFF);
        }
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        if (Robot.intake.getSensorState() == true) {
            Robot.intake.setMotorState(Intake.IntakeBottomMotorState.REVERSED);
        }
        return true;
    }
}