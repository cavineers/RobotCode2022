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
//Drives forward for 3 seconds and enables shooter/stops and disables shooter
        if(this.startTime >= 0){
            this.rc.drivetrain.drive(-0.3, 0, true);
            // enable shooter
            this.rc.m_autoShootCommand.execute();
        } else if (this.startTime <= 0.3){
            this.rc.drivetrain.drive(0, 0, true);
            //disable shooter
            this.rc.m_autoShootCommand.end(true);
        }

//Go to closest (teams) cargo, toggle intake ON, enable shooter
        if(this.startTime >= 3.005){
            this.rc.drivetrain.drive(0, 0, true); //TODO change the values in .drive() to turn
        } else if (this.startTime <= 3.01){
            this.rc.drivetrain.drive(0, 0, true);
        }
        if(this.startTime >= 0.4){
            this.rc.drivetrain.drive(0, 0.1, true);//Turns 
            //this.rc.drivetrain.drive(0, -0.1, true);//Turns 
        } else if (this.startTime <= 0.45){
            this.rc.drivetrain.drive(0, 0, true);
        }
        
        if (this.startTime >= 0.45){
            this.rc.drivetrain.drive(-0.3, 0, true);
        } else if (this.startTime <= 0.5){
            this.rc.drivetrain.drive(0, 0, true);
            this.rc.m_intakeCommand.execute();
        } else if(this.startTime >= 0.6){
            //TODO toggle intake off
            this.rc.m_intakeCommand.end();
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.rc.drivetrain.drive(0, 0, true);
    }

    @Override
    public boolean isFinished() {
        return (Timer.getFPGATimestamp()-this.startTime > 1.5);
    }
}
