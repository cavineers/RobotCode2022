package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Climber extends SubsystemBase {

  private CANSparkMax m_climberElevatorOne = new CANSparkMax(Constants.Climber.ClimberMotor, MotorType.kBrushless);
  
  private CANSparkMax m_climberElevatorTwo = new CANSparkMax(Constants.Climber.ClimberMotorTwo, MotorType.kBrushless);

  private ClimberMotorState m_motorElevState = ClimberMotorState.OFF;

  private CANSparkMax m_climberAngleOne = new CANSparkMax(Constants.Climber.ClimberMotorThree, MotorType.kBrushless);
  
  private CANSparkMax m_climberAngleTwo = new CANSparkMax(Constants.Climber.ClimberMotorFour, MotorType.kBrushless);
  
  private ClimberMotorState m_motorAngleState = ClimberMotorState.OFF;

  public enum ClimberMotorState {
    ON,
    OFF,
    REVERSED
  }

  public Climber() {
    // Set motors into break mode and invert the movement
    this.m_climberElevatorOne.setIdleMode(IdleMode.kBrake);
    this.m_climberElevatorTwo.setIdleMode(IdleMode.kBrake);
    this.m_climberElevatorOne.setInverted(true);
  
    this.m_climberElevatorTwo.follow(this.m_climberElevatorOne, true);
    this.m_climberAngleTwo.follow(this.m_climberAngleOne);
  }

  //! Elevator

  public double getElevatorSpeed() {
    return this.m_climberElevatorOne.getEncoder().getVelocity();
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

  //! Angle Adjustment

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

  public double getAngleMotorSpeed() {
    return this.m_climberAngleOne.getEncoder().getVelocity();
  }
}
