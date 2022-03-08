package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  public CANSparkMax left1  = new CANSparkMax(Constants.DriveTrain.DriveTrainMotorLeft1, MotorType.kBrushless);
  public CANSparkMax right1 = new CANSparkMax(Constants.DriveTrain.DriveTrainMotorRight1, MotorType.kBrushless);
  public CANSparkMax left2  = new CANSparkMax(Constants.DriveTrain.DriveTrainMotorLeft2, MotorType.kBrushless);
  public CANSparkMax right2 = new CANSparkMax(Constants.DriveTrain.DriveTrainMotorRight2, MotorType.kBrushless);

  public enum DriveCoastBrake {
    COAST,
    BRAKE
  }

  private DifferentialDrive differentialDrive;

  public DriveCoastBrake driveMode;

  public DriveTrain(Joystick joy) {
    // Create a new drivetrain with primary motors
    this.differentialDrive = new DifferentialDrive(this.left1, this.right1);

    // Reset all of the motors to default settings
    this.left1.restoreFactoryDefaults();
    this.left2.restoreFactoryDefaults();
    this.right1.restoreFactoryDefaults();
    this.right2.restoreFactoryDefaults();

    // Make the secondary motors follow the actions of their primary
    this.left2.follow(left1);
    this.right2.follow(right1);

    // Set the drivetrain to brake mode
    this.setCoastBrakeMode(DriveCoastBrake.BRAKE);

    this.left1.setSmartCurrentLimit(39);
    this.left2.setSmartCurrentLimit(39);
    this.right1.setSmartCurrentLimit(39);
    this.right2.setSmartCurrentLimit(39);
  }

  public void setCoastBrakeMode(DriveCoastBrake mode) {
    this.driveMode = mode;
    CANSparkMax.IdleMode idleMode = mode == DriveCoastBrake.BRAKE ? CANSparkMax.IdleMode.kBrake : CANSparkMax.IdleMode.kCoast;
    this.left1.setIdleMode(idleMode);
    this.left2.setIdleMode(idleMode);
    this.right1.setIdleMode(idleMode);
    this.right2.setIdleMode(idleMode);
  }

  public void drive(double drive, double steer, boolean turnInPlace) {
    this.differentialDrive.curvatureDrive(drive, steer, turnInPlace);
  }

  public DriveCoastBrake getCurrentDrive() {
    return this.driveMode;
  }

  public DifferentialDrive getDifferentialDrive() {
    return this.differentialDrive;
  }

  public double getActiveLeftSpeed() {
    return this.left1.getEncoder().getVelocity();
  }
  
  public double getActiveRightSpeed() {
    return this.right1.getEncoder().getVelocity();
  }

  @Override
  public void periodic() {}
}
