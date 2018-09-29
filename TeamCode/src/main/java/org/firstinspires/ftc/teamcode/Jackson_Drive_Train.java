package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp (name = "Jackson's cool drive", group = "")
public class Jackson_Drive_Train extends OpMode {
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;


    @Override
    public void init() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "bl");
        backRightDrive = hardwareMap.get(DcMotor.class, "br");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fl");
        frontRightDrive = hardwareMap.get(DcMotor.class, "fr");

        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        gamepad1.setJoystickDeadzone((float) 0.1);
    }

    @Override
    public void loop() {
        double leftDrivePower = -gamepad1.left_stick_y;
        double rightDrivePower = -gamepad1.right_stick_y;

        frontLeftDrive.setPower(leftDrivePower);
        frontRightDrive.setPower(rightDrivePower);


    }
}
