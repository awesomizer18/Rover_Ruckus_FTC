package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Disabled
@TeleOp(name="Brian's Drive Test")
public class Brians_Drive_Trest extends OpMode {
    private DcMotor Back_Left;
    private DcMotor Back_Right;
    private DcMotor Front_Left;
    private DcMotor Front_Right;

    @Override
    public void init() {
        Back_Left = hardwareMap.get(DcMotor.class,"bl");
        Back_Right = hardwareMap.get(DcMotor.class,"br");
        Front_Left = hardwareMap.get(DcMotor.class,"fl");
        Front_Right = hardwareMap.get(DcMotor.class,"fr");

        Back_Left.setDirection(DcMotor.Direction.REVERSE);
        Back_Right.setDirection(DcMotor.Direction.FORWARD);
        Front_Left.setDirection(DcMotor.Direction.REVERSE);
        Front_Right.setDirection(DcMotor.Direction.FORWARD);

        Back_Left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Back_Right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Front_Left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Front_Right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        gamepad1.setJoystickDeadzone(0.1f);
    }

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double turnPower = gamepad1.right_stick_x;
        double strafePower = gamepad1.left_stick_x;

        setDrive(forward, turnPower, strafePower);
    }
    private void setDrive(double forward, double turnPower, double strafePower) {

        Back_Left.setPower(forward + turnPower - strafePower);
        Front_Left.setPower(forward + turnPower + strafePower);
        Back_Right.setPower(forward - turnPower + strafePower);
        Front_Right.setPower(forward - turnPower - strafePower);


    }
}