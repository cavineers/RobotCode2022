package frc.robot.commands.homing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class HomeAngle extends CommandBase {
    // Check if angle systems are homed
    boolean isAngleHomed = false;
    boolean isRightHomed = false;
    boolean isLeftHomed = false;

    public HomeAngle() {
        addRequirements(Robot.climber);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(Robot.climber.getRightAngleSwitch() == false) {
            // Keep angle motors moving downward
            Robot.climber.getAngleMotorRight().set(-0.09);
        } else if (Robot.climber.getRightAngleSwitch() == true) {
            // Turn off angle motors
            Robot.climber.getAngleMotorRight().set(0.0);
            // Reset encoder revolution count to 0
            Robot.climber.setAngleMotorPosition(0.0);
            // Set homing as true
            this.isRightHomed = true;
        }

        if(Robot.climber.getLeftAngleSwitch() == false) {
            // Keep angle motors moving downward
            Robot.climber.getAngleMotorLeft().set(-0.09);
        } else if (Robot.climber.getLeftAngleSwitch() == true) {
            // Turn off angle motors
            Robot.climber.getAngleMotorLeft().set(0.0);
            // Reset encoder revolution count to 0
            Robot.climber.setAngleMotorPositionLeft(0.0);
            // Set homing as true
            this.isLeftHomed = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.climber.getAngleMotorRight().set(0.0);
        Robot.climber.getAngleMotorLeft().set(0.0);
    }

    @Override
    public boolean isFinished() {
        if (this.isLeftHomed && this.isRightHomed) {
            this.isLeftHomed = false;
            this.isRightHomed = false;
            return true;
        } else {
            return false;
        }
    }
}