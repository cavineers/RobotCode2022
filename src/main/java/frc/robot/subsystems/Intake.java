package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;

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
public IntakeMotorState getMotorState() {
    // return the current motor state
    return this.m_currentMode;
}
}
