package frc.robot.commands.homing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator.ElevatorMotorState;

public class HomeElevator extends CommandBase {
    // Check if elevator systems are homed
    boolean isElevatorHomed = false;

    public HomeElevator() {
        addRequirements(Robot.elevator);
    }

    @Override
    public void initialize() {
        if (Robot.elevator.getRightElevatorSwitch() == true) {
            Robot.elevator.getElevatorMotor().set(0.1);
        } else {
            Robot.elevator.getElevatorMotor().set(-0.1);
        }
    }

    @Override
    public void execute() {
        if(Robot.elevator.getRightElevatorSwitch() == false) {
            // Keep elevator moving downward
            Robot.elevator.getElevatorMotor().set(-0.1);
        } else if (Robot.elevator.getRightElevatorSwitch() == true) {
            // Turn off elevator
            Robot.elevator.setElevMotorState(ElevatorMotorState.OFF);
            // Reset encoder revolution count to 0
            Robot.elevator.setElevatorMotorPosition(0.0);
            // Set homing as true
            this.isElevatorHomed = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.elevator.setElevMotorState(ElevatorMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        return this.isElevatorHomed;
    }
}
