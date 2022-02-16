package frc.robot.commands.homing;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class HomeAll extends CommandBase {
    
    public HomeAll() {}

    // Set Motor State to ON / OFF
    @Override
    public void initialize() {
        new HomeAngle().schedule();
        new HomeElevator().schedule();
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {}

    @Override
    public boolean isFinished() {
        return true;
    }
}
