package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Driver Centric Teleop")
public class Driver_Centric_Mode extends OpMode
{
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;

    private Heading driveDirection;
    private boolean isTurning = false;

    private int ruleBreaks = 0;

    private BNO055IMU imu = null;

    private double sensitivity = 0.7;
    PID_Controller turnHoldController = new PID_Controller(0.025 * sensitivity, 0.0, 0.006 * sensitivity);

    @Override
    public void init() {
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fld");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frd");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bld");
        backRightDrive = hardwareMap.get(DcMotor.class, "brd");
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        frontLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        gamepad1.setJoystickDeadzone(0.1f);

        setupImu();

        Heading.setImu(imu);
        Heading.setFieldOffset(-Heading.getAbsoluteHeading());
    }

    @Override
    public void start() {
        driveDirection = Heading.createRelativeHeading(0f);
        turnHoldController.setSetpoint(0.0);
    }

    @Override
    public void loop() {
        double away = -gamepad1.left_stick_y;
        double side  = gamepad1.left_stick_x;
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

        double directionOfJoystick = Math.toDegrees(Math.atan2(side, away));

        double heading = Heading.getFieldHeading();
        double robotOrientedCommand = directionOfJoystick - heading;

        double inputSize = Math.sqrt(Math.pow(away, 2) + Math.pow(side, 2));
        double strafe = inputSize * Math.sin(Math.toRadians(robotOrientedCommand));
        double forward = inputSize * Math.cos(Math.toRadians(robotOrientedCommand));

        setDrive(forward, turnPower, strafe);

        telemetry.addData("Direction",directionOfJoystick);
        telemetry.addData("Field Heading",Heading.getFieldHeading());
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

        imu.initialize(imuParameters);
    }
}