package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class HomeElevator extends CommandBase {
    //
    boolean isElevatorHomed = false;

    public HomeElevator() {
        addRequirements(Robot.climber);
    }

}
