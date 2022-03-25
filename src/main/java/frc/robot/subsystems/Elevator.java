package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Elevator extends SubsystemBase {
    private CANSparkMax m_climberElevatorRight = new CANSparkMax(Constants.Climber.ClimberElevMotorRight, MotorType.kBrushless);
    private CANSparkMax m_climberElevatorLeft = new CANSparkMax(Constants.Climber.ClimberElevMotorLeft, MotorType.kBrushless);

    private DigitalInput m_elevatorLimitSwtichRight = new DigitalInput(Constants.DIO.ElevatorSwitchRight);
    private DigitalInput m_elevatorLimitSwtichLeft = new DigitalInput(Constants.DIO.ElevatorSwitchLeft);

    public Elevator() {
        this.m_climberElevatorRight.setIdleMode(IdleMode.kBrake);
        this.m_climberElevatorLeft.setIdleMode(IdleMode.kBrake);

        // this.m_climberElevatorTwo.follow(this.m_climberElevatorOne, true);

        this.m_climberElevatorRight.setSmartCurrentLimit(39);
        this.m_climberElevatorLeft.setSmartCurrentLimit(39);
    }


    public CANSparkMax getElevatorRightMotor() {
        return this.m_climberElevatorRight;
    }

    public CANSparkMax getElevatorLeftMotor() {
        return this.m_climberElevatorLeft;
    }

    public double getElevatorRightPosition() {
        return -this.m_climberElevatorRight.getEncoder().getPosition();
    }

    public void setElevatorRightMotorPosition(double position) {
        this.m_climberElevatorRight.getEncoder().setPosition(position);
    }

    public double getElevatorLeftPosition() {
        return -this.m_climberElevatorLeft.getEncoder().getPosition();
    }

    public void setElevatorLeftMotorPosition(double position) {
        this.m_climberElevatorLeft.getEncoder().setPosition(position);
    }

    public boolean getRightElevatorSwitch() {
        return this.m_elevatorLimitSwtichRight.get();
    }
    
      public boolean getLeftElevatorSwitch() {
        return this.m_elevatorLimitSwtichLeft.get();
    }
}
