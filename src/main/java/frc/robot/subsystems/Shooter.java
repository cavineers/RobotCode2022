package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax.IdleMode;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;

public class Shooter extends SubsystemBase {
    
    public enum ShooterStatus {
      ENABLED,
      DISABLED
    }

    private CANSparkMax m_shooterMotor = new CANSparkMax(Constants.Shooter.ShooterMotor, MotorType.kBrushless);

    private CANSparkMax m_shooterAngle = new CANSparkMax(Constants.Shooter.ShooterAngle, MotorType.kBrushless);

    private CANSparkMax m_shooterFeeder = new CANSparkMax(Constants.Shooter.ShooterFeeder, MotorType.kBrushless);

    // Infrared Sensor
    private DigitalInput m_sensorBall = new DigitalInput(Constants.Dio.kBallSensor1);

    public boolean getSensorBallState() {
      return !m_sensorBall.get();
    }
    
    private int currentSetpoint;

    //Enum for shooter angle
    public enum ShooterAngle {
      HIGH,
      LOW,
      MEDIUM
    }

    // Constant shooting angles
    public void turnToAngle(ShooterAngle angle) {
        switch (angle) {
            case LOW:
                this.currentSetpoint = 0;
                break;
            case MEDIUM:
                this.currentSetpoint = 0;
                break;
            case HIGH:
                this.currentSetpoint = 0;
                break;
                 }
        }

    // See the shooter to disabled by default
    private ShooterStatus m_shooterState = ShooterStatus.DISABLED;

    // Shooter systems setpoint speed (setpoint in RPM)
    private double m_speed = 0;

    // Shooter motor encoder
    private RelativeEncoder m_shootEncoder = m_shooterMotor.getEncoder();

    // Create new PID controller
    private SparkMaxPIDController m_pidController = m_shooterMotor.getPIDController();

    public Shooter() {
      this.m_shooterMotor.restoreFactoryDefaults();

      // Sets motor to coast mode
      this.m_shooterMotor.setIdleMode(IdleMode.kCoast);

      // Sets 39 amp limit on motor
      this.m_shooterMotor.setSmartCurrentLimit(39);

      // TODO: Add PID parameters here.
    }
  
    public void enableShooter() {
      this.m_shooterState = ShooterStatus.ENABLED;

    }

    public void disableShooter() {
      this.m_shooterState = ShooterStatus.DISABLED;
      this.setSpeed(0.0);
    }

    public ShooterStatus getCurrentState() {
      return this.m_shooterState;
    }

    // Sets the shooter set point speed (in RPM)
    public void setSpeed(double speed) {
      this.m_speed = speed;
    }

    public double getCurrentSpeedSetpoint() {
      return this.m_speed;
    }

    // Returns true of the shooter motor is at it's target setpoint
    public boolean atSetpoint() {
      return (Math.abs(this.m_speed - Math.abs(this.m_shootEncoder.getVelocity())) < 120);
    }

    public void aim(){
      
    }

    @Override
    public void periodic() {
      // TODO Add PID values

      this.m_pidController.setReference(this.m_speed, CANSparkMax.ControlType.kVelocity);

      SmartDashboard.putNumber("Shooter Setpoint Speed", this.m_speed);
      SmartDashboard.putNumber("Shooter Actual Speed", this.m_shootEncoder.getVelocity());
      SmartDashboard.putBoolean("Infrared Ball Sensor Value", this.getSensorBallState());

    }
}

