package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Jakes Test Program", group = "")
public class Jakes_Teleop_Minibot extends OpMode {
    DcMotor leftDrive;
    DcMotor rightDrive;
    @Override
    public void init() {
        leftDrive = hardwareMap.get(DcMotor.class,"left");
        rightDrive = hardwareMap.get(DcMotor.class,"right");

        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    @Override
    public void loop() {

        float forwardPower = -gamepad1.left_stick_y;
        float turnPower = gamepad1.right_stick_x;

        float leftPower = forwardPower + turnPower;
        float rightPower = forwardPower - turnPower;

        leftDrive.setPower(leftPower);
        rightDrive.setPower(rightPower);


    }
}
