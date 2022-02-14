package frc.robot.commands.homing;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Climber.ClimberMotorState;

public class HomeAngle extends CommandBase {
    // Check if angle systems are homed
    boolean isAngleHomed = false;

    public HomeAngle() {
        addRequirements(Robot.climber);
    }

    @Override
    public void initialize() {
        if (Robot.climber.getRightAngleSwitch() == true) {
            Robot.climber.getAngleMotor().set(0.1);
        } else {
            Robot.climber.getAngleMotor().set(-0.1);
        }
    }

    @Override
    public void execute() {
        if(Robot.climber.getRightAngleSwitch() == false) {
            // Keep angle motors moving downward
            Robot.climber.getAngleMotor().set(-0.1);
        } else if (Robot.climber.getRightAngleSwitch() == true) {
            // Turn off angle motors
            Robot.climber.setAngleMotorState(ClimberMotorState.OFF);
            // Reset encoder revolution count to 0
            Robot.climber.setAngleMotorPosition(0.0);
            // Set homing as true
            this.isAngleHomed = true;
        }
    }

    @Override
    public void end(boolean interrupted) {
        Robot.climber.setAngleMotorState(ClimberMotorState.OFF);
        
        if (this.isAngleHomed){
        	System.out.println("Home finished");
        } else {
            System.out.println("Homeing error detected");
        }
    }

    @Override
    public boolean isFinished() {
        return this.isAngleHomed;
    }
}