package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.DriveTrain;
import java.io.File;
import java.util.Scanner;
import com.revrobotics.CANSparkMax;


public class AutonomousExecute extends CommandBase {
    private String m_filename = "";
    private Scanner m_data;
    private boolean m_finished = false;

    public enum AutoMovementState{
        OFF,
        ON,
    }
  
    public AutoMovementState m_AutoMovementstate = AutoMovementState.OFF;

    public void AutoExecute(String filename) {
        this.m_filename = filename;
    }


    @Override
    public void initialize() {
        Robot.drive.setState(DriveTrain.OTHER_AUTO);

        try {
            File file = new File("/home/lvuser/paths/" + this.m_filename);
            SmartDashboard.putString("path", file.getAbsolutePath());
            this.m_data = new Scanner(file); 
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            Robot.logger.addInfo("AutoExec", "Unable to load file " + this.m_filename);
        }
    }

    @Override
    public void execute() {
        if (this.m_data.hasNextLine()) {
            String[] data = this.m_data.nextLine().split(",");

            String forward = data[0];
            String rotation = data[2];

            System.out.println(forward);

            // Robot.swerveDrive.heldSwerve(Double.parseDouble(forward), Double.parseDouble(strafe), Double.parseDouble(rotation), false);
        } else {
            this.m_finished = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.m_data.close();
    }

    @Override
    public boolean isFinished() {
        return this.m_finished;
    }

/**
 * Get the current AutoMovement state.
 * @return AutoMovement state
 */
public AutoMovementState getAutoMovementState() {
    return this.m_AutoMovementstate;
}
}

