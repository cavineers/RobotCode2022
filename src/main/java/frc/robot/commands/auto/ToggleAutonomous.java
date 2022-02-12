package frc.robot.commands.auto;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ToggleAutonomous {
    public enum AutonomousState {
        ON,
        OFF,
    }
    public static void main(String[] args) {

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        final Runnable runnable = new Runnable() {
            int countdownStarter = 15;

            public void run() {

                System.out.println(countdownStarter);
                countdownStarter--;
                AutonomousState = Off;
                if (countdownStarter < 0) {
                    
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 1, SECONDS);
    }
}

}
