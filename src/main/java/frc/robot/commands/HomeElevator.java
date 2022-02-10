package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Climber.ClimberMotorState;

public class HomeElevator extends CommandBase {
    // Check if elevator systems are homed
    boolean isElevatorHomed = false;

    public HomeElevator() {
        addRequirements(Robot.climber);
    }

    @Override
    public void initialize() {
        if (Robot.climber.getElevatorLimitSwitch() == true) {
            Robot.climber.getElevatorMotor().set(0.1);
        } else {
            Robot.climber.getElevatorMotor().set(-0.1);
        }
    }

    @Override
    public void execute() {
        if(Robot.climber.getElevatorLimitSwitch() == false) {
            Robot.climber.getElevatorMotor().set(-0.1);
        } else if (Robot.climber.getElevatorLimitSwitch() == true) {
            Robot.climber.setElevMotorState(ClimberMotorState.OFF);
            this.isElevatorHomed = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.climber.setElevMotorState(ClimberMotorState.OFF);
        
        if (this.isElevatorHomed){
        	System.out.println("Home Finished");
        }
    }

    @Override
    public boolean isFinished() {
        return this.isElevatorHomed;
    }
}
