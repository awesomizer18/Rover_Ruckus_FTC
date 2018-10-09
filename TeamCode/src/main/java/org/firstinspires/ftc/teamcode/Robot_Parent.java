package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class Robot_Parent extends LinearOpMode {

    protected final double EC_PER_DEGREE = 20.04;
    protected DcMotor backLeftDrive;
    protected DcMotor backRightDrive;
    protected DcMotor frontLeftDrive;
    protected DcMotor frontRightDrive;

    @Override
    public void runOpMode() throws InterruptedException {
    }

    abstract public void getReady();

    abstract public void go();

    protected void setDrive(double forwardPower, double turnPower, double strafePower) {
        backLeftDrive.setPower(forwardPower + turnPower - strafePower);
        backRightDrive.setPower(forwardPower - turnPower + strafePower);
        frontLeftDrive.setPower(forwardPower + turnPower + strafePower);
        frontRightDrive.setPower(forwardPower - turnPower - strafePower);
    }
    protected double getTurnPosition() {
        double position = backLeftDrive.getCurrentPosition() - backRightDrive.getCurrentPosition()
                + frontLeftDrive.getCurrentPosition() - frontRightDrive.getCurrentPosition();
        position /= 4.0;
        position /= EC_PER_DEGREE;
        return position;
    }
}
