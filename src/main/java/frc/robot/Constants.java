package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {

    public static class CANIds {
        // TODO Update these values
        public static int DriveTrainMotor1       = 1;  // Left 1 (neo)
        public static int DriveTrainMotor3       = 2;  // Left 2 (neo)
        public static int DriveTrainMotor2       = 3;  // Right 1 (neo)
        public static int DriveTrainMotor4       = 4;  // Right 2 (neo)
        public static int ShooterMotor          = 5;
    }

    public static class DriveTrain {
        public static int DriveTrainMotor1 = CANIds.DriveTrainMotor1;
        public static int DriveTrainMotor2 = CANIds.DriveTrainMotor2;
        public static int DriveTrainMotor3 = CANIds.DriveTrainMotor3;
        public static int DriveTrainMotor4 = CANIds.DriveTrainMotor4;
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
    }
}
