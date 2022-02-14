package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Intake extends SubsystemBase {
      
    public enum IntakeTopMotorState {
      ON,
      OFF,
      REVERSED
    }

    public enum IntakeBottomMotorState{
        ON,
        OFF,
        REVERSED
    }

    public CANSparkMax m_intakeTopMotor = new CANSparkMax(Constants.Intake.IntakeTopID, MotorType.kBrushless);

    public CANSparkMax m_intakeBottomMotor = new CANSparkMax(Constants.Intake.IntakeBottomID, MotorType.kBrushless);

    public IntakeBottomMotorState m_intakeBottomMotorState = IntakeBottomMotorState.OFF;

    public IntakeTopMotorState m_intakeTopMotorState = IntakeTopMotorState.OFF;

    public DigitalInput m_sensor = new DigitalInput(Constants.DIO.IntakeSensor);
    


    /**
     * Set the desired intake state.
     * @param off wanted intake state
     */
    public void setTopMotorState(IntakeTopMotorState off){
        
        // set motor state
        switch (off) {
            case ON:
                // On
                this.m_intakeTopMotor.set(Constants.Intake.IntakeSpeed);
                break;
            case OFF:
                // Off
                this.m_intakeTopMotor.set(0.0);
                break;
            case REVERSED:
                // Reversed
                this.m_intakeTopMotor.set(Constants.Intake.IntakeSpeedRev);
                break;
            default:
            this.m_intakeTopMotorState = IntakeTopMotorState.OFF;
        }
    }

    /**
     * Get the current intake state.
     * @return intake state
     */
    public IntakeTopMotorState getIntakeTopMotorState() {
        return this.m_intakeTopMotorState;
    }

    /**
     * Set the desired intake state.
     * @param state wanted intake state
     */
    public void setMotorState(IntakeBottomMotorState state) {
        // set the current drop motor state
        this.m_intakeBottomMotorState = state;
        
        // set drop motor state
        switch (state) {
            case ON:
                // On
                this.m_intakeBottomMotor.set(Constants.Intake.DropSpeed);
                break;
            case OFF:
                // Off
                this.m_intakeBottomMotor.set(0);
                break;
            case REVERSED:
                // Reversed
                this.m_intakeBottomMotor.set(Constants.Intake.LiftSpeed);
                break;
            default:
                this.m_intakeBottomMotorState = IntakeBottomMotorState.OFF;
        }
    }



    /**
     * Get the current intake state.
     * @return intake state
     */
    public IntakeBottomMotorState getBottomMotorState() {
        // return the current motor state
        return this.m_intakeBottomMotorState;
    }

    // Tell when ball is properly in the intake.
    public boolean getSensorState() {
        return !m_sensor.get();
    }
}

