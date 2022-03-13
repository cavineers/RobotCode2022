package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Intake extends SubsystemBase {
      
    public enum IntakeMotorState {
        ON,
        OFF,
        REVERSED
    }

    public enum IntakeDropMotorState {
        ON,
        OFF,
        REVERSED
    }


    public CANSparkMax m_intakeMotor = new CANSparkMax(Constants.Intake.IntakeID, MotorType.kBrushless);

    public CANSparkMax m_intakeDropMotor = new CANSparkMax(Constants.Intake.IntakeDropID, MotorType.kBrushless);

    public IntakeDropMotorState m_dropMotorState = IntakeDropMotorState.OFF;

    public IntakeMotorState m_intakeMotorState = IntakeMotorState.OFF;

    private DigitalInput m_sensorOne = new DigitalInput(Constants.DIO.IntakeSensor);

    /**
     * Intake constructor.
     */
    public Intake() {
        this.setMotorState(IntakeMotorState.OFF);
        this.m_intakeMotor.setInverted(true);
        this.m_intakeDropMotor.setIdleMode(IdleMode.kBrake);
    }

    /**
     * Set the desired intake state.
     * @param state wanted intake state
     */
    public void setMotorState(IntakeMotorState state) {
        // set the current state
        this.m_intakeMotorState = state;
        
        // set motor state
        switch (state) {
            case ON:
                // On
                this.m_intakeMotor.set(Constants.Intake.IntakeSpeed);
                break;
            case OFF:
                // Off
                this.m_intakeMotor.set(0.0);
                break;
            case REVERSED:
                // Reversed
                this.m_intakeMotor.set(Constants.Intake.IntakeSpeedRev);
                break;
            default:
                this.setMotorState(IntakeMotorState.OFF);
        }
    }

    /**
     * Get the current intake state.
     * @return intake state
     */
    public IntakeMotorState getIntakeMotorState() {
        return this.m_intakeMotorState;
    }

    /**
     * Set the desired intake state.
     * @param state wanted intake state
     */
    public void setDropMotorState(IntakeDropMotorState state) {
        // set the current drop motor state
        this.m_dropMotorState = state;
        
        // set drop motor state
        switch (state) {
            case ON:
                // On
                this.m_intakeDropMotor.set(Constants.Intake.DropSpeed);
                break;
            case OFF:
                // Off
                this.m_intakeDropMotor.set(0);
                break;
            case REVERSED:
                // Reversed
                this.m_intakeDropMotor.set(Constants.Intake.LiftSpeed);
                break;
        }
    }

    /**
     * Get the current intake state.
     * @return intake state
     */
    public IntakeDropMotorState getDropMotorState() {
        // return the current motor state
        return this.m_dropMotorState;
    }

    public CANSparkMax getDropMotor() {
        // return the current motor state
        return this.m_intakeDropMotor;
    }

    // Tell when ball is properly in the intake.
    public boolean getSensorOneState() {
        return !m_sensorOne.get();
    }

    @Override
    public void periodic() {}
}