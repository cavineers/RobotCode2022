package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Climber extends SubsystemBase {
  //! Variables
  private CANSparkMax m_climberAngleOne = new CANSparkMax(Constants.Climber.ClimberAngleMotorOne, MotorType.kBrushless);
  private CANSparkMax m_climberAngleTwo = new CANSparkMax(Constants.Climber.ClimberAngleMotorTwo, MotorType.kBrushless);
  
  // Angle Motor States
  private ClimberMotorState m_motorAngleState = ClimberMotorState.OFF;

  // Sensors
  private DigitalInput m_angleLimitSwitchRight = new DigitalInput(Constants.DIO.ClimberAngleSwitchRight);
  private DigitalInput m_angleLimitSwitchLeft = new DigitalInput(Constants.DIO.ClimberAngleSwitchLeft);

  public enum ClimberMotorState {
    ON,
    OFF,
    REVERSED
  }

  //! Constructor

  public Climber() {
    // Set all motors to run in brake mode
    this.m_climberAngleOne.setIdleMode(IdleMode.kBrake);
    this.m_climberAngleTwo.setIdleMode(IdleMode.kBrake);

    // Set amps on secondary angles to follow primary angles
    this.m_climberAngleTwo.follow(this.m_climberAngleOne);
  }

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

  public boolean getRightAngleSwitch() {
    return this.m_angleLimitSwitchRight.get();
  }

  public boolean getLeftAngleSwitch() {
    return this.m_angleLimitSwitchLeft.get();
  }
}