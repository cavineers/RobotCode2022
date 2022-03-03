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
            this.rc.drivetrain.drive(0, 0, true); //driving backwards
        } else if (this.startTime >= 3.05 && this.startTime < 7){
            this.rc.m_autoShoot.schedule(); //bot revs up to shoot and shoots
        } else if(this.startTime >= 7 && this.startTime < 7.5){
            this.rc.m_autoShoot.cancel(); // Now weve shoot the perloaded ball and it slows down
            //Change these based on where we are placed at start of match
            this.rc.drivetrain.drive(0, 0.1, true);// Turns Right
            //this.rc.drivetrain.drive(0, -0.1, true);//Turns Left
            //this.rc.drivetrain.drive(-0.3, 0, true); //Drive  
        } else if (this.startTime >= 8 && this.startTime < 10){
            this.rc.drivetrain.drive(0.3, 0, true); //driving toward next ball
        } else if (this.startTime >= 7.5 && this.startTime < 9){ //we lower the intake 
            this.rc.drivetrain.drive(0, 0, true);
            this.rc.m_lowerIntake.schedule(); 
        } else if(this.startTime >= 9 && this.startTime < 11){//we pick up the ball
            this.rc.m_toggleIntake.schedule();
    
        } else if (this.startTime >= 11.5 && this.startTime < 13){//we turn
            this.rc.drivetrain.drive(0, 0.1, true);
        } else if (this.startTime >= 13.5 && this.startTime < 15){//we shoot the ball(we sit on the toile-)
            this.rc.m_autoShoot.schedule();
        } else if(this.startTime == 15){//stopping everything
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
