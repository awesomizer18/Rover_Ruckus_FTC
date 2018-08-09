package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "My1Program",group = "")
public class AidenMinibotTeleop extends OpMode {
    DcMotor motorL;
    DcMotor motorR;
    @Override
    public void init() {
        motorL = hardwareMap.get(DcMotor.class,"left");
        motorR = hardwareMap.get(DcMotor.class,"right");

        motorL.setDirection(DcMotorSimple.Direction.FORWARD);
        motorR.setDirection(DcMotorSimple.Direction.FORWARD);

        motorL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motorR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        float forwardPower = -gamepad1.left_stick_y;
        float turnPower = gamepad1.right_stick_x;

        float leftPower = forwardPower + turnPower;
        float rightPower = forwardPower - turnPower;

        motorL.setPower(leftPower);
        motorR.setPower(rightPower);

    }
}
