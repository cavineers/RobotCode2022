package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.DriveMotion;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.RobotContainer.CurrentMode;

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
        Robot.elevator.getElevatorRightMotor().set(0.0);
        Robot.elevator.getElevatorLeftMotor().set(0.0);
        Robot.climber.getAngleMotorRight().set(0.0);
        Robot.climber.getAngleMotorLeft().set(0.0);
    }

    @Override
    public void execute() {
        // Elevator
        // double elevatorSetpoint = height / 0.34;

        // Right Elevator
        if (Robot.climber.getAngleMotorPositionRight() < 70) {
            if (Robot.elevator.getElevatorRightPosition() < Constants.Climber.MaxElevatorRevolutionsInital && Robot.elevator.getElevatorRightPosition() > 0) {
                Robot.elevator.getElevatorRightMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
            } else if (Robot.elevator.getElevatorRightPosition() > Constants.Climber.MaxElevatorRevolutionsInital) {
                if(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05) > 0) {
                    Robot.elevator.getElevatorRightMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
                } else {
                    Robot.elevator.getElevatorRightMotor().set(0.0);
                }
            } else if (Robot.elevator.getElevatorRightPosition() <= 0) {
                if(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05) < 0) {
                    Robot.elevator.getElevatorRightMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
                } else {
                    Robot.elevator.getElevatorRightMotor().set(0.0);
                }
            }
        } else {
            if (Robot.elevator.getElevatorRightPosition() < Constants.Climber.MaxElevatorRevolutions && Robot.elevator.getElevatorRightPosition() > 0) {
                Robot.elevator.getElevatorRightMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
            } else if (Robot.elevator.getElevatorRightPosition() > Constants.Climber.MaxElevatorRevolutions) {
                if(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05) > 0) {
                    Robot.elevator.getElevatorRightMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
                } else {
                    Robot.elevator.getElevatorRightMotor().set(0.0);
                }
            } else if (Robot.elevator.getElevatorRightPosition() <= 0) {
                if(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05) < 0) {
                    Robot.elevator.getElevatorRightMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
                } else {
                    Robot.elevator.getElevatorRightMotor().set(0.0);
                }
            }
        }

        // Left Elevator
        if (Robot.climber.getAngleMotorPositionRight() < 70) {
            if (Robot.elevator.getElevatorLeftPosition() < Constants.Climber.MaxElevatorRevolutionsInital && Robot.elevator.getElevatorLeftPosition() > 0) {
                Robot.elevator.getElevatorLeftMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
            } else if (Robot.elevator.getElevatorLeftPosition() > Constants.Climber.MaxElevatorRevolutionsInital) {
                if(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05) > 0) {
                    Robot.elevator.getElevatorLeftMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
                } else {
                    Robot.elevator.getElevatorLeftMotor().set(0.0);
                }
            } else if (Robot.elevator.getElevatorLeftPosition() <= 0) {
                if(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05) < 0) {
                    Robot.elevator.getElevatorLeftMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
                } else {
                    Robot.elevator.getElevatorLeftMotor().set(0.0);
                }
            }
        } else {
            if (Robot.elevator.getElevatorLeftPosition() < Constants.Climber.MaxElevatorRevolutions && Robot.elevator.getElevatorLeftPosition() > 0) {
                Robot.elevator.getElevatorLeftMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
            } else if (Robot.elevator.getElevatorLeftPosition() > Constants.Climber.MaxElevatorRevolutions) {
                if(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05) > 0) {
                    Robot.elevator.getElevatorLeftMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
                } else {
                    Robot.elevator.getElevatorLeftMotor().set(0.0);
                }
            } else if (Robot.elevator.getElevatorLeftPosition() <= 0) {
                if(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05) < 0) {
                    Robot.elevator.getElevatorLeftMotor().set(DriveMotion.add(this.joy.getRawAxis(1) / 1.4, 0.05));
                } else {
                    Robot.elevator.getElevatorLeftMotor().set(0.0);
                }
            }
        }


        // Angle Right
        if (Robot.climber.getAngleMotorPositionRight() < Constants.Climber.MaxSwivelRevolutions && Robot.climber.getAngleMotorPositionRight() > 1) {
            Robot.climber.getAngleMotorRight().set(-DriveMotion.add(this.joy.getRawAxis(4) / 1.6, 0.05));
        } else if (Robot.climber.getAngleMotorPositionRight() > Constants.Climber.MaxSwivelRevolutions) {
            if(-DriveMotion.add(this.joy.getRawAxis(4) / 1.6, 0.05) < 0) {
                Robot.climber.getAngleMotorRight().set(-DriveMotion.add(this.joy.getRawAxis(4) / 1.6, 0.05));
            } else {
                if (Robot.climber.getAngleMotorPositionRight() > Constants.Climber.MaxSwivelRevolutions + 3) {
                    Robot.climber.getAngleMotorRight().set(-0.2);
                } else {
                    Robot.climber.getAngleMotorRight().set(0.0);
                }
            }
        } else if (Robot.climber.getAngleMotorPositionRight() <= 1.5) {
            if(-DriveMotion.add(this.joy.getRawAxis(4) / 1.6, 0.05) > 0) {
                Robot.climber.getAngleMotorRight().set(-DriveMotion.add(this.joy.getRawAxis(4) / 1.6, 0.05));
            } else {
                Robot.climber.getAngleMotorRight().set(0.0);
            }
        }

        // Angle Left
        if (Robot.climber.getAngleMotorPositionLeft() < Constants.Climber.MaxSwivelRevolutions && Robot.climber.getAngleMotorPositionLeft() > 1) {
            if (-DriveMotion.add(this.joy.getRawAxis(4) / 1.6, 0.05) < 0) {
                Robot.climber.getAngleMotorLeft().set(-DriveMotion.add((this.joy.getRawAxis(4) / 1.6) + Constants.Climber.LeftAngleConstant, 0.05));
            } else {
                Robot.climber.getAngleMotorLeft().set(-DriveMotion.add((this.joy.getRawAxis(4) / 1.6) - Constants.Climber.LeftAngleConstant, 0.05));
            }
        } else if (Robot.climber.getAngleMotorPositionLeft() > Constants.Climber.MaxSwivelRevolutions) {
            if(-DriveMotion.add(this.joy.getRawAxis(4) / 1.6, 0.05) < 0) {
                Robot.climber.getAngleMotorLeft().set(-DriveMotion.add((this.joy.getRawAxis(4) / 1.6) + Constants.Climber.LeftAngleConstant, 0.05));
            } else {
                if (Robot.climber.getAngleMotorPositionLeft() > Constants.Climber.MaxSwivelRevolutions + 3) {
                    Robot.climber.getAngleMotorLeft().set(-0.2);
                } else {
                    Robot.climber.getAngleMotorLeft().set(0.0);
                }
            }
        } else if (Robot.climber.getAngleMotorPositionLeft() <= 1.5) {
            if(-DriveMotion.add(this.joy.getRawAxis(4) / 1.6, 0.05) > 0) {
                Robot.climber.getAngleMotorLeft().set(-DriveMotion.add((this.joy.getRawAxis(4) / 1.6) - Constants.Climber.LeftAngleConstant, 0.05));
            } else {
                Robot.climber.getAngleMotorLeft().set(0.0);
            }
        }
    }

    @Override
    public void end(boolean interrupted) {
        // Turn off motors at the end
        Robot.elevator.getElevatorRightMotor().set(0.0);
        Robot.elevator.getElevatorLeftMotor().set(0.0);
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
