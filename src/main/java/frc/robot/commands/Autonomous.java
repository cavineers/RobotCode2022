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
    public void initialize() {}

    @Override
    public void execute() { 
        this.startTime = Timer.getFPGATimestamp();

        if(this.startTime < 3){
            this.rc.drivetrain.drive(-0.3, 0, true);
        } else if (this.startTime >= 3 && this.startTime < 3.05){
            this.rc.drivetrain.drive(0, 0, true);
            this.rc.m_autoShoot.schedule();
        } else if (this.startTime >= 3.05 && this.startTime < 5){
            this.rc.m_autoShoot.cancel(); 
        } else if(this.startTime >= 5 && this.startTime < 7){
            //Change these based on where we are placed at start of match
            this.rc.drivetrain.drive(0, 0.1, true);// Turns Right
            //this.rc.drivetrain.drive(0, -0.1, true);//Turns Left
            //this.rc.drivetrain.drive(-0.3, 0, true); //Drive  
        } else if (this.startTime >= 7 && this.startTime < 7.5){
            this.rc.drivetrain.drive(0.3, 0, true);
        } else if (this.startTime >= 7.5 && this.startTime < 9){
            this.rc.drivetrain.drive(0, 0, true);
            this.rc.m_lowerIntake.schedule();
            this.rc.m_toggleIntake.schedule();
        } else if(this.startTime >= 9 && this.startTime < 11){
            this.rc.m_toggleIntake.cancel();
            this.rc.drivetrain.drive(-0.3, 0, true);
            this.rc.m_autoShoot.schedule();
        } else if (this.startTime >= 11.5 && this.startTime < 13){
            this.rc.m_autoShoot.cancel();
        } else if(this.startTime == 15){
            this.rc.drivetrain.drive(0, 0, true);
            this.rc.m_autoShoot.cancel();
            this.rc.m_toggleIntake.cancel();
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
        return (Timer.getFPGATimestamp()-this.startTime > 15);
    }
}
