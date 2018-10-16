package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Teleop_Test", group = "main")
public class Teleop_Test extends Teleop_Parent {
    @Override
    public void run() {
        double drivePower = -gamepad1.left_stick_y;
        double turnPower = gamepad1.right_stick_x;
        double strafePower = gamepad1.left_stick_x;
        setDrive(drivePower,turnPower, strafePower);
    }

    @Override
    public void begin() {

    }
}
