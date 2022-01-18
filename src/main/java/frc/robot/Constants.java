package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 */
public final class Constants {

    // These will need to change...
    public static class CANIds {
        public static int DriveTrainMotor1       = 1;  // Left 1 (neo)
        public static int DriveTrainMotor3       = 2;  // Left 2 (neo)
        public static int DriveTrainMotor2       = 3;  // Right 1 (neo)
        public static int DriveTrainMotor4       = 4;  // Right 2 (neo)
    }

    public static class DriveTrain {
        public static int DriveTrainMotor1 = CANIds.DriveTrainMotor1;
        public static int DriveTrainMotor2 = CANIds.DriveTrainMotor2;
        public static int DriveTrainMotor3 = CANIds.DriveTrainMotor3;
        public static int DriveTrainMotor4 = CANIds.DriveTrainMotor4;
    }
}
