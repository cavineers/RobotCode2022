package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.commands.auto.ToggleAutonomous;
import com.revrobotics.CANSparkMax;



public class AutoMovement extends CommandBase{

 public enum AutoMovementState {
     ON,
     FORWARD,
     REVERSE,
     ROTATE,
     OFF;

    public void set(double d) {
    }
 }

private AutoMovementState m_AutoMovementState;

 public void setAutoMovementState(AutoMovementState state) {
    // set the current state
    this.m_AutoMovementState = state;
    
    // set autonomous state
    switch (state) {
        case ON:
            // On
            this.m_AutoMovementState.set(Constants.Auto.AutoMovementSpeed);
            break;
        case FORWARD:
            //forward
            this.m_AutoMovementState.set(Constants.Auto.AutoMovementSpeed);
            break;
        case OFF:
            // Off
            this.m_AutoMovementState.set(0.0);
            break;
        case REVERSE:
            // Reversed
            this.m_AutoMovementState.set(Constants.Auto.AutoMovementSpeedrev);
            break;
        default:
            this.setAutoMovementState(AutoMovementState.OFF);
    }
}

/**
 * Get the current AutoMovement state.
 * @return AutoMovement state
 */
public AutoMovementState getAutoMovementState() {
    return this.m_AutoMovementState;
}
}

