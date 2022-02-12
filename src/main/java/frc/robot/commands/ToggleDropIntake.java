   package frc.robot.commands;

import frc.robot.Robot;
import frc.robot.commands.ToggleDropIntake;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;



public class ToggleDropIntake extends CommandBase {
   
    Joystick joystick;

    public ToggleDropIntake(Joystick joy) {
        this.addRequirements(Robot.intake);
        this.joystick = joy;
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
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        if (Robot.intake.getSensorOneState() == true) {
            return true;
        } else if (this.joystick.getRawButtonPressed(1)) {
            return true;
        } else {
            return false;
        }
    }
}