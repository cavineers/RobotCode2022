package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    
    public enum IntakeMotorState {
      ON,
      OFF,
      REVERSED
    }
  
    /** Creates a new Intake. */
    public Intake() {}
}
