package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.DriveMotion;
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
        // Height max 2.5 ft | est. 2.5 inches per rev | est. 120 revolutions needed

        // Set the motor speed to the axis
        this.rc.climber.getElevatorMotor().set(DriveMotion.add(this.joy.getRawAxis(1), 0.05));
        this.rc.climber.getAngleMotor().set(-DriveMotion.add(this.joy.getRawAxis(4), 0.05));
    
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
        if(this.rc.mode == CurrentMode.CLIMB) {
            return false;
        } else {
            return true;
        }
    }
}
