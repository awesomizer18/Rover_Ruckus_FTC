package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Brian's PID Drive")

public class Brian_Drive_PID_2 extends OpMode {

    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private PID_Controller forwardPID = new PID_Controller(0.071, 0.0, 0.0);
    private PID_Controller turnPID = new PID_Controller(0.025, 0.0, 0.0);
    private PID_Controller strafePID = new PID_Controller(0.071, 0.0, 0.0);
    private final double ENCODER_COUNTS_PER_INCH = 81.19;
    private final double ENCODER_COUNTS_PER_DEGREE = 18.06;
    //20.04 for enc/degree?

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

        gamepad1.setJoystickDeadzone(0.1f);
    }

    @Override
    public void start() {
        forwardPID.resetPID();
        turnPID.resetPID();
        strafePID.resetPID();
        forwardPID.setSetpoint(getForwardPosition() + 60.0);
        turnPID.setSetpoint(getTurnPosition());
        strafePID.setSetpoint(getStrafePosition());
    }

    @Override
    public void loop() {
        double forwardPower = forwardPID.update(getForwardPosition());
        double turnPower = turnPID.update(getTurnPosition());
        double strafePower = strafePID.update(getStrafePosition());

        setDrive(forwardPower, turnPower, strafePower);

    }

    private void setDrive(double forwardPower, double turnPower, double strafePower) {
        backLeftDrive.setPower(forwardPower + turnPower - strafePower);
        backRightDrive.setPower(forwardPower - turnPower + strafePower);
        frontLeftDrive.setPower(forwardPower + turnPower + strafePower);
        frontRightDrive.setPower(forwardPower - turnPower - strafePower);
    }

    private double getForwardPosition() {
        double position = backLeftDrive.getCurrentPosition() + backRightDrive.getCurrentPosition() +
                frontLeftDrive.getCurrentPosition()+ frontRightDrive.getCurrentPosition();
        position /= 4.0;
        position /= ENCODER_COUNTS_PER_INCH;
        return position;
    }
    private double getTurnPosition() {
        double position = backLeftDrive.getCurrentPosition() + frontLeftDrive.getCurrentPosition()
                - backRightDrive.getCurrentPosition() - frontRightDrive.getCurrentPosition();
        position /= 4.0;
        position /= ENCODER_COUNTS_PER_DEGREE;
        return position;
    }
    private double getStrafePosition() {
        double position = backRightDrive.getCurrentPosition() + frontLeftDrive.getCurrentPosition()
                - backLeftDrive.getCurrentPosition() - frontRightDrive.getCurrentPosition();
        position /= 4.0;
        position /= ENCODER_COUNTS_PER_INCH;
        return position;
    }
}