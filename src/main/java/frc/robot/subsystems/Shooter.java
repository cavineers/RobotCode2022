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
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;

public class Shooter extends SubsystemBase {
    
    private boolean isPositioned = false;

    private CANSparkMax m_shooterMotor = new CANSparkMax(Constants.Shooter.ShooterMotor, MotorType.kBrushless);

    private CANSparkMax m_shooterAngleMotor = new CANSparkMax(Constants.Shooter.ShooterAngle, MotorType.kBrushless);

    private CANSparkMax m_shooterFeeder = new CANSparkMax(Constants.Shooter.ShooterFeeder, MotorType.kBrushless);

    //When the button is pressed, the shooter will turn to the correct angle
    //Then the flywheel will go to the correct speed
    //Then the feeder will activate

    private double currentAngleSetpoint;

    // Infrared Sensor
    private DigitalInput m_sensorBall = new DigitalInput(Constants.DIO.BallSensorShooter);

    // See the shooter to disabled by default
    private ShooterStatus m_shooterState = ShooterStatus.DISABLED;

    private FeederStatus m_feederState = FeederStatus.DISABLED;
 
    // Shooter systems setpoint speed (setpoint in RPM)
    private double m_speedSetpoint = 0;
 
    // Shooter motor encoder
    private RelativeEncoder m_shootEncoder = m_shooterMotor.getEncoder();

    private RelativeEncoder m_angleEncoder = m_shooterAngleMotor.getEncoder();

    private double currentAngleMotorPosition = m_angleEncoder.getPosition();
 
    // Create new PID controller
    //private SparkMaxPIDController m_pidController = m_shooterMotor.getPIDController();
    private PIDController m_pidController = new PIDController(Constants.Shooter.kP, Constants.Shooter.kI, Constants.Shooter.kD);

    private double lengthZ = ShooterTargeting.findZ();

    


    public Shooter() {
      this.m_shooterMotor.restoreFactoryDefaults();

      // Sets motor to coast mode
      this.m_shooterMotor.setIdleMode(IdleMode.kCoast);

      // Sets 39 amp limit on motor
      this.m_shooterMotor.setSmartCurrentLimit(39);

    }

    public enum ShooterStatus {
      ENABLED,
      DISABLED
    }

    public enum FeederStatus {
      ENABLED,
      DISABLED
    }
    
    //Enum for shooter angle
    public enum ShooterAngle {
      HIGH,
      LOW,
      MEDIUM
    }

    public boolean getSensorBallState() {
      return !m_sensorBall.get();
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
          this.currentAngleSetpoint = (currentAngle / Constants.Shooter.degreesPerRotation);
          if (currentAngleMotorPosition < currentAngleSetpoint) {
            this.m_shooterAngleMotor.set(.5);
          } else if (currentAngleMotorPosition > currentAngleSetpoint) {
            this.m_shooterAngleMotor.set(-.5);
          } else if (currentAngleMotorPosition == currentAngleSetpoint) {
            this.m_shooterAngleMotor.set(0);
            isPositioned = true;
          }
        } else if (angle == ShooterAngle.MEDIUM) {
          currentAngle = Constants.Shooter.shooterAngleMedium;
          this.currentAngleSetpoint = (currentAngle / Constants.Shooter.degreesPerRotation);
          if (currentAngleMotorPosition < currentAngleSetpoint) {
            this.m_shooterAngleMotor.set(.5);
          } else if (currentAngleMotorPosition > currentAngleSetpoint) {
            this.m_shooterAngleMotor.set(-.5);
          } else if (currentAngleMotorPosition == currentAngleSetpoint) {
            this.m_shooterAngleMotor.set(0);
            isPositioned = true;
          }
        } else if (angle == ShooterAngle.HIGH) {
          currentAngle = Constants.Shooter.shooterAngleHigh;
          this.currentAngleSetpoint = (currentAngle / Constants.Shooter.degreesPerRotation);
          if (currentAngleMotorPosition < currentAngleSetpoint) {
            this.m_shooterAngleMotor.set(.1);
          } else if (currentAngleMotorPosition > currentAngleSetpoint) {
            this.m_shooterAngleMotor.set(-.1);
          } else if (currentAngleMotorPosition == currentAngleSetpoint) {
            this.m_shooterAngleMotor.set(0);
            isPositioned = true;
          }
        }
    }

    public void enableShooter() {
      this.m_shooterState = ShooterStatus.ENABLED;
      double angle;
      if (setShooterAngle(lengthZ) == ShooterAngle.HIGH) {
        angle = Constants.Shooter.shooterAngleHigh;
      } else if (setShooterAngle(lengthZ) == ShooterAngle.MEDIUM){
        angle = Constants.Shooter.shooterAngleMedium;
      } else {
        angle = Constants.Shooter.shooterAngleLow;
      }
      this.setSpeed(ShooterTargeting.calculateVelocity(lengthZ, angle, Constants.Shooter.shooterHeight));
      double speed = getCurrentSpeedSetpoint();
      this.m_shooterMotor.set(speed);
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

    public void enableFeeder(){
      if(getSensorBallState() == true) {
        this.m_feederState = FeederStatus.ENABLED;
        this.m_shooterFeeder.set(.1);
      }
    }

    @Override
    public void periodic() {
      // TODO Add PID values

      //this.m_pidController.setReference(this.m_speedSetpoint, CANSparkMax.ControlType.kVelocity);
      m_shooterMotor.set(m_pidController.calculate(m_shootEncoder.getPosition(),getCurrentSpeedSetpoint()));

      SmartDashboard.putNumber("Shooter Setpoint Speed", this.m_speedSetpoint);
      SmartDashboard.putNumber("Shooter Actual Speed", this.m_shootEncoder.getVelocity());
      SmartDashboard.putBoolean("Infrared Ball Sensor Value", this.getSensorBallState());
      SmartDashboard.putNumber("shooter_angle", this.currentAngleSetpoint);

    }
}