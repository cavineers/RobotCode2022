# RobotCode2022
Robot Code for the 2022 FRC Season

## Notes / Warnings
The ensure proper use of the robot and this software, please follow all listed instructions:

- Ensure the intake drop mechanism is fully up on the robot
- Ensure the shooter angle system is fully lowered and within the frame parimaters to home the shooter on boot up. DO NOT POWER ON THE BOT WITHOUT THIS STEP BEING COMPLETED
- Follow all FIRST, FRC, and Hardware safety protocols

[Robot Controls](https://github.com/cavineers/RobotCode2022/blob/development/CONTROLS.md)

## PID Controller
PID - Proportional, Integral, and Derivative - A method of setting a setpoint for a given motor and using a PID Loop to keep it at the setpoint. The PID will allow the motor to account for friction, backward forces, or counter forces that might slow down the motor as it interacts with objects or gears.

![PID Loop](https://media.discordapp.net/attachments/578396462480621568/942794011607978024/unknown.png)

To tune a PID or PIDF (F meaning Feed Forward) start off with all values at 0 and then slowly increase P until the motor oscillates (moves back and forth) around the setpoint. Then increase to D to reduce the time to find the setpoint and F to account for any constant under or over in speed. The PID loop will become closer and more accurate to the setpoint over time, but ajusting D up slightly can reduce that time. (Don't increase D too much as it can cause fluctations in accuracy). Use F if the setpoint is 1000 for example but the loop always reaches 800, then increase F to account for the 200 RPM difference.

## Limelight
For our targeting system, we are using a Limelight which uses reflective tape in order to measure distance and form triangles which we can use for both X and Y offset of the goals. Visit: [http://limelight.local:5801](http://limelight.local:5801) when connected to the robot in order to change or modify system settings and camera streams.
