package frc.lib;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import java.text.DecimalFormat;

public class ShooterTargeting {

    private static final DecimalFormat df = new DecimalFormat("0.0");

    // -b squared plus c squared = a squared
    // C is the TD from limelight
    // B is the height of the goal - height of limelight
    // A is the distance between limelight and base of goal
    public static double findZ() {
        double z = (Math.sqrt((Math.pow(getTD(), 2) - Math.pow((Constants.Targeting.kFieldGoalHeightFromGround-Constants.Targeting.kLimelightHeightFromGround), 2))));
        String stringZ = df.format(z);
        return Double.parseDouble(stringZ);
        
    }

    // Returns hypotenuse distance to target
    public static double getTD() {
        return Robot.limelight.getDistance();
    }
    
    // Calculate optimal angle
    public static double calculateAngle(double z) {
        //double a = (10 - ((8 + (2/3))-(12.4/12)))/((15/17) * Math.pow(z, 2));
        return 0.0;
    }

    // Calculate Variable Velocity
    public static double calculateVelocity(double z, double angle, double height) {
        //height is height of the goal - height of shooter shooter
        double x = Constants.Targeting.kFieldGoalHeightFromGround-Constants.Targeting.kLimelightHeightFromGround;
        double velocityMPS = Math.sqrt((4.9 * Math.pow(z, 2)) / ((Math.pow(Math.cos(angle), 2) * Math.tan(angle) * z) - (x * Math.pow(Math.cos(angle), 2))));
        
        SmartDashboard.putNumber("Velocity M/S", velocityMPS);
        double velocityRPM = velocityMPS * 60 / (Constants.Shooter.flywheelRadius * (2*Math.PI));
        
        SmartDashboard.putNumber("Velocity RPM", velocityRPM);
        return velocityRPM;
    }
}
