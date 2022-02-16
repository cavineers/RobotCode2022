package frc.robot.commands.homing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber.ClimberMotorState;

public class HomeAngle extends CommandBase {
    // Check if angle systems are homed
    boolean isAngleHomed = false;
    RobotContainer rc;

    public HomeAngle(RobotContainer container) {
        this.rc = container;
    }

    @Override
    public void initialize() {
        if (this.rc.climber.getRightAngleSwitch() == true) {
            this.rc.climber.getAngleMotor().set(0.1);
        } else {
            this.rc.climber.getAngleMotor().set(-0.1);
        }
    }

    @Override
    public void execute() {
        if(this.rc.climber.getRightAngleSwitch() == false) {
            // Keep angle motors moving downward
            this.rc.climber.getAngleMotor().set(-0.1);
        } else if (this.rc.climber.getRightAngleSwitch() == true) {
            // Turn off angle motors
            this.rc.climber.setAngleMotorState(ClimberMotorState.OFF);
            // Reset encoder revolution count to 0
            this.rc.climber.setAngleMotorPosition(0.0);
            // Set homing as true
            this.isAngleHomed = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.rc.climber.setAngleMotorState(ClimberMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        return this.isAngleHomed;
    }
}