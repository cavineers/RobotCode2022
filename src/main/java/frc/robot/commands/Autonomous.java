package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class Autonomous extends CommandBase {
    private RobotContainer rc;

    private double startTime = 0;

    public Autonomous(RobotContainer rc) {
        this.rc = rc;
    }

    @Override
    public void initialize() {
        this.startTime = Timer.getFPGATimestamp();
    }

    @Override
    public void execute() { 
        if(this.startTime >= 0){
            this.rc.drivetrain.drive(-0.3, 0, true);
            this.rc.m_autoShoot.execute();
        } else if (this.startTime <= 3){
            this.rc.drivetrain.drive(0, 0, true);
            this.rc.m_autoShoot.end(true);
        }

        if(this.startTime >= 3.05){
            this.rc.drivetrain.drive(0, 0, true); 
        } else if (this.startTime <= 3.5){
            this.rc.drivetrain.drive(0.3, 0, true);
        }
        if(this.startTime >= 4){
            this.rc.drivetrain.drive(0, 0.1, true);// Turns Right
            //this.rc.drivetrain.drive(0, -0.1, true);//Turns Left 
        } else if (this.startTime <= 4.5){
            this.rc.drivetrain.drive(0, 0, true);
        }
        
        if (this.startTime >= 5){
            this.rc.drivetrain.drive(-0.3, 0, true);
        } else if (this.startTime <= 0.5){
            this.rc.drivetrain.drive(0, 0, true);
            this.rc.m_lowerIntake.schedule();
            this.rc.m_toggleIntake.schedule();

        } else if(this.startTime >= 6){
        this.rc.m_toggleIntake.cancel();
        this.rc.drivetrain.drive(-0.3, 0, true);
        this.rc.m_autoShoot.schedule();
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.rc.drivetrain.drive(0, 0, true);
        this.rc.m_autoShoot.cancel();
        this.rc.m_lowerIntake.cancel();
        this.rc.m_toggleIntake.cancel();
    }

    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp()-this.startTime > 1.5);
    }
}
