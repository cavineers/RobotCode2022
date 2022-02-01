package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Climber extends SubsystemBase {

  private CANSparkMax m_shooterMotor = new CANSparkMax(Constants.Climber.ClimberMotorH1, MotorType.kBrushless);
  
  private CANSparkMax m_shooterMotorTwo = new CANSparkMax(Constants.Climber.ClimberMotorH2, MotorType.kBrushless);

  private CANSparkMax m_shooterMotorThree = new CANSparkMax(Constants.Climber.ClimberMotorV1, MotorType.kBrushless);
  
  private CANSparkMax m_shooterMotorFour = new CANSparkMax(Constants.Climber.ClimberMotorV2, MotorType.kBrushless);
  
  private CANSparkMax climberMotorH1;

  private CANSparkMax climberMotorH2;

  private CANSparkMax climberMotorV1;

  private CANSparkMax climberMotorV2;
  
  public enum ClimberMotorH1Status {
    ON,
    OFF
  }
  
  public enum ClimberMotorH2Status {
    ON,
    OFF
  }
  public enum ClimberMotorV1Status {
    ON,
    OFF
  }
  
  public enum ClimberMotorV2Status {
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
