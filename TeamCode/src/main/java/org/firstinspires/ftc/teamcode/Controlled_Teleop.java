package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Controlled Teleop")
public class Controlled_Teleop extends OpMode {
    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;

    private Heading driveDirection;
    private boolean isTurning = false;

    private int ruleBreaks = 0;

    private BNO055IMU imu;

    PID_Controller turnHoldController = new PID_Controller(0.025, 0.0, 0.006);

    @Override
    public void init() {
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fld");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frd");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bld");
        backRightDrive = hardwareMap.get(DcMotor.class, "brd");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        gamepad1.setJoystickDeadzone(0.1f);

        setupImu();

        Heading.setImu(imu);
        Heading.setFieldOffset(0.0f);
    }

    @Override
    public void start() {
        driveDirection = Heading.createRelativeHeading(0f);
        turnHoldController.setSetpoint(0.0);
    }

    @Override
    public void loop() {
        double turnPower = gamepad1.right_stick_x;
        float currentHeading = driveDirection.getRelativeHeading();
        if (Math.abs(turnPower) < 0.01) {
            if (isTurning) {
                isTurning = false;
                driveDirection.setRelativeOffset(-Heading.getFieldHeading());
                turnHoldController.resetPID();
            }
            turnPower = turnHoldController.update(currentHeading);

            if (Math.abs(turnPower) > 0.5) {
                turnPower = Math.min(Math.max(turnPower,-0.5),0.5);
                ruleBreaks++;
            }
        }
        else
            isTurning = true;

        setDrive(-gamepad1.left_stick_y, turnPower,gamepad1.left_stick_x);

        telemetry.addData("Heading", currentHeading);
        telemetry.addData("Rule breaks", ruleBreaks);
        telemetry.update();
    }

    private void setDrive(double forwardsPower, double turnPower, double strafePower) {
        frontRightDrive.setPower(forwardsPower - turnPower - strafePower);
        backRightDrive.setPower(forwardsPower - turnPower + strafePower);
        frontLeftDrive.setPower(forwardsPower + turnPower + strafePower);
        backLeftDrive.setPower(forwardsPower + turnPower - strafePower);
    }

    private void setupImu() {
        BNO055IMU.Parameters imuParameters = new BNO055IMU.Parameters();
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        imuParameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        imuParameters.loggingEnabled = true;
        imuParameters.loggingTag = "IMU";
        imuParameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imuParameters.temperatureUnit = BNO055IMU.TempUnit.FARENHEIT;

        imu.initialize(imuParameters);
    }
}
