package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {

    // LED Light Modes
    public enum LedMode {
        ON, OFF, DEFAULT, BLINK
    }

    private NetworkTable m_limeLightTable;

    public Limelight() {
        this.m_limeLightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public NetworkTable getTable() {
        return this.m_limeLightTable;
    }

    public double getHorizontalOffset() {
        double tx = this.m_limeLightTable.getEntry("tx").getDouble(0.0);
        return tx;
    }

    public double getVerticalOffset() {
        return this.m_limeLightTable.getEntry("ty").getDouble(0.0);
    }

    public boolean validTargets() {
        return this.m_limeLightTable.getEntry("tv").getDouble(0.0) > 0;
    }

    public double getRange() {
        return this.m_limeLightTable.getEntry("ta").getDouble(0.0);
    }

    public double getScreenFill() {
        return this.m_limeLightTable.getEntry("ta").getDouble(0.0);
    }

    /**
     * Set the LED mode of the Limelight.
     * @param mode The mode to use
     */
    public void setLightMode(LedMode mode) {
        switch (mode) {
            case ON:
                this.m_limeLightTable.getEntry("ledMode").setNumber(3);
                break;
            case BLINK:
                this.m_limeLightTable.getEntry("ledMode").setNumber(2);
                break;
            case OFF:
                this.m_limeLightTable.getEntry("ledMode").setNumber(1);
                break;
            case DEFAULT:
                this.m_limeLightTable.getEntry("ledMode").setNumber(0);
                break;
            default:
                this.m_limeLightTable.getEntry("ledMode").setNumber(0);
                break;
        }
    }

    private double llCatch(double a) {
        if (a == Double.POSITIVE_INFINITY || a == Double.NEGATIVE_INFINITY || a < 0.0) {
            return 0.0;
        } else {
            return a;
        }
    }

    /**
     * Find the distance to the target.
     * @return Distance in meters.
     */
    public double getDistance() {
        double height1 = Constants.Targeting.kLimelightHeightFromGround;
        double height2 = Constants.Targeting.kFieldGoalHeightFromGround;
        double angle1 = Constants.Targeting.kLimelightMountingAngle;
        double angle2 = this.m_limeLightTable.getEntry("ty").getDouble(0.0);
        // double distance = (height2 - height1) * (1 / Math.tan(Math.toRadians(angle1 + angle2)));
        double distance = (height2 - height1) / (Math.sin(Math.toRadians(angle1 + angle2)));
        return this.llCatch(distance);
        // return (Math.round(this.llCatch(distance) * 10) / 10);
    }
}
