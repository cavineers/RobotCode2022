package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.DriveMotion;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.RobotContainer.CurrentMode;
import frc.robot.subsystems.Elevator.ElevatorMotorState;

public class ClimberDrive extends CommandBase {

    private Joystick joy;
    private RobotContainer rc;

    public ClimberDrive(RobotContainer container, Joystick joy) {
        this.joy = joy;
        this.rc = container;
        this.addRequirements(Robot.climber, Robot.elevator);
    }

    @Override
    public void initialize() {
        Robot.elevator.setElevMotorState(ElevatorMotorState.OFF);
        Robot.climber.getAngleMotorRight().set(0.0);
        Robot.climber.getAngleMotorLeft().set(0.0);
    }

    @Override
    public void execute() {
        if (Robot.elevator.getElevatorPosition() < Constants.Climber.MaxElevatorRevolutions && Robot.elevator.getElevatorPosition() > 0) {
            Robot.elevator.getElevatorMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.5, 0.05));
        } else if (Robot.elevator.getElevatorPosition() > Constants.Climber.MaxElevatorRevolutions) {
            if(DriveMotion.add(this.joy.getRawAxis(1) / 1.5, 0.05) > 0) {
                Robot.elevator.getElevatorMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.5, 0.05));
            } else {
                Robot.elevator.setElevMotorState(ElevatorMotorState.OFF);
            }
        } else if (Robot.elevator.getElevatorPosition() <= 0) {
            if(DriveMotion.add(this.joy.getRawAxis(1) / 1.5, 0.05) < 0) {
                Robot.elevator.getElevatorMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.5, 0.05));
            } else {
                Robot.elevator.setElevMotorState(ElevatorMotorState.OFF);
            }
        }

        if (Robot.climber.getAngleMotorPositionRight() < Constants.Climber.MaxSwivelRevolutions && Robot.climber.getAngleMotorPositionRight() > 0) {
            Robot.climber.getAngleMotorRight().set(-DriveMotion.add(this.joy.getRawAxis(4) / 2.3, 0.05));
        } else if (Robot.climber.getAngleMotorPositionRight() > Constants.Climber.MaxSwivelRevolutions) {
            if(-DriveMotion.add(this.joy.getRawAxis(4) / 2.3, 0.05) < 0) {
                Robot.climber.getAngleMotorRight().set(-DriveMotion.add(this.joy.getRawAxis(4) / 2.3, 0.05));
            } else {
                if (Robot.climber.getAngleMotorPositionRight() > Constants.Climber.MaxSwivelRevolutions + 1.2) {
                    Robot.climber.getAngleMotorRight().set(-0.2);
                } else {
                    Robot.climber.getAngleMotorRight().set(0.0);
                }
            }
        } else if (Robot.climber.getAngleMotorPositionRight() <= 0) {
            if(-DriveMotion.add(this.joy.getRawAxis(4) / 2.3, 0.05) > 0) {
                Robot.climber.getAngleMotorRight().set(-DriveMotion.add(this.joy.getRawAxis(4) / 2.3, 0.05));
            } else {
                Robot.climber.getAngleMotorRight().set(0.0);
            }
        }

        // Move any direction if between 0 and max
        if (Robot.climber.getAngleMotorPositionLeft() < Constants.Climber.MaxSwivelRevolutions && Robot.climber.getAngleMotorPositionLeft() > 0) {
            if (-DriveMotion.add(this.joy.getRawAxis(4) / 2.3, 0.05) < 0) {
                Robot.climber.getAngleMotorLeft().set(-DriveMotion.add((this.joy.getRawAxis(4) / 2.3) + Constants.Climber.LeftAngleConstant, 0.05));
            } else {
                Robot.climber.getAngleMotorLeft().set(-DriveMotion.add((this.joy.getRawAxis(4) / 2.3) - Constants.Climber.LeftAngleConstant, 0.05));
            }
        } else if (Robot.climber.getAngleMotorPositionLeft() > Constants.Climber.MaxSwivelRevolutions) {
            if(-DriveMotion.add(this.joy.getRawAxis(4) / 2.3, 0.05) < 0) {
                Robot.climber.getAngleMotorLeft().set(-DriveMotion.add((this.joy.getRawAxis(4) / 2.3) + Constants.Climber.LeftAngleConstant, 0.05));
            } else {
                if (Robot.climber.getAngleMotorPositionLeft() > Constants.Climber.MaxSwivelRevolutions + 1.2) {
                    Robot.climber.getAngleMotorLeft().set(-0.2);
                } else {
                    Robot.climber.getAngleMotorLeft().set(0.0);
                }
            }
        } else if (Robot.climber.getAngleMotorPositionLeft() <= 0) {
            if(-DriveMotion.add(this.joy.getRawAxis(4) / 2.3, 0.05) > 0) {
                Robot.climber.getAngleMotorLeft().set(-DriveMotion.add((this.joy.getRawAxis(4) / 2.3) - Constants.Climber.LeftAngleConstant, 0.05));
            } else {
                Robot.climber.getAngleMotorLeft().set(0.0);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        // Turn off motors at the end
        Robot.elevator.setElevMotorState(ElevatorMotorState.OFF);
        Robot.climber.getAngleMotorRight().set(0.0);
        Robot.climber.getAngleMotorLeft().set(0.0);
    }

    @Override
    public boolean isFinished() {
        // Keep running unless in DRIVE mode
        if(this.rc.mode == CurrentMode.CLIMB) {
            return false;
        } else {
            return true;
        }
    }
}
