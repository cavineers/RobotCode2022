package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {

    // Reference
    // neo - full sized neo motor with SparkMax
    // minineo - mini neo motor with SparkMax

    public static class CANIds {
        public static int DriveTrainMotorLeft1  = 1;  // Left 1 (neo)
        public static int DriveTrainMotorRight1 = 3;  // Left 2 (neo)
        public static int DriveTrainMotorLeft2  = 2;  // Right 1 (neo)
        public static int DriveTrainMotorRight2 = 4;  // Right 2 (neo)
        public static int ClimberElevMotorRight   = 5;  // ClimberElevatorMotor (neo)
        public static int ClimberElevMotorLeft   = 6;  // ClimberElevatorMotorTwo (neo)
        public static int ClimberAngleMotorRight = 7;  // ClimberAngleMotor (minineo)
        public static int ClimberAngleMotorLeft  = 8;  // ClimberAngleMotor (minineo)
        public static int IntakeMotor           = 9;  // IntakeMotor (neo)
        public static int IntakeMotorDrop       = 10;  // IntakeDrop (minineo)
        public static int ShooterAngle          = 11;  // ShooterAngle(neo)
        public static int ShooterFeeder         = 12;  // FeederWheel (minineo)
        public static int ShooterMotor          = 13;  // FlyWheel (neo)
    }

    public static class DriveTrain {
        public static int DriveTrainMotorLeft1 = CANIds.DriveTrainMotorLeft1;
        public static int DriveTrainMotorRight1 = CANIds.DriveTrainMotorRight1;
        public static int DriveTrainMotorLeft2 = CANIds.DriveTrainMotorLeft2;
        public static int DriveTrainMotorRight2 = CANIds.DriveTrainMotorRight2;
    }

    public static class Targeting {
        public static double kFieldGoalHeightFromGroundIn = 103; // Math constants 104
        public static double kLimelightHeightFromGroundIn = 23.6; // vertical distance from limelight to ground
    
        public static double kFieldGoalHeightFromGround = Units.inchesToMeters(kFieldGoalHeightFromGroundIn); // Math constants
        public static double kLimelightHeightFromGround = Units.inchesToMeters(kLimelightHeightFromGroundIn); // vertical distance from limelight to ground
        public static double kLimelightMountingAngle    = 32.8;
    }

    public static class Shooter {
        public static double shooterVelocityConstant = 0;
        public static int ShooterMotor = CANIds.ShooterMotor;
        public static int ShooterAngle = CANIds.ShooterAngle;
        public static int ShooterFeeder = CANIds.ShooterFeeder;

        // Angles
        public static double shooterAngleLow = 67;
        public static double shooterAngleMedium = 69;
        public static double shooterAngleHigh = 78;

        public static double shooterHeight = Units.inchesToMeters(38); // 12.4

        public static double degreesPerRevolution = 3.327; // 1.625 on 100:1 with 2:1 increase
        public static double flywheelRotationsPerRevolution = .2;

        public static double flywheelRadius = .0508; //checked with shooter, 2 in = .0508 m

        public static double kP = 0.00072; // 0.0008; Proportional
        public static double kI = 0.0; // 0.0; Integral
        public static double kD = 0.0204; // 0.009; Derivative
        public static double kF = 0.000204; // 0.000206; Feed Forward

        public static double angleSpeed = 0.25; // Angle motor speed 0-1 bounds 0.1
        public static double feederSpeed = 1.00; // Angle motor speed 0-1 bounds
        
        // Feederwheel distances - Right to wall => 1.68 inches
        // Left to wall => 1.51 inches
        // Distance between => 5.07 inches
    }

    public static class Intake {
        public static int IntakeID = CANIds.IntakeMotor;
        public static int IntakeDropID = CANIds.IntakeMotorDrop;

        public static double IntakeSpeed = 0.7; // Intake motor speed (-1.0 -- 1.0)
        public static double IntakeSpeedRev = -0.65; // Intake reverse speed

        public static double DropSpeed = -0.2;
        public static double LiftSpeed = 0.2;

        public static double RevolutionsToLower = -31;
    }

    public static class Climber {
        public static int ClimberElevMotorRight = CANIds.ClimberElevMotorRight;
        public static int ClimberElevMotorLeft = CANIds.ClimberElevMotorLeft;
        public static int ClimberAngleMotorRight = CANIds.ClimberAngleMotorRight;
        public static int ClimberAngleMotorLeft = CANIds.ClimberAngleMotorLeft;

        public static double ElevatorSpeed = -0.1;
        public static double ElevatorSpeedRev = 0.1;
        public static double ElevatorMetersPerRevolution = Math.PI / 367.3221; //pi/9.33, then converted into meters

        public static double AngleSpeed = -0.1;
        public static double AngleSpeedRev = 0.1;
        public static double LeftAngleConstant = 0.011; // 0.012

        public static double MaxElevatorRevolutions = 89;
        public static double MaxElevatorRevolutionsInital = 58;
        public static double MaxSwivelRevolutions = 84;
    }
    public static class DIO {
        public static int IntakeSensor = 0;
        public static int ElevatorSwitchRight = 1;
        public static int ElevatorSwitchLeft = 6;
        public static int ClimberAngleSwitchRight = 2;
        public static int ClimberAngleSwitchLeft = 4;
        public static int BallSensorShooter = 9;
    }
}
