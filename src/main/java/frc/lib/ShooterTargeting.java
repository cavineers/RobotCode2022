package frc.lib;

import frc.robot.Constants;

public class ShooterTargeting {

    // Calculate Variable Velocity
    public static double calculateVelocity(double x) {
        return (6.346 / (Math.sin(Math.atan(2.055 / x))) * Constants.Shooter.shooterVelocityConstant);
    }
}
