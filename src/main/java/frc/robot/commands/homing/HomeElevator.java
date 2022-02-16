package frc.robot.commands.homing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Elevator.ElevatorMotorState;

public class HomeElevator extends CommandBase {
    // Check if elevator systems are homed
    boolean isElevatorHomed = false;
    RobotContainer rc;

    public HomeElevator(RobotContainer container) {
        this.rc = container;
        addRequirements(container.elevator);
    }

    @Override
    public void initialize() {
        if (this.rc.elevator.getRightElevatorSwitch() == true) {
            this.rc.elevator.getElevatorMotor().set(0.1);
        } else {
            this.rc.elevator.getElevatorMotor().set(-0.1);
        }
    }

    @Override
    public void execute() {
        if(this.rc.elevator.getRightElevatorSwitch() == false) {
            // Keep elevator moving downward
            this.rc.elevator.getElevatorMotor().set(-0.1);
        } else if (this.rc.elevator.getRightElevatorSwitch() == true) {
            // Turn off elevator
            this.rc.elevator.setElevMotorState(ElevatorMotorState.OFF);
            // Reset encoder revolution count to 0
            this.rc.elevator.setElevatorMotorPosition(0.0);
            // Set homing as true
            this.isElevatorHomed = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.rc.elevator.setElevMotorState(ElevatorMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        return this.isElevatorHomed;
    }
}
