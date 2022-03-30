package frc.robot.commands.homing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class HomeAngle extends CommandBase {
    // Check if angle systems are homed
    boolean isRightHomed = false;
    boolean isLeftHomed = false;

    boolean zeroedRight = false;
    boolean zeroedLeft = false;

    public HomeAngle() {
        addRequirements(Robot.climber);
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        if(Robot.climber.getRightAngleSwitch() == false && this.zeroedRight == false) {
            // Keep angle motors moving downward
            Robot.climber.getAngleMotorRight().set(-0.06);
        } else if (Robot.climber.getRightAngleSwitch() == true && this.zeroedRight == false) {
            Robot.climber.setAngleMotorPosition(0.0);
            Robot.climber.getAngleMotorRight().set(0.06);
            this.zeroedRight = true;
        }

        if (Robot.climber.getAngleMotorPositionRight() >= 2 && this.zeroedRight == true) {
            Robot.climber.getAngleMotorRight().set(0);
            this.isRightHomed = true;
        }

        if(Robot.climber.getLeftAngleSwitch() == false && this.zeroedLeft == false) {
            // Keep angle motors moving downward
            Robot.climber.getAngleMotorLeft().set(-0.06);
        } else if (Robot.climber.getLeftAngleSwitch() == true && this.zeroedLeft == false) {
            Robot.climber.setAngleMotorPositionLeft(0.0);
            Robot.climber.getAngleMotorLeft().set(0.06);
            this.zeroedLeft = true;
        }

        if (Robot.climber.getAngleMotorPositionLeft() >= 2 && this.zeroedLeft == true) {
            Robot.climber.getAngleMotorLeft().set(0);
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
            this.zeroedLeft = false;
            this.zeroedRight = false;
            return true;
        } else {
            return false;
        }
    }
}