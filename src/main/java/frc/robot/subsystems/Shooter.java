package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import frc.lib.ShooterTargeting;
import frc.robot.Constants;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.DigitalInput;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter extends SubsystemBase {
    
    private boolean isPositioned = false;

    public CANSparkMax m_shooterMotor = new CANSparkMax(Constants.Shooter.ShooterMotor, MotorType.kBrushless);

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
    public RelativeEncoder m_shootEncoder = m_shooterMotor.getEncoder();

    public SparkMaxPIDController m_shootPID = m_shooterMotor.getPIDController();

    private RelativeEncoder m_angleEncoder = m_shooterAngleMotor.getEncoder();

    private double currentAngleMotorPosition = m_angleEncoder.getPosition();
 
    // Create new PID controller
    //private SparkMaxPIDController m_pidController = m_shooterMotor.getPIDController();
    //private PIDController m_pidController = new PIDController(Constants.Shooter.kP, Constants.Shooter.kI, Constants.Shooter.kD);

    public Shooter() {
      this.m_shooterMotor.restoreFactoryDefaults();

      // Sets motor to coast mode
      this.m_shooterMotor.setIdleMode(IdleMode.kCoast);
      this.m_shooterAngleMotor.setIdleMode(IdleMode.kBrake);

      // Sets 39 amp limit on motor
      this.m_shooterMotor.setSmartCurrentLimit(39);
      this.m_shooterAngleMotor.setSmartCurrentLimit(39);
      this.m_shooterFeeder.setSmartCurrentLimit(39);

      this.m_shootPID.setIZone(0.0);
      this.m_shootPID.setOutputRange(-1.0, 1.0);
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
      if (z >= 6) {
        return ShooterAngle.LOW;
      } else if ((z <= 6) && (z >= 3)) {
        return ShooterAngle.MEDIUM;
      } else {
        return ShooterAngle.HIGH;
      }
    }

    public double getCurrentAngleMotorPosition(){
      currentAngleMotorPosition = m_angleEncoder.getPosition();
      return currentAngleMotorPosition;
    }

    // Constant shooting angles
    public void turnToAngle(ShooterAngle angle) {
        double currentAngle;
        if (angle == ShooterAngle.LOW) {
          currentAngle = Constants.Shooter.shooterAngleLow;
          this.currentAngleSetpoint = (currentAngle / Constants.Shooter.degreesPerRevolution);
          if (getCurrentAngleMotorPosition() < currentAngleSetpoint - 2) {
            this.m_shooterAngleMotor.set(.05);
          } else if (getCurrentAngleMotorPosition() > currentAngleSetpoint + 2) {
            this.m_shooterAngleMotor.set(-.05);
          } else {
            this.m_shooterAngleMotor.set(0);
            isPositioned = true;
          }
        } else if (angle == ShooterAngle.MEDIUM) {
          currentAngle = Constants.Shooter.shooterAngleMedium;
          this.currentAngleSetpoint = (currentAngle / Constants.Shooter.degreesPerRevolution);
          if (getCurrentAngleMotorPosition() < currentAngleSetpoint - 2) {
            this.m_shooterAngleMotor.set(.05);
          } else if (getCurrentAngleMotorPosition() > currentAngleSetpoint + 2) {
            this.m_shooterAngleMotor.set(-.05);
          } else {
            this.m_shooterAngleMotor.set(0);
            isPositioned = true;
          }
        } else if (angle == ShooterAngle.HIGH) {
          currentAngle = Constants.Shooter.shooterAngleHigh;
          this.currentAngleSetpoint = (currentAngle / Constants.Shooter.degreesPerRevolution);
          if (getCurrentAngleMotorPosition() < currentAngleSetpoint - 2) {
            this.m_shooterAngleMotor.set(.05);
          } else if (getCurrentAngleMotorPosition() > currentAngleSetpoint + 2) {
            this.m_shooterAngleMotor.set(-.05);
          } else {
            this.m_shooterAngleMotor.set(0);
            isPositioned = true;
          }
        }
    }

    public void enableShooter(double z) {
      this.m_shooterState = ShooterStatus.ENABLED;
      double angle;
      if (setShooterAngle(z) == ShooterAngle.HIGH) {
        angle = Constants.Shooter.shooterAngleHigh;
      } else if (setShooterAngle(z) == ShooterAngle.MEDIUM){
        angle = Constants.Shooter.shooterAngleMedium;
      } else {
        angle = Constants.Shooter.shooterAngleLow;
      }
      this.setSpeed(ShooterTargeting.calculateVelocity(z, Units.degreesToRadians(angle), Constants.Shooter.shooterHeight));
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
      return (Math.abs(this.getCurrentSpeedSetpoint() - Math.abs(this.m_shootEncoder.getVelocity())) < 50);
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

    public void disableFeeder(){
      this.m_shooterState = ShooterStatus.DISABLED;
      this.m_shooterFeeder.set(0);
    }

    public double getShooterMotorPosition() {
     return m_shootEncoder.getPosition();
    }

    @Override
    public void periodic() {
      this.m_shootPID.setReference(this.getCurrentSpeedSetpoint(), CANSparkMax.ControlType.kVelocity);

      this.m_shootPID.setP(Constants.Shooter.kP);
      this.m_shootPID.setI(Constants.Shooter.kI);
      this.m_shootPID.setD(Constants.Shooter.kD);
      this.m_shootPID.setFF(Constants.Shooter.kF);

      SmartDashboard.putNumber("SetPoint2", this.getCurrentSpeedSetpoint());
      SmartDashboard.putNumber("Actual2", this.m_shootEncoder.getVelocity());
    }
}