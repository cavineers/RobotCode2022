package frc.robot.commands.homing;

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
        if (Robot.climber.getRightElevatorSwitch() == true) {
            Robot.climber.getElevatorMotor().set(0.1);
        } else {
            Robot.climber.getElevatorMotor().set(-0.1);
        }
    }

    @Override
    public void execute() {
        if(Robot.climber.getRightElevatorSwitch() == false) {
            // Keep elevator moving downward
            Robot.climber.getElevatorMotor().set(-0.1);
        } else if (Robot.climber.getRightElevatorSwitch() == true) {
            // Turn off elevator
            Robot.climber.setElevMotorState(ClimberMotorState.OFF);
            // Reset encoder revolution count to 0
            Robot.climber.setElevatorMotorPosition(0.0);
            // Set homing as true
            this.isElevatorHomed = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.climber.setElevMotorState(ClimberMotorState.OFF);
        
        if (this.isElevatorHomed){
        	System.out.println("Home finished");
        } else {
            System.out.println("Homeing error detected");
        }
    }

    @Override
    public boolean isFinished() {
        return this.isElevatorHomed;
    }
}
