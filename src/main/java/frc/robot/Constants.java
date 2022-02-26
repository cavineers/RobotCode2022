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
        public static int ClimberElevMotorOne   = 5;  // CliberElevatorMotor (neo)
        public static int ClimberElevMotorTwo   = 6;  // CliberElevatorMotorTwo (neo)
        public static int ClimberAngleMotorOne  = 7;  // ClimberAngleMotor (minineo)
        public static int ClimberAngleMotorTwo  = 8;  // ClimberAngleMotor (minineo)
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
        // TODO Update these values
        public static double kFieldGoalHeightFromGroundIn = 69; // Math constants 104
        public static double kLimelightHeightFromGroundIn = 37; // vertical distance from limelight to ground
    
        public static double kFieldGoalHeightFromGround = Units.inchesToMeters(kFieldGoalHeightFromGroundIn); // Math constants
        public static double kLimelightHeightFromGround = Units.inchesToMeters(kLimelightHeightFromGroundIn); // vertical distance from limelight to ground
        public static double kLimelightMountingAngle    = 20; // Angle Mounted on Robot
    }

    public static class Shooter {
        // TODO Update these values
        public static double shooterVelocityConstant = 0;
        public static int ShooterMotor = CANIds.ShooterMotor;
        public static int ShooterAngle = CANIds.ShooterAngle;
        public static int ShooterFeeder = CANIds.ShooterFeeder;

        public static double shooterAngleLow = 44;
        public static double shooterAngleMedium = 63;
        public static double shooterAngleHigh = 84;

        public static double shooterHeight = Units.inchesToMeters(38); // 12.4

        //TODO Check values with shooter sub-team
        public static double degreesPerRevolution = 1.8; //27.78 revolutions = 50 degrees, 1:5 for flywheel
        public static double flywheelRotationsPerRevolution = .2;

        public static double flywheelRadius = .0508; //checked with shooter, 2 in = .0508 m

<<<<<<< Updated upstream
        public static double kP = 0.00095; //.00095; Proportional
        public static double kI = 0.0000001; //0.0; Integral
        public static double kD = 0.0095; //0.009; Derivative
        public static double kF = 0.00015; // 0.00015; Feed Forward
=======
        public static double kP = 0.0008; //.00095; Proportional
        public static double kI = 0.0; //0.0; Integral
        public static double kD = 0.000000; //0.009; Derivative
        public static double kF = 0.00017; // 0.00015; Feed Forward
>>>>>>> Stashed changes
    }

    public static class Intake {
        public static int IntakeID = CANIds.IntakeMotor;
        public static int IntakeDropID = CANIds.IntakeMotorDrop;

        public static double IntakeSpeed = 0.1; // Intake motor speed (-1.0 -- 1.0)
        public static double IntakeSpeedRev = -0.1; // Intake reverse speed

        public static double DropSpeed = 0.2;
        public static double LiftSpeed = -0.2;

        public static double RevolutionsToLower = -20;
    }

    public static class Climber {
        public static int ClimberElevMotorOne = CANIds.ClimberElevMotorOne;
        public static int ClimberElevMotorTwo = CANIds.ClimberElevMotorTwo;
        public static int ClimberAngleMotorOne = CANIds.ClimberAngleMotorOne;
        public static int ClimberAngleMotorTwo = CANIds.ClimberAngleMotorTwo;

        public static double ElevatorSpeed = -0.1;
        public static double ElevatorSpeedRev = 0.1;
        public static double ElevatorMetersPerRevolution = Math.PI / 367.3221; //pi/9.33, then converted into meters

        public static double AngleSpeed = -0.1;
        public static double AngleSpeedRev = 0.1;

        public static double MaxElevatorRevolutions = 243.6;
        public static double MaxSwivelRevolutions = 700.0;
    }
    public static class DIO {
        public static int IntakeSensor = 0;
        public static int ElevatorSwitchRight = 1;
        public static int ElevatorSwitchLeft = 2;
        public static int ClimberAngleSwitchRight = 3;
        public static int ClimberAngleSwitchLeft = 4;
        public static int BallSensorShooter = 5;
    }
}
