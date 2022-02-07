package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.lib.DriveMotion;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleopDrive extends CommandBase {
    private DriveTrain driveTrain;
    private Joystick joystick;

    public TeleopDrive(DriveTrain drive, Joystick joy) {
        this.addRequirements(drive);
        this.driveTrain = drive;
        this.joystick = joy;
    }

    @Override
    public void initialize() {}

    @Override
    public void execute() {
        // Put numbers on logging platform
        SmartDashboard.putNumber("Joystick steering value", this.joystick.getRawAxis(4));
        SmartDashboard.putNumber("Joystick drive value", this.joystick.getRawAxis(1));
        SmartDashboard.putNumber("Left Drive Speed", this.driveTrain.getActiveLeftSpeed());
        SmartDashboard.putNumber("Right Drive Speed", this.driveTrain.getActiveRightSpeed());

        // Get the joystick to drive forward and turn the robot
        double steer = -DriveMotion.add(this.joystick.getRawAxis(4), 0.05);
        double drive = DriveMotion.add(this.joystick.getRawAxis(1), 0.05);

        this.driveTrain.drive(drive, steer, true);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        this.driveTrain.drive(0, 0, false);
    }
}
