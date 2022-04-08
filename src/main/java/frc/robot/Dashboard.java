package frc.robot;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class Dashboard {
    private RobotContainer container;

    public Dashboard(RobotContainer rc) {
        this.container = rc;
        this.configureDashobard();
    }

    private void configureDashobard() {
        //! General Vals

        // Differential Drive Graph
        Shuffleboard.getTab("DriveTrain")
            .add("DriveTrain", this.container.drivetrain.getDifferentialDrive())
            .withWidget(BuiltInWidgets.kDifferentialDrive)
            .withPosition(6, 1)
            .withSize(3, 2);
    }
}
