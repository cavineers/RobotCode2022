package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
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
    public void initialize() {}

    @Override
    public void execute() {
        // Set the motor speed to the axis
        this.rc.climber.getElevatorMotor().set(this.joy.getRawAxis(1));
        this.rc.climber.getAngleMotor().set(this.joy.getRawAxis(4));
    }

    @Override
    public void end(boolean interrupted) {
        // Turn off motors at the end
        this.rc.climber.setElevMotorState(ClimberMotorState.OFF);
        this.rc.climber.setAngleMotorState(ClimberMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        if(this.rc.mode == CurrentMode.CLIMB) {
            return false;
        } else {
            return true;
        }
    }
}
