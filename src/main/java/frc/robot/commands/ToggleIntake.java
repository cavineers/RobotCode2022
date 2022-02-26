package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends CommandBase {

    private boolean isDone = false;

    private double m_timestamp;

    public ToggleIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        this.m_timestamp = Timer.getFPGATimestamp();
        if (Robot.intake.getIntakeMotorState() == Intake.IntakeMotorState.OFF) {
            Robot.intake.setMotorState(Intake.IntakeMotorState.ON);
            isDone = false;
        } else {
            Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
            isDone = true;
        }
    }

    @Override
    public void execute() {
        SmartDashboard.putBoolean("Intake Sensor", Robot.intake.getSensorOneState());
        if (Robot.intake.getSensorOneState() == true) {
            Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
            isDone = true;
       } 
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        /*if (Robot.intake.getSensorOneState() == true) {
            return true;
        } else {
            return false;
        }*/
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0.1 && Robot.m_robotContainer.joy.getRawButton(2)) {
            this.isDone = true;
        }

        return isDone;
    }
}
