package org.firstinspires.ftc.teamcode;

public abstract class Teleop_Parent extends Robot_Parent {

    @Override
    public void initialize() {
        gamepad1.setJoystickDeadzone(0.1f);
        setup();
    }

    @Override
    public void play() {
        begin();

        while (opModeIsActive()) {
            repeat();
        }
    }

    public abstract void repeat();

    protected double mapJoystick(double joystickValue) {
        return joystickValue;

    }
}
