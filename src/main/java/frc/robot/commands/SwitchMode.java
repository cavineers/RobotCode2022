package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class SwitchMode extends CommandBase {
    private RobotContainer rc;

    /**
     * Sets the safety mode.
     * @param container Robot container.
     */
    public SwitchMode(RobotContainer container) {
        this.rc = container;
    }

    @Override
    public void initialize() {
        if (this.rc.mode == RobotContainer.CurrentMode.DRIVE) {
            this.rc.mode = RobotContainer.CurrentMode.CLIMB;
        } else {
            this.rc.mode = RobotContainer.CurrentMode.DRIVE;
        }
    }

    @Override
    public void execute() {}

    @Override
    public void end(boolean interrupted) {
        SmartDashboard.putString("ChassisMode", this.rc.mode.toString());
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
