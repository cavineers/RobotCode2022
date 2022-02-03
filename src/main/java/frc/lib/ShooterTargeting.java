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
    public static double calculateAngle() {
        return 0.0;
    }

    // Calculate Variable Velocity
    public static double calculateVelocity(double x) {
        return (6.346 / (Math.sin(Math.atan(2.055 / x))) * Constants.Shooter.shooterVelocityConstant);
    }
}
