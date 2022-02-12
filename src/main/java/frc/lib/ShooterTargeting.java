package frc.lib;

import frc.robot.Constants;
import frc.robot.Robot;

public class ShooterTargeting {

    // -b squared plus c squared = a squared
    // C is the TD from limelight
    // B is the height of the goal - height of limelight
    // A is the distance between limelight and base of goal
    public static double findZ() {
        return (Math.sqrt(-Math.pow((104-Constants.Targeting.kLimelightHeightFromGround), 2) + Math.pow(getTD(), 2)));
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
        //height is height of the shooter
        //return (6.346 / (Math.sin(Math.atan(2.055 / x))) * Constants.Shooter.shooterVelocityConstant);
        return (4.9 * Math.pow(z, 2) / (Math.pow(Math.cos(angle), 2) * Math.tan(angle*z) - (height * Math.pow(Math.cos(angle), 2))));
    }
}
