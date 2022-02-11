package frc.robot.commands.homing;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class HomeAll extends SequentialCommandGroup {
    public HomeAll() {
        addCommands(
            new HomeAngle(),
            new HomeElevator()
        );
    }
}
