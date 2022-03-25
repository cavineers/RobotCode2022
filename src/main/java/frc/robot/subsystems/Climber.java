package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.DigitalInput;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class Climber extends SubsystemBase {
  //! Variables
  private CANSparkMax m_climberAngleRight = new CANSparkMax(Constants.Climber.ClimberAngleMotorRight, MotorType.kBrushless);
  private CANSparkMax m_climberAngleLeft = new CANSparkMax(Constants.Climber.ClimberAngleMotorLeft, MotorType.kBrushless);

  // Sensors
  private DigitalInput m_angleLimitSwitchRight = new DigitalInput(Constants.DIO.ClimberAngleSwitchRight);
  private DigitalInput m_angleLimitSwitchLeft = new DigitalInput(Constants.DIO.ClimberAngleSwitchLeft);
  
  //! Constructor

  public Climber() {
    // Set all motors to run in brake mode
    this.m_climberAngleRight.setIdleMode(IdleMode.kBrake);
    this.m_climberAngleLeft.setIdleMode(IdleMode.kBrake);

    // Set amps on secondary angles to follow primary angles
    // this.m_climberAngleLeft.follow(this.m_climberAngleRight, true);
    this.m_climberAngleLeft.setInverted(true);
  
    this.m_climberAngleRight.setSmartCurrentLimit(38);
    this.m_climberAngleLeft.setSmartCurrentLimit(38);
  }

  public CANSparkMax getAngleMotorRight() {
    return this.m_climberAngleRight;
  }

  public CANSparkMax getAngleMotorLeft() {
    return this.m_climberAngleLeft;
  }

  public double getAngleMotorPositionRight() {
    return this.m_climberAngleRight.getEncoder().getPosition();
  }

  public double getAngleMotorPositionLeft() {
    return this.m_climberAngleLeft.getEncoder().getPosition();
  }

  public void setAngleMotorPosition(double position) {
    this.m_climberAngleRight.getEncoder().setPosition(position);
  }

  public void setAngleMotorPositionLeft(double position) {
    this.m_climberAngleLeft.getEncoder().setPosition(position);
  }

  //! Sensors

  public boolean getRightAngleSwitch() {
    return this.m_angleLimitSwitchRight.get();
  }

  public boolean getLeftAngleSwitch() {
    return this.m_angleLimitSwitchLeft.get();
  }
}