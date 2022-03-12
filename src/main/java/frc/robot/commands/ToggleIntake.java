package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Intake;

public class ToggleIntake extends CommandBase {

    private boolean isDone = false;

    private boolean holding = false;

    private double m_timestamp;

    private double m_timestampIntake;
    private boolean timeSet = false;

    public ToggleIntake() {
        this.addRequirements(Robot.intake);
    }

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        this.m_timestamp = Timer.getFPGATimestamp();

        if (Robot.shooter.getSensorBallState() == true) {
            this.holding = true;
        } else {
            this.holding = false;
        }

        if (Robot.intake.getIntakeMotorState() == Intake.IntakeMotorState.OFF) {
            Robot.intake.setMotorState(Intake.IntakeMotorState.ON);
            this.isDone = false;
        } else {
            Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
            this.isDone = true;
        }
    }

    @Override
    public void execute() {
        SmartDashboard.putBoolean("Intake Sensor", Robot.intake.getSensorOneState());

        if (this.holding == true) {
            if (Robot.intake.getSensorOneState() == true) {
                if (this.timeSet == false) {
                    this.timeSet = true;
                    this.m_timestampIntake = Timer.getFPGATimestamp();
                } else {
                    if (Timer.getFPGATimestamp() - this.m_timestampIntake >= 0.5) {
                        Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
                        this.isDone = true;
                    }
                }
            }
        } else {
            Robot.shooter.enableFeeder(0.4);
            if (Robot.shooter.getSensorBallState() == true) {
                Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
                Robot.shooter.disableFeeder();
                this.isDone = true; 
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.intake.setMotorState(Intake.IntakeMotorState.OFF);
        Robot.shooter.disableFeeder();
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() - this.m_timestamp >= 0.1 && Robot.m_robotContainer.joy.getRawButton(2)) {
            this.isDone = true;
        }

        return this.isDone;
    }
}
