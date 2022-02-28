package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
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
//Drives backwards for 5 seconds and enables shooter/stops and disables shooter
        if(this.startTime >= 0){
            this.rc.drivetrain.drive(-0.3, 0, true);
            // enable shooter
            this.rc.m_autoShoot.schedule();
        } else if (this.startTime <= 5){
            this.rc.drivetrain.drive(0, 0, true);
            //disable shooter
            this.rc.m_autoShoot.cancel();
        }
//Go to closest (teams) cargo, toggle intake ON, enable shooter
        if(this.startTime >= 6){
            this.rc.drivetrain.drive(0, 0, true); 
        } else if (this.startTime <= 6.5){
            this.rc.drivetrain.drive(0, 0, true);
        }
        if(this.startTime >= 7.5){
            this.rc.drivetrain.drive(0, 0.1, true);//Turns right
            //this.rc.drivetrain.drive(0, -0.1, true);//Turns left
        } else if (this.startTime <= 8){
            this.rc.drivetrain.drive(0, 0, true);
        }
        if (this.startTime >= 8.5){
            this.rc.drivetrain.drive(-0.3, 0, true);
        } else if (this.startTime <= 10){
            this.rc.drivetrain.drive(0, 0, true);
            this.rc.m_lowerIntake.schedule();
            this.rc.m_toggleIntake.schedule();
        } else if(this.startTime >= 11){
            this.rc.m_lowerIntake.cancel();
            this.rc.m_toggleIntake.cancel();
        }
        if (this.startTime >= 13){
            this.rc.m_autoShoot.schedule();
        } else if (this.startTime >= 14){
            this.rc.m_autoShoot.cancel();
        }
       // if all else fail (Fnaf.Lore.explain){

    }

    @Override
    public void end(boolean interrupted) {
        this.rc.drivetrain.drive(0, 0, true);
    }

    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp()-this.startTime > 15);
    }
}
