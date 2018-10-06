package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Encoder_Counts", group = "")


public class Encoder_Counts extends OpMode{
    private final double ENCODER_COUNTS_PER_INCH = 81.19;
    private final double ENCODER_COUNTS_PER_DEGREE = 18.06;

    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;


    @Override
    public void init() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "bld");
        backRightDrive = hardwareMap.get(DcMotor.class, "brd");
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fld");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frd");

        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);
        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);

        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        gamepad1.setJoystickDeadzone(0.0f);
    }

    @Override
    public void loop() {
        double forwardPower = -gamepad1.left_stick_y;
        double turnPower = gamepad1.right_stick_x;
        double strafePower = 0.0;

        telemetry.addData("Forward Position",getForwardPosition());
        telemetry.addData("Turn Position",getTurnPosition());
        telemetry.update();

        setDrive(forwardPower, turnPower, strafePower);
    }
    private void setDrive(double forwardPower, double turnPower, double strafePower) {
        backLeftDrive.setPower(forwardPower + turnPower - strafePower);
        backRightDrive.setPower(forwardPower - turnPower + strafePower);
        frontLeftDrive.setPower(forwardPower + turnPower + strafePower);
        frontRightDrive.setPower(forwardPower - turnPower - strafePower);
    }

    private double getForwardPosition(){
        double position = backLeftDrive.getCurrentPosition()+
                frontLeftDrive.getCurrentPosition() + frontRightDrive.getCurrentPosition() +
                backRightDrive.getCurrentPosition();

        position /= 4.0;
        position /= ENCODER_COUNTS_PER_INCH;

        return position;
    }
    private double getTurnPosition(){
        double position = backLeftDrive.getCurrentPosition()+
                frontLeftDrive.getCurrentPosition() - frontRightDrive.getCurrentPosition() -
                backRightDrive.getCurrentPosition();

        position /= 4.0;
        position /= ENCODER_COUNTS_PER_DEGREE;

        return position;
    }
    private double getStrafePosition(){
        double position = - backLeftDrive.getCurrentPosition()+
                frontLeftDrive.getCurrentPosition() - frontRightDrive.getCurrentPosition() +
                backRightDrive.getCurrentPosition();

        position /= 4.0;
        position /= ENCODER_COUNTS_PER_INCH;

        return position;
    }
}
