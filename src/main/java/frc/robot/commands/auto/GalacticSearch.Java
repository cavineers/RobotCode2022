package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.lib.Target;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Intake.IntakeMotorState;
import frc.robot.subsystems.DriveTrain.DriveTrainState;

/**
 * Galactic Search autonomous command.
 * Note: Tolerance for Vision calcs is +- 2 inches
 */
public class GalacticSearch extends CommandBase {
    // Finished
    private boolean m_finished = false;

    // PIDs
    private PIDController m_td = new PIDController(Constants.ObjVision.kDistancePID_P, 
            Constants.ObjVision.kDistancePID_I, Constants.ObjVision.kDistancePID_D);

    private PIDController m_a = new PIDController(Constants.ObjVision.kXPID_P, 
            Constants.ObjVision.kXPID_I, Constants.ObjVision.kXPID_D);

    private PIDController m_rotatePid = new PIDController(Constants.ObjVision.kAnglePIDp,
            Constants.ObjVision.kAnglePIDi, Constants.ObjVision.kAnglePIDd);


    public GalacticSearch() {
        this.addRequirements(Robot.drive);
    }

    @Override
    public void initialize() {
        Robot.logger.addInfo("GalacticSearch", "Galactic Search Autonomous Command Activated.");

        //Turns Intake Subsystem On.
        Robot.intake.setMotorState(IntakeMotorState.ON);

        // PID setup.
        this.m_td.setTolerance(Constants.ObjVision.kDistancePID_Tolerance);
        this.m_a.setTolerance(Constants.ObjVision.kXPID_Tolerance);
        this.m_rotatePid.setTolerance(1.0);

        // Set setpoint
        this.m_td.setSetpoint(0.0);
        this.m_a.setSetpoint(0.0);
        this.m_rotatePid.setSetpoint(0.0);

        // Switch to other autonomous
        Robot.drive.setState(DriveTrainState.OTHER_AUTO);
    }

    @Override
    public void execute() {
        // If we haven't collected all PowerCells
        if (Robot.transportation.getBallCount() < 3) {
            Target closestPowerCell = Robot.vision.getPowerCellTarget();

            // Check distance
            if (closestPowerCell.getDistance() <= 0 || !closestPowerCell.isSet()) {
                // Log PowerCell error.
                Robot.logger.addInfo("GalacticSearch", "Closest PowerCell is within the bot or not set", closestPowerCell);

                // Skip the rest of the method
                return;
            }

            // Calculations
            double td = closestPowerCell.getDistance(); // distance y (b) value in law of sines
            final double tx = closestPowerCell.getTx(); // A angle in law of sines
            final double a = (Math.tan(tx) * td);
            td = Math.abs(td);
            // final double a = Math.sqrt(Math.pow((td / Math.sin(90 - tx)), 2) + Math.pow(td, 2)); // distance x (a) in law of sines
            final double vRotatePid = this.m_rotatePid.calculate(Robot.swerveDrive.getAngle().getDegrees(), 0.0);
            final double vtd = this.m_td.calculate(td);
            final double va = -this.m_a.calculate(a);

            // Send debug info to Smart Dashboard
            SmartDashboard.putNumber("gs_td", td);
            SmartDashboard.putNumber("gs_tx", tx);
            SmartDashboard.putNumber("gs_a", a);
            SmartDashboard.putNumber("gs_vtd", vtd);
            SmartDashboard.putNumber("gs_va", va);
            SmartDashboard.putNumber("gs_rot", vRotatePid);
            SmartDashboard.putNumber("gs_ty", closestPowerCell.getTy());
            Robot.logger.addInfo("Vision Data", Double.toString(td) + "||" + Double.toString(a) + "||" + Double.toString(tx));

            // Drive the robot based on the coordinates of power cell
            // Robot.swerveDrive.heldSwerve(-vtd, va, vRotatePid, true); // TODO: test if field-oriented works and solves issues
            // Robot.swerveDrive.heldSwerve(0.0, va, vRotatePid, true); // TODO: test if field-oriented works and solves issues
        } else {
            // Finish command if more than three balls are in the chamber
            this.m_finished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        // Robot.swerveDrive.swerve(3?00, 0, 0, false);
        Robot.logger.addInfo("GalacticSearch", "Autonomous Galactic Search Command Ended");
        Robot.intake.setMotorState(IntakeMotorState.OFF);
    }

    @Override
    public boolean isFinished() {
        return this.m_finished;
    }
}