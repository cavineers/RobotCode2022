package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

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

    public CANSparkMax m_shooterAngleMotor = new CANSparkMax(Constants.Shooter.ShooterAngle, MotorType.kBrushless);

    private CANSparkMax m_shooterFeeder = new CANSparkMax(Constants.Shooter.ShooterFeeder, MotorType.kBrushless);

    //When the button is pressed, the shooter will turn to the correct angle
    //Then the flywheel will go to the correct speed
    //Then the feeder will activate

    private double currentAngleSetpoint;

    // Infrared Sensor
    private DigitalInput m_sensorBall = new DigitalInput(Constants.DIO.BallSensorShooter);

    // See the shooter to disabled by default
    private ShooterStatus m_shooterState = ShooterStatus.DISABLED;

    public FeederStatus feederState = FeederStatus.DISABLED;
 
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
      this.m_shooterFeeder.setIdleMode(IdleMode.kCoast);
      this.m_shooterAngleMotor.setIdleMode(IdleMode.kBrake);

      this.m_shooterMotor.setInverted(true);
      this.m_shooterFeeder.setInverted(true);
      this.m_shooterAngleMotor.setInverted(true);

      // Sets 39 amp limit on motor
      this.m_shooterMotor.setSmartCurrentLimit(39);
      this.m_shooterAngleMotor.setSmartCurrentLimit(39);
      this.m_shooterFeeder.setSmartCurrentLimit(39);

      this.m_shootPID.setIZone(0.0);
      this.m_shootPID.setOutputRange(-1.0, 1.0);
    }

    // Enum for shooter status
    public enum ShooterStatus {
      ENABLED,
      DISABLED
    }

    // Enum for feeder status
    public enum FeederStatus {
      ENABLED,
      DISABLED,
      REVERSED
    }
    
    // Enum for shooter angle
    public enum ShooterAngle {
      HIGH,
      LOW,
      MEDIUM
    }

    // Returns true if the sensor is tripped and ball is in holding
    public boolean getSensorBallState() {
      return !m_sensorBall.get();
    }

    // Returns which angle constant is set
    public ShooterAngle setShooterAngle(double z) {
      if (z >= 6) {
        return ShooterAngle.LOW;
      } else if ((z <= 6) && (z >= 3)) {
        return ShooterAngle.MEDIUM;
      } else {
        return ShooterAngle.HIGH;
      }
    }

    // Returns the angle motor revolution positions
    public double getCurrentAngleMotorPosition(){
      currentAngleMotorPosition = m_angleEncoder.getPosition();
      return currentAngleMotorPosition;
    }

    // Turns to the angle setpoint
    public void turnToAngle(ShooterAngle angle) {
        double currentAngle;

        if (angle == ShooterAngle.LOW) {
          currentAngle = 90 - Constants.Shooter.shooterAngleLow;
          this.currentAngleSetpoint = (currentAngle / Constants.Shooter.degreesPerRevolution);
          
          if (getCurrentAngleMotorPosition() < this.currentAngleSetpoint - 0.5) {
            this.m_shooterAngleMotor.set(Constants.Shooter.angleSpeed);
          } else if (getCurrentAngleMotorPosition() > this.currentAngleSetpoint + 0.5) {
            this.m_shooterAngleMotor.set(-Constants.Shooter.angleSpeed);
          } else {
            this.m_shooterAngleMotor.set(0);
            isPositioned = true;
          }
        } else if (angle == ShooterAngle.MEDIUM) {
          currentAngle = 90 - Constants.Shooter.shooterAngleMedium;
          this.currentAngleSetpoint = (currentAngle / Constants.Shooter.degreesPerRevolution);

          if (getCurrentAngleMotorPosition() < this.currentAngleSetpoint - 0.5) {
            this.m_shooterAngleMotor.set(Constants.Shooter.angleSpeed);
          } else if (getCurrentAngleMotorPosition() > this.currentAngleSetpoint + 0.5) {
            this.m_shooterAngleMotor.set(-Constants.Shooter.angleSpeed);
          } else {
            this.m_shooterAngleMotor.set(0);
            isPositioned = true;
          }
        } else if (angle == ShooterAngle.HIGH) {
          currentAngle = 90 - Constants.Shooter.shooterAngleHigh;
          this.currentAngleSetpoint = (currentAngle / Constants.Shooter.degreesPerRevolution);

          if (getCurrentAngleMotorPosition() < this.currentAngleSetpoint - 0.5) {
            this.m_shooterAngleMotor.set(Constants.Shooter.angleSpeed);
          } else if (getCurrentAngleMotorPosition() > this.currentAngleSetpoint + 0.5) {
            this.m_shooterAngleMotor.set(-Constants.Shooter.angleSpeed);
          } else {
            this.m_shooterAngleMotor.set(0);
            isPositioned = true;
          }
        }
    }

    // Enables the shooter
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
      if (ShooterTargeting.calculateVelocity(z, Units.degreesToRadians(angle)) > 0.2) {
        this.setSpeed(ShooterTargeting.calculateVelocity(z, Units.degreesToRadians(angle)));
      }
    }

    // Disables the shooter
    public void disableShooter() {
      this.m_shooterState = ShooterStatus.DISABLED;
      this.setSpeed(0.0);
    }

    // Returns if the shooter is enabled or disabled
    public ShooterStatus getCurrentState() {
      return this.m_shooterState;
    }

    // Sets the shooter set point speed (in RPM)
    public void setSpeed(double speed) {
      this.m_speedSetpoint = speed;
    }

    // Returns the target speed setpoint of the shooter
    public double getCurrentSpeedSetpoint() {
      return this.m_speedSetpoint;
    }

    // Returns true if the shooter motor is at it's target setpoint
    public boolean atSetpoint() {
      return (Math.abs(this.getCurrentSpeedSetpoint() - Math.abs(this.m_shootEncoder.getVelocity())) < 50);
    }

    // Returns true if the shooter motor is at it's target setpoint
    public boolean atAngle() {
        return this.isPositioned;
    }

    // Resets the isPositioned variable to false when command ends
    public void resetPosition() {
      this.isPositioned = false;
    }

    // Turns on the feeder system
    public void enableFeeder(){
      if(getSensorBallState() == true) {
        this.feederState = FeederStatus.ENABLED;
        this.m_shooterFeeder.set(Constants.Shooter.feederSpeed);
      }
    }

    // Overloaded method for feeder systems 0 - 1 speed
    public void enableFeeder(double speed){
        this.feederState = FeederStatus.ENABLED;
        this.m_shooterFeeder.set(speed);
    }

    // Disables the feeder systems
    public void disableFeeder(){
      this.feederState = FeederStatus.DISABLED;
      this.m_shooterFeeder.set(0);
    }

    // Sets the feeder in reverse
    public void reverseFeeder(){
      this.feederState = FeederStatus.REVERSED;
      this.m_shooterFeeder.set(-Constants.Shooter.feederSpeed);
    }

    // Returns the position of the shooter motor
    public double getShooterMotorPosition() {
     return m_shootEncoder.getPosition();
    }

    public boolean withinXTolerance() {
      if (ShooterTargeting.getTx() >= -5.2 && ShooterTargeting.getTx() <= 5.2) {
        return true;
      } else {
        return false;
      }
    }

    @Override
    public void periodic() {
      this.m_shootPID.setReference(this.getCurrentSpeedSetpoint(), CANSparkMax.ControlType.kVelocity);

      this.m_shootPID.setP(Constants.Shooter.kP);
      this.m_shootPID.setI(Constants.Shooter.kI);
      this.m_shootPID.setD(Constants.Shooter.kD);
      this.m_shootPID.setFF(Constants.Shooter.kF);

      SmartDashboard.putNumber("Shooter SetPoint (RPM)", this.getCurrentSpeedSetpoint());
      SmartDashboard.putNumber("Shooter Actual (RPM)", this.m_shootEncoder.getVelocity());
      SmartDashboard.putNumber("TX Offset", ShooterTargeting.getTx());
      SmartDashboard.putString("Setpoint of Angle", this.setShooterAngle(ShooterTargeting.findZ()).toString());
      SmartDashboard.putBoolean("ShooterSensor", this.getSensorBallState());
      SmartDashboard.putBoolean("Shooter Ready", (this.atAngle() == true && this.atSetpoint() == true));
      SmartDashboard.putBoolean("Within X Offset", this.withinXTolerance());
      SmartDashboard.putNumber("TD Value", ShooterTargeting.getTD());
      SmartDashboard.putNumber("Z Value", ShooterTargeting.findZ());
      SmartDashboard.putNumber("Motor Position - Angle", this.getCurrentAngleMotorPosition());
    }
}