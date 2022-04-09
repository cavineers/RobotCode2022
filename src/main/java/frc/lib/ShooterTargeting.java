package frc.lib;

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

    public static double getTx() {
        return Robot.limelight.getHorizontalOffset();
    }

    public static double getTy() {
        return Robot.limelight.getVerticalOffset();
    }
    
    // Calculate optimal angle
    public static double calculateAngle(double z) {
        //double a = (10 - ((8 + (2/3))-(12.4/12)))/((15/17) * Math.pow(z, 2));
        return 0.0;
    }

    // Calculate Variable Velocity
    public static double calculateVelocity(double z, double angle) {
        //height is height of the goal - height of shooter shooter
        double x = Constants.Targeting.kFieldGoalHeightFromGround-Constants.Targeting.kLimelightHeightFromGround;
        double velocityMPS = Math.sqrt((4.9 * Math.pow(z, 2)) / ((Math.pow(Math.cos(angle), 2) * Math.tan(angle) * z) - (x * Math.pow(Math.cos(angle), 2))));

        double velocityRPM;
        if (z <= 3) {
            velocityRPM = (velocityMPS * 60 / (Constants.Shooter.flywheelRadius * (2*Math.PI))) * 1.53; // 1.56 
        } else {
            velocityRPM = (velocityMPS * 60 / (Constants.Shooter.flywheelRadius * (2*Math.PI))) * 1.45; // 1.55
        }
        return velocityRPM;
    }
}
