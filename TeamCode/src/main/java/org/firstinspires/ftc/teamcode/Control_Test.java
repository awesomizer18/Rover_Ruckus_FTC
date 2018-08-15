package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Control Test")
public class Control_Test extends LinearOpMode {

    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;

    private BNO055IMU imu;

    private double dGain = 1.0;

    PID_Controller turnController       = new PID_Controller(0.025, 0.0, 0.0);
    PID_Controller turnHoldController   = new PID_Controller(0.0, 0.0, dGain);
    PID_Controller forwardController    = new PID_Controller(0.0, 0.0, 0.0);
    PID_Controller strafeController     = new PID_Controller(0.0, 0.0, 0.0);

    @Override
    public void runOpMode() throws InterruptedException {
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

        setupImu();

        Heading.setImu(imu);
        Heading.setFieldOffset(0.0f);

        waitForStart();

        Heading driveDirection = Heading.createRelativeHeading(0f);

        turnController.setSetpoint(0.0);
        turnHoldController.setSetpoint(0.0);

        double currentHeading;
        double turnPower;
        double turnHoldPower;
        while (opModeIsActive()) {
            if (gamepad1.dpad_up || gamepad1.dpad_down)
            {
                if (gamepad1.dpad_up)
                    dGain *= 1.1;
                if (gamepad1.dpad_down)
                    dGain /= 1.1;
                turnHoldController = new PID_Controller(0.0, 0.0, dGain);
                turnHoldController.setSetpoint(0.0);
                while (gamepad1.dpad_up || gamepad1.dpad_down);
                for (int i = 0; i < 3; i++)
                {
                    turnHoldController.update(driveDirection.getRelativeHeading());
                }
            }
            currentHeading = driveDirection.getRelativeHeading();

            turnPower = turnController.update(currentHeading);
            turnHoldPower = turnHoldController.update(currentHeading);
            setDrive(0.0, turnPower, 0.0);

            telemetry.addData("D Gain", dGain);
            telemetry.addData("Heading", currentHeading);
            telemetry.addData("TurnPower", turnPower);
            telemetry.addData("TurnHoldPower", turnHoldPower);
            telemetry.update();
        }
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
