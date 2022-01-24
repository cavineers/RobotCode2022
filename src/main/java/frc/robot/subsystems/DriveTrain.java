package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Joystick;
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

  /** Creates a new DriveTrain. */
  public DriveTrain() {}

  /**
   * Set the brake and coast mode
   * @param mode coast / brake
   */
  public void setCoastBrakeMode(DriveCoastBrake mode) {
    this.driveMode = mode;
    CANSparkMax.IdleMode idleMode = mode == DriveCoastBrake.BRAKE ? CANSparkMax.IdleMode.kBrake : CANSparkMax.IdleMode.kCoast;
    this.left1.setIdleMode(idleMode);
    this.left2.setIdleMode(idleMode);
    this.right1.setIdleMode(idleMode);
    this.right2.setIdleMode(idleMode);
  }

  /**
   * Get Current Drive Mode
   * @return CostBrake mode of robot
   */
  public DriveCoastBrake getCurrentDrive() {
    return this.driveMode;
  }

  @Override
  public void periodic() {}
}
