package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Andrew.Minibot", group = "")
public class andrewMinibotTeleop extends OpMode{
    DcMotor leftMotor;
    DcMotor rightMotor;
    @Override
    public void init() {
        leftMotor = hardwareMap.get(DcMotor.class,"left");
        rightMotor = hardwareMap.get(DcMotor.class,"right");

        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {

        float forwardPower = -gamepad1.left_stick_y;
        float turnPower = gamepad1.right_stick_x;

        float leftPower = forwardPower + turnPower;
        float rightPower = forwardPower - turnPower;

        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);




    }
}
