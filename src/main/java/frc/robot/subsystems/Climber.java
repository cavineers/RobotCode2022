package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Climber extends SubsystemBase {

  private CANSparkMax m_shooterMotor = new CANSparkMax(Constants.Climber.ClimberMotor, MotorType.kBrushless);
  
  private CANSparkMax m_shooterMotorTwo = new CANSparkMax(Constants.Climber.ClimberMotorTwo, MotorType.kBrushless);

  private CANSparkMax m_shooterMotorThree = new CANSparkMax(Constants.Climber.ClimberMotorThree, MotorType.kBrushless);
  
  private CANSparkMax m_shooterMotorFour = new CANSparkMax(Constants.Climber.ClimberMotorFour, MotorType.kBrushless);
  
  private CANSparkMax climberMotor;

  private CANSparkMax climberMotorTwo;

  private CANSparkMax climberMotorThree;

  private CANSparkMax climberMotorFour;
  
  public enum ClimberMotorStatus {
    ON,
    OFF
  }
  
  public enum ClimberMotorTwoStatus {
    ON,
    OFF
  }
  public enum ClimberMotorThreeStatus {
    ON,
    OFF
  }
  
  public enum ClimberMotorFourStatus {
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
