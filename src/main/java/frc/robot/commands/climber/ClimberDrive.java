package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.DriveMotion;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.RobotContainer.CurrentMode;
import frc.robot.subsystems.Climber.ClimberMotorState;
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
        Robot.climber.setAngleMotorState(ClimberMotorState.OFF);
    }

    @Override
    public void execute() {
        // Set the motor speed to the axis
        Shuffleboard.getTab("Climber").add("Elevator Position", Robot.elevator.getElevatorPosition());
        
        if (Robot.elevator.getElevatorPosition() < Constants.Climber.MaxElevatorRevolutions && Robot.elevator.getElevatorPosition() > 0) {
            Robot.elevator.getElevatorMotor().set(-DriveMotion.add(this.joy.getRawAxis(1) / 3, 0.05));
        } else if (Robot.elevator.getElevatorPosition() > Constants.Climber.MaxElevatorRevolutions) {
            if(-DriveMotion.add(this.joy.getRawAxis(1) / 3, 0.05) < 0) {
                Robot.elevator.getElevatorMotor().set(-DriveMotion.add(this.joy.getRawAxis(1) / 3, 0.05));
            } else {
                Robot.elevator.setElevMotorState(ElevatorMotorState.OFF);
            }
        } else if (Robot.elevator.getElevatorPosition() <= 0) {
            if(-DriveMotion.add(this.joy.getRawAxis(1) / 3, 0.05) > 0) {
                Robot.elevator.getElevatorMotor().set(-DriveMotion.add(this.joy.getRawAxis(1) / 3, 0.05));
            } else {
                Robot.elevator.setElevMotorState(ElevatorMotorState.OFF);
            }
        }

        Shuffleboard.getTab("Climber").add("Swivel Position", Robot.climber.getAngleMotorPosition());
    
        if (Robot.climber.getAngleMotorPosition() < Constants.Climber.MaxSwivelRevolutions && Robot.climber.getAngleMotorPosition() > 0) {
            Robot.climber.getAngleMotor().set(-DriveMotion.add(this.joy.getRawAxis(4) / 3, 0.05));
        } else if (Robot.climber.getAngleMotorPosition() > Constants.Climber.MaxSwivelRevolutions) {
            if(-DriveMotion.add(this.joy.getRawAxis(4) / 3, 0.05) < 0) {
                Robot.climber.getAngleMotor().set(-DriveMotion.add(this.joy.getRawAxis(4) / 3, 0.05));
            } else {
                Robot.climber.setAngleMotorState(ClimberMotorState.OFF);
            }
        } else if (Robot.climber.getAngleMotorPosition() <= 0) {
            if(-DriveMotion.add(this.joy.getRawAxis(4) / 3, 0.05) > 0) {
                Robot.climber.getAngleMotor().set(-DriveMotion.add(this.joy.getRawAxis(4) / 3, 0.05));
            } else {
                Robot.climber.setAngleMotorState(ClimberMotorState.OFF);
            }
        }

        Shuffleboard.getTab("Climber").add("angle axis", this.joy.getRawAxis(4) / 3);
        Shuffleboard.getTab("Climber").add("elev axis", this.joy.getRawAxis(1) / 3);
    }

    @Override
    public void end(boolean interrupted) {
        // Turn off motors at the end
        Robot.elevator.setElevMotorState(ElevatorMotorState.OFF);
        Robot.climber.setAngleMotorState(ClimberMotorState.OFF);
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
