package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Omni Drive Teleop", group="Competition")//Competition/Main
public class Omni_Drive_Teleop extends Omni_Parent
{
    public void cycle() {
        // Setup a variable for each drive wheel to save power level for telemetry
        double leftPower;
        double rightPower;

        //leftPower = -controllerThreshold(gamepad1.left_stick_y);
        //rightPower = -controllerThreshold(gamepad1.right_stick_y);

        double drive = -controllerThreshold(gamepad1.left_stick_y);
        double strafe = controllerThreshold(gamepad1.left_stick_x);
        double turn = controllerThreshold(gamepad1.right_stick_x);

        if (Math.abs(drive) > Math.abs(strafe))
            setDrive(drive, turn, 0.0);
        else
            setDrive(0.0, turn, strafe);
    }


}