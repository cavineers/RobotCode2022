package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.DriveMotion;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.RobotContainer.CurrentMode;
import frc.robot.subsystems.Climber.ClimberMotorState;

public class ClimberDrive extends CommandBase {

    private Joystick joy;
    private RobotContainer rc;

    public ClimberDrive(RobotContainer container, Joystick joy) {
        this.joy = joy;
        this.rc = container;
        this.addRequirements(this.rc.climber);
    }

    @Override
    public void initialize() {
        this.rc.climber.setElevMotorState(ClimberMotorState.OFF);
        this.rc.climber.setAngleMotorState(ClimberMotorState.OFF);
    }

    @Override
    public void execute() {
        // Set the motor speed to the axis
        SmartDashboard.putNumber("Elevator Position", this.rc.climber.getElevatorPosition());
        
        if (this.rc.climber.getElevatorPosition() < Constants.Climber.MaxElevatorRevolutions && this.rc.climber.getElevatorPosition() > 0) {
            this.rc.climber.getElevatorMotor().set(-DriveMotion.add(this.joy.getRawAxis(1), 0.05));
        } else if (this.rc.climber.getElevatorPosition() > Constants.Climber.MaxElevatorRevolutions) {
            if(-DriveMotion.add(this.joy.getRawAxis(1), 0.05) < 0) {
                this.rc.climber.getElevatorMotor().set(-DriveMotion.add(this.joy.getRawAxis(1), 0.05));
            } else {
                this.rc.climber.setElevMotorState(ClimberMotorState.OFF);
            }
        } else if (this.rc.climber.getElevatorPosition() < 0) {
            if(-DriveMotion.add(this.joy.getRawAxis(1), 0.05) > 0) {
                this.rc.climber.getElevatorMotor().set(-DriveMotion.add(this.joy.getRawAxis(1), 0.05));
            } else {
                this.rc.climber.setElevMotorState(ClimberMotorState.OFF);
            }
        }

        SmartDashboard.putNumber("Swivel Position", this.rc.climber.getAngleMotorPosition());
    
        if (this.rc.climber.getAngleMotorPosition() < Constants.Climber.MaxSwivelRevolutions && this.rc.climber.getAngleMotorPosition() > 0) {
            this.rc.climber.getAngleMotor().set(-DriveMotion.add(this.joy.getRawAxis(4), 0.05));
        } else if (this.rc.climber.getAngleMotorPosition() > Constants.Climber.MaxSwivelRevolutions) {
            if(-DriveMotion.add(this.joy.getRawAxis(4), 0.05) < 0) {
                this.rc.climber.getAngleMotor().set(-DriveMotion.add(this.joy.getRawAxis(4), 0.05));
            } else {
                this.rc.climber.setAngleMotorState(ClimberMotorState.OFF);
            }
        } else if (this.rc.climber.getAngleMotorPosition() < 0) {
            if(-DriveMotion.add(this.joy.getRawAxis(4), 0.05) > 0) {
                this.rc.climber.getAngleMotor().set(-DriveMotion.add(this.joy.getRawAxis(4), 0.05));
            } else {
                this.rc.climber.setAngleMotorState(ClimberMotorState.OFF);
            }
        }

        SmartDashboard.putNumber("angle axis", this.joy.getRawAxis(4));
        SmartDashboard.putNumber("elev axis", this.joy.getRawAxis(1));
    }

    @Override
    public void end(boolean interrupted) {
        // Turn off motors at the end
        this.rc.climber.setElevMotorState(ClimberMotorState.OFF);
        this.rc.climber.setAngleMotorState(ClimberMotorState.OFF);
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
