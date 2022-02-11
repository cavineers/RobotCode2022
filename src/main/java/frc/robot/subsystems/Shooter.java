package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax.IdleMode;

import frc.lib.ShooterTargeting;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;

public class Shooter extends SubsystemBase {
    
    private boolean isPositioned = false;

    public enum ShooterStatus {
      ENABLED,
      DISABLED
    }

    private CANSparkMax m_shooterMotor = new CANSparkMax(Constants.Shooter.ShooterMotor, MotorType.kBrushless);

    private CANSparkMax m_shooterAngleMotor = new CANSparkMax(Constants.Shooter.ShooterAngle, MotorType.kBrushless);

    private CANSparkMax m_shooterFeeder = new CANSparkMax(Constants.Shooter.ShooterFeeder, MotorType.kBrushless);

    //When the button is pressed, the shooter will turn to the correct angle
    //Then the flywheel will go to the correct speed
    //Then the feeder will activate


    public Shooter() {
      this.m_shooterMotor.restoreFactoryDefaults();

      // Sets motor to coast mode
      this.m_shooterMotor.setIdleMode(IdleMode.kCoast);

      // Sets 39 amp limit on motor
      this.m_shooterMotor.setSmartCurrentLimit(39);

    }
    public boolean getSensorBallState() {
      return !m_sensorBall.get();
    }
    
    private int currentAngleSetpoint;

    // Infrared Sensor
    private DigitalInput m_sensorBall = new DigitalInput(Constants.Dio.kBallSensor1);

    // See the shooter to disabled by default
    private ShooterStatus m_shooterState = ShooterStatus.DISABLED;
 
    // Shooter systems setpoint speed (setpoint in RPM)
    private double m_speedSetpoint = 0;
 
    // Shooter motor encoder
    private RelativeEncoder m_shootEncoder = m_shooterMotor.getEncoder();

    private RelativeEncoder m_angleEncoder = m_shooterAngleMotor.getEncoder();

    private double originalAngleMotorPosition = m_angleEncoder.getPosition();
 
    // Create new PID controller
    private SparkMaxPIDController m_pidController = m_shooterMotor.getPIDController();

    private double lengthZ = ShooterTargeting.findZ();


    //Enum for shooter angle
    public enum ShooterAngle {
      HIGH,
      LOW,
      MEDIUM
    }

    public ShooterAngle setShooterAngle(double z) {
      z = lengthZ;
      if (z >= 6) {
        return ShooterAngle.LOW;
      } else if ((z < 6) && (z > 3)) {
        return ShooterAngle.MEDIUM;
      } else {
        return ShooterAngle.HIGH;
      }
    }

    // Constant shooting angles
    public void turnToAngle(ShooterAngle angle) {
        double currentAngle;
        if (angle == ShooterAngle.LOW) {
          currentAngle = Constants.Shooter.shooterAngleLow;
          this.currentAngleSetpoint = 0;
        } else if (angle == ShooterAngle.MEDIUM) {
          currentAngle = Constants.Shooter.shooterAngleMedium;
          this.currentAngleSetpoint = 0;
        } else if (angle == ShooterAngle.HIGH) {
          currentAngle = Constants.Shooter.shooterAngleHigh;
          this.currentAngleSetpoint = 0;
        }
        
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
      this.m_speedSetpoint = speed;
    }

    public double getCurrentSpeedSetpoint() {
      return this.m_speedSetpoint;
    }

    // Returns true of the shooter motor is at it's target setpoint
    public boolean atSetpoint() {
      return (Math.abs(this.m_speedSetpoint - Math.abs(this.m_shootEncoder.getVelocity())) < 120);
    }

    public boolean atAngle() {
      return this.isPositioned;
    }

    public void togglePosition(boolean position) {
      this.isPositioned = position;
    }


    @Override
    public void periodic() {
      // TODO Add PID values

      this.m_pidController.setReference(this.m_speedSetpoint, CANSparkMax.ControlType.kVelocity);

      SmartDashboard.putNumber("Shooter Setpoint Speed", this.m_speedSetpoint);
      SmartDashboard.putNumber("Shooter Actual Speed", this.m_shootEncoder.getVelocity());
      SmartDashboard.putBoolean("Infrared Ball Sensor Value", this.getSensorBallState());
      SmartDashboard.putNumber("shooter_angle", this.currentAngleSetpoint);

    }
}

