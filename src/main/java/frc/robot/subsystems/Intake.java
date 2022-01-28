package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake extends SubsystemBase {
    
  private CANSparkMax m_lowerBrushMotor = new CANSparkMax(Constants.Intake.IntakeLowerBrush, MotorType.kBrushless);

  public enum IntakeMotorState {
    ON,
    OFF,
    REVERSED
  }

  /** Creates a new Intake. */
  public Intake() {}
}
