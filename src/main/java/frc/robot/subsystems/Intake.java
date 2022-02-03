package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Intake extends SubsystemBase {
      
    public enum IntakeMotorState {
      ON,
      OFF,
      REVERSED
    }

    // 
    public CANSparkMax m_intakeMotor = new CANSparkMax(Constants.Intake.kIntakeID, null);

    // Current intake mode
    public IntakeMotorState m_currentMode = IntakeMotorState.OFF;

    private DigitalInput m_sensorOne = new DigitalInput(Constants.Intake.kIntakeSensor);

    /**
     * Intake constructor.
     */
    public Intake() {
        this.setMotorState(IntakeMotorState.OFF);
    }

    /**
     * Set the desired intake state.
     * @param state wanted intake state
     */
    public void setMotorState(IntakeMotorState state) {
        // set the current state
        this.m_currentMode = state;
        

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
                this.m_intakeMotor.set(-1.0);

                break;
            default:
                this.setMotorState(IntakeMotorState.OFF);
        }
    }

    /**
     * Get the current intake state.
     * @return intake state
     */
    public IntakeMotorState getMotorTwoState() {
        // return the current motor state
        return this.m_currentMode;
    }
    public CANSparkMax m_intakeMotorTwo = new CANSparkMax(Constants.Intake.kIntakeID, null);

    // Current intake mode
    public IntakeMotorState m_currentState = IntakeMotorState.OFF;

    /**
     * Intake constructor.
     */
    public void DropIntake() {
        this.setMotorTwoState(IntakeMotorState.OFF);
    }

    /**
     * Set the desired intake state.
     * @param state wanted intake state
     */
    public void setMotorTwoState(IntakeMotorState state) {
        // set the current state
        this.m_currentMode = state;
        

        // set motor state
        switch (state) {
            case ON:
                // On
                this.m_intakeMotor.set(.50);
                break;
            case OFF:
                // Off
                this.m_intakeMotor.set(0);
                break;
            case REVERSED:
                // Reversed
                this.m_intakeMotor.set(-0.50);
                break;
            default:
                this.setMotorState(IntakeMotorState.OFF);
        }
    }

    /**
     * Get the current intake state.
     * @return intake state
     */
    public IntakeMotorState getMotorState() {
        // return the current motor state
        return this.m_currentMode;
        
    }

    public boolean getSensorOneState() {
            return !m_sensorOne.get();
    }
}