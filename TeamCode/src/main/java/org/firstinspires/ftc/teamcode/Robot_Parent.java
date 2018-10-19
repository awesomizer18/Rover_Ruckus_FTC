package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class Robot_Parent extends LinearOpMode {

    // TODO: remember to set values to diff number
    private final double ENCODER_COUNTS_PER_DEGREE = 18.06;

    protected DcMotor leftDrive;
    protected DcMotor rightDrive;
    protected DcMotor middleDrive;

    protected PID_Controller goToTurnPID = new PID_Controller(0.025, 0.0, 0.0);
    protected PID_Controller holdTurnPID = new PID_Controller(0.0,0.0,0.0);

    @Override
    public void runOpMode() throws InterruptedException {
        leftDrive = hardwareMap.get(DcMotor.class, "ld");
        rightDrive = hardwareMap.get(DcMotor.class, "rd");
        middleDrive = hardwareMap.get(DcMotor.class, "md");

        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        middleDrive.setDirection(DcMotor.Direction.FORWARD);

        //middle drive assumes motor faces backwards. Switch if motor faces forwards

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        middleDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        initialize();
        telemetry.addLine("Initialization Complete");
        telemetry.update();
        waitForStart();
        play();
    }

    public abstract void initialize();
    public abstract void play();
    public abstract void setup();
    public abstract void begin();

    // Functions

    protected void setDrive(double forwardPower, double turnPower, double strafePower) {
        leftDrive.setPower(forwardPower + turnPower);
        rightDrive.setPower(forwardPower - turnPower);
        middleDrive.setPower(strafePower);
    }

    protected double getTurnPosition() {
        double position = leftDrive.getCurrentPosition() - rightDrive.getCurrentPosition();
        position /= 2.0;
        position /= ENCODER_COUNTS_PER_DEGREE;
        return position;
    }
}
