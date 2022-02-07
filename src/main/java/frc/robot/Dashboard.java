package frc.robot;

import java.util.Map;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class Dashboard {
    private RobotContainer container;

    public Dashboard(RobotContainer rc) {
        this.container = rc;
        this.configureDashobard();
    }

    private void configureDashobard() {
        Shuffleboard.getTab("Dashboard")
            .add("DriveTrain", this.container.drivetrain.getDifferentialDrive())
            .withWidget(BuiltInWidgets.kDifferentialDrive)
            .withPosition(6, 1)
            .withSize(3, 2);
    }
}
