package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
    
  public CANSparkMax left1  = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor1, MotorType.kBrushless);
  public CANSparkMax right1 = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor2, MotorType.kBrushless);
  public CANSparkMax left2  = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor3, MotorType.kBrushless);
  public CANSparkMax right2 = new CANSparkMax(Constants.DriveTrain.DriveTrainMotor4, MotorType.kBrushless);

  public enum DriveCoastBrake {
    COAST,
    BRAKE
  }

  /** Creates a new DriveTrain. */
  public DriveTrain() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
