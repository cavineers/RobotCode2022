package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.lib.ShooterTargeting;

public class Dashboard {
    private RobotContainer container;

    public Dashboard(RobotContainer rc) {
        this.container = rc;
        this.configureDashobard();
    }

    private void configureDashobard() {
        // Differential Drive Graph
        Shuffleboard.getTab("DriveTrain")
            .add("DriveTrain", this.container.drivetrain.getDifferentialDrive())
            .withWidget(BuiltInWidgets.kDifferentialDrive)
            .withPosition(6, 1)
            .withSize(3, 2);

         // Limelight Camera
         Shuffleboard.getTab("General")
            .add("Camera", "http://10.45.41.6:8101")
            .withWidget(BuiltInWidgets.kCameraStream)
            .withPosition(4, 2)
            .withSize(3, 3);
    }
}
