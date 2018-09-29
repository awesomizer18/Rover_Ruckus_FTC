package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Tyler Drive Class")
public class tyler_drive_class extends OpMode {
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;
    private DcMotor frontRightDrive;
    private DcMotor frontLeftDrive;

    @Override
    public void init() {
        backLeftDrive = hardwareMap.get(DcMotor.class,"bl");
        backRightDrive = hardwareMap.get(DcMotor.class,"br");
        frontLeftDrive = hardwareMap.get(DcMotor.class,"fl");
        frontRightDrive = hardwareMap.get(DcMotor.class,"fr");


        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);


        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        gamepad1.setJoystickDeadzone(0.1f);
    }

    @Override
    public void loop() {
        double forwardpower = -gamepad1.left_stick_y;
        double turnpower = gamepad1.right_stick_x;
        double strafePower = gamepad1.left_stick_x;

        setDrive(forwardpower,turnpower, strafePower);



    }
    private void setDrive (double forwardPower, double turnPower, double strafePower) {

        backLeftDrive.setPower(forwardPower + turnPower - strafePower);
        backRightDrive.setPower(forwardPower - turnPower + strafePower);
        frontRightDrive.setPower(forwardPower - turnPower - strafePower);
        frontLeftDrive.setPower(forwardPower + turnPower + strafePower);

    }
}
