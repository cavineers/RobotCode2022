package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {

    // Refernce
    // neo - full sized neo motor with SparkMax
    // minineo - mini neo motor with SparkMax

    public static class CANIds {
        // TODO Update these values
        public static int DriveTrainMotorLeft1  = 1;  // Left 1 (neo)
        public static int DriveTrainMotorRight1 = 2;  // Left 2 (neo)
        public static int DriveTrainMotorLeft2  = 3;  // Right 1 (neo)
        public static int DriveTrainMotorRight2 = 4;  // Right 2 (neo)
        public static int ShooterMotor          = 5;  // FlyWheel (neo)
        public static int ShooterAngle          = 6;  //         (neo)
    }

    public static class DriveTrain {
        public static int DriveTrainMotorLeft1 = CANIds.DriveTrainMotorLeft1;
        public static int DriveTrainMotorRight1 = CANIds.DriveTrainMotorRight1;
        public static int DriveTrainMotorLeft2 = CANIds.DriveTrainMotorLeft2;
        public static int DriveTrainMotorRight2 = CANIds.DriveTrainMotorRight2;
    }

    public static class Targeting {
        // TODO Update these values
        public static double kFieldGoalHeightFromGround = Units.inchesToMeters(0); // Math constants
        public static double kLimelightHeightFromGround = Units.inchesToMeters(0); // vertical distance from limelight to ground
        public static double kLimelightMountingAngle    = 0; // Angle Mounted on Robot
    }

    public static class Shooter {
        // TODO Update these values
        public static double shooterVelocityConstant = 0;
        public static int ShooterMotor = CANIds.ShooterMotor;
        public static int ShooterAngle = CANIds.ShooterAngle;
    }
}
