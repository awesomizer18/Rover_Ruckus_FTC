package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Basic Drive")
public class Basic_Drive extends OpMode{
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor leftDrive2;
    private DcMotor rightDrive2;

    @Override
    public void init() {
        leftDrive = hardwareMap.get (DcMotor.class, "left");
        rightDrive = hardwareMap.get (DcMotor.class, "right");
        leftDrive2 = hardwareMap.get (DcMotor.class, "left2");
        rightDrive2 = hardwareMap.get (DcMotor.class, "right2");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        leftDrive2.setDirection(DcMotor.Direction.REVERSE);
        rightDrive2.setDirection(DcMotor.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        gamepad1.setJoystickDeadzone(0.1f);

    }

    @Override
    public void loop() {
        double forwardPower = -gamepad1.left_stick_y;
        double turnPower = gamepad1.right_stick_x;

        setDrive(forwardPower, turnPower);

    }
    private void setDrive(double forwardPower, double turnPower) {
        leftDrive.setPower(forwardPower + turnPower);
        rightDrive.setPower(forwardPower - turnPower);
        leftDrive2.setPower(forwardPower + turnPower);
        rightDrive2.setPower(forwardPower - turnPower);
    }
}