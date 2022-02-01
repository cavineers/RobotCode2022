package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Climber extends SubsystemBase {

  private CANSparkMax m_climberElevatorOne = new CANSparkMax(Constants.Climber.ClimberMotor, MotorType.kBrushless);
  
  private CANSparkMax m_climberElevatorTwo = new CANSparkMax(Constants.Climber.ClimberMotorTwo, MotorType.kBrushless);

  private CANSparkMax m_climberAngleOne = new CANSparkMax(Constants.Climber.ClimberMotorThree, MotorType.kBrushless);
  
  private CANSparkMax m_climberAngleTwo = new CANSparkMax(Constants.Climber.ClimberMotorFour, MotorType.kBrushless);
  
  public enum ClimberMotorStatus {
    ON,
    OFF
  }

  public Climber() {}
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
