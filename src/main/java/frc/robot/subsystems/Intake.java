package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;


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
            this.m_intakeMotor.set(1.0);
            break;
        case OFF:
            // Off
            this.m_intakeMotor.set(0);
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
>>>>>>> Stashed changes
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
private DigitalInput m_sensorOne = new DigitalInput(Constants.Intake.kIntakeSensor);
public void toggleFeeder() {
        Robot.Intake.addInfo("ToggleIntake", "Intake Toggle");
        if (Robot.Intake.getIntakeMotorState() == IntakeMotorState.OFF) {
            Robot.Intake.setIntakeMotorState(IntakeMotorState.ON);
        } else {
            Robot.Intake.setIntakeMotorState(IntakeMotorState.OFF);
            }


}
public boolean getSensorOneState() {
        return !m_sensorOne.get();
    }
}