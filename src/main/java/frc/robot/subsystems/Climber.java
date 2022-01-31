package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Climber extends SubsystemBase {

  private CANSparkMax m_shooterMotor = new CANSparkMax(Constants.Climber.ClimberMotor, MotorType.kBrushless);

  private CANSparkMax climberMotor;
  public enum ClimberMotorStatus {
    ON,
    OFF
  }
  
    /** Creates a new ExampleSubsystem. */
    public Climber() {}
    
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}
