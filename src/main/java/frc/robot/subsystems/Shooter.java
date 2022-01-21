package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    
    public enum ShooterStatus {
      ENABLED,
      DISABLED
    }

    private CANSparkMax m_shooterMotor = new CANSparkMax(Constants.Shooter.ShooterMotor, MotorType.kBrushless);
  
    // See the shooter to disabled by default
    private ShooterStatus m_shooterState = ShooterStatus.DISABLED;

    public Shooter() {}
  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
}