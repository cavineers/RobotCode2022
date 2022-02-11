package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Climber extends SubsystemBase {
  //! Variables

  private CANSparkMax m_climberElevatorOne = new CANSparkMax(Constants.Climber.ClimberElevMotorOne, MotorType.kBrushless);
  private CANSparkMax m_climberElevatorTwo = new CANSparkMax(Constants.Climber.ClimberElevMotorTwo, MotorType.kBrushless);

  private ClimberMotorState m_motorElevState = ClimberMotorState.OFF;

  private CANSparkMax m_climberAngleOne = new CANSparkMax(Constants.Climber.ClimberAngleMotorOne, MotorType.kBrushless);
  private CANSparkMax m_climberAngleTwo = new CANSparkMax(Constants.Climber.ClimberAngleMotorTwo, MotorType.kBrushless);
  
  private ClimberMotorState m_motorAngleState = ClimberMotorState.OFF;

  private DigitalInput m_elevatorLimitSwtich = new DigitalInput(Constants.DIO.ElevatorSwitch);
  private DigitalInput m_angleLimitSwitch = new DigitalInput(Constants.DIO.ClimberAngleSwitch);


  public enum ClimberMotorState {
    ON,
    OFF,
    REVERSED
  }

  //! Constructor

  public Climber() {
    // Set motors into break mode and invert the movement
    this.m_climberElevatorOne.setIdleMode(IdleMode.kBrake);
    this.m_climberElevatorTwo.setIdleMode(IdleMode.kBrake);
    this.m_climberElevatorOne.setInverted(true);
  
    this.m_climberElevatorTwo.follow(this.m_climberElevatorOne, true);
    this.m_climberAngleTwo.follow(this.m_climberAngleOne);
  }

  //! Elevator

  public CANSparkMax getElevatorMotor() {
    return this.m_climberElevatorOne;
  }

  public void setElevMotorState(ClimberMotorState state) {
    this.m_motorElevState = state;

    // Switch motor to new state
    switch (state) {
      case ON:
        this.m_climberElevatorOne.set(Constants.Climber.ElevatorSpeed);
        break;
      case OFF:
        this.m_climberElevatorOne.set(0.0);
        break;
      case REVERSED:
        this.m_climberElevatorOne.set(Constants.Climber.ElevatorSpeedRev);
        break;
      default:
        this.setElevMotorState(ClimberMotorState.OFF);
    }
  }

  public ClimberMotorState getElevState() {
    return this.m_motorElevState;
  }

  public double getElevatorPosition() {
    return this.m_climberElevatorOne.getEncoder().getPosition();
  }

  public void setElevatorMotorPosition(double position) {
    this.m_climberAngleOne.getEncoder().setPosition(position);
  }

  //! Angle Adjustment

  public CANSparkMax getAngleMotor() {
    return this.m_climberAngleOne;
  }

  public void setAngleMotorState(ClimberMotorState state) {
    this.m_motorAngleState = state;

    // Switch motor to new state
    switch (state) {
      case ON:
        this.m_climberAngleOne.set(Constants.Climber.AngleSpeed);
        break;
      case OFF:
        this.m_climberAngleOne.set(0.0);
        break;
      case REVERSED:
        this.m_climberAngleOne.set(Constants.Climber.AngleSpeedRev);
        break;
      default:
        this.setAngleMotorState(ClimberMotorState.OFF);
    }
  }

  public ClimberMotorState getAngleMotorState() {
    return this.m_motorAngleState;
  }

  public double getAngleMotorPosition() {
    return this.m_climberAngleOne.getEncoder().getPosition();
  }

  public void setAngleMotorPosition(double position) {
    this.m_climberAngleOne.getEncoder().setPosition(position);
  }

  //! Sensors

  public boolean getElevatorLimitSwitch() {
    return !this.m_elevatorLimitSwtich.get();
  }

  public boolean getAngleLimitSwitch() {
    return !this.m_angleLimitSwitch.get();
  }
}