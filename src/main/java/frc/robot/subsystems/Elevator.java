package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
    private CANSparkMax m_climberElevatorOne = new CANSparkMax(Constants.Climber.ClimberElevMotorOne, MotorType.kBrushless);
    private CANSparkMax m_climberElevatorTwo = new CANSparkMax(Constants.Climber.ClimberElevMotorTwo, MotorType.kBrushless);
  
    // Climber Motor States
    private ElevatorMotorState m_motorElevState = ElevatorMotorState.OFF;

    private DigitalInput m_elevatorLimitSwtichRight = new DigitalInput(Constants.DIO.ElevatorSwitchRight);
    private DigitalInput m_elevatorLimitSwtichLeft = new DigitalInput(Constants.DIO.ElevatorSwitchLeft);
  
    public enum ElevatorMotorState {
        ON,
        OFF,
        REVERSED
    }  

    public Elevator() {
        this.m_climberElevatorOne.setIdleMode(IdleMode.kBrake);
        this.m_climberElevatorTwo.setIdleMode(IdleMode.kBrake);

        this.m_climberElevatorTwo.follow(this.m_climberElevatorOne, true);

        this.m_climberElevatorOne.setSmartCurrentLimit(39);
        this.m_climberElevatorTwo.setSmartCurrentLimit(39);
    }


    public CANSparkMax getElevatorMotor() {
        return this.m_climberElevatorOne;
    }

    public void setElevMotorState(ElevatorMotorState state) {
        this.m_motorElevState = state;

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
            this.setElevMotorState(ElevatorMotorState.OFF);
        }
    }

    public ElevatorMotorState getElevState() {
        return this.m_motorElevState;
    }

    public double getElevatorPosition() {
        return -this.m_climberElevatorOne.getEncoder().getPosition();
    }

    public void setElevatorMotorPosition(double position) {
        this.m_climberElevatorOne.getEncoder().setPosition(position);
    }

    public boolean getRightElevatorSwitch() {
        return this.m_elevatorLimitSwtichRight.get();
    }
    
      public boolean getLeftElevatorSwitch() {
        return this.m_elevatorLimitSwtichLeft.get();
    }
}
