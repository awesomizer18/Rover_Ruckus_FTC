package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Temperature;

@Autonomous(name = "Control Test")
public class Control_Test extends LinearOpMode {

    private DcMotor frontLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backLeftDrive;
    private DcMotor backRightDrive;

    private BNO055IMU imu;

    private double pGain = 0.01;

    PID_Controller turnController       = new PID_Controller(0.0, 0.0, 0.0);
    PID_Controller turnHoldController   = new PID_Controller(pGain, 0.0, 0.0);
    PID_Controller forwardController    = new PID_Controller(0.0, 0.0, 0.0);
    PID_Controller strafeController     = new PID_Controller(0.0, 0.0, 0.0);

    @Override
    public void runOpMode() throws InterruptedException {
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

        setupImu();

        Heading.setImu(imu);
        Heading.setFieldOffset(0.0f);

        waitForStart();

        Heading driveDirection = Heading.createRelativeHeading(0f);

        turnHoldController.setSetpoint(0.0); // Don't turn
        forwardController.setSetpoint(60.0); // Drive forwards 60 inches (5 feet)

        double currentHeading;
        double turnPower;
        while (opModeIsActive()) {
            if (gamepad1.dpad_up || gamepad1.dpad_down)
            {
                if (gamepad1.dpad_up)
                    pGain *= 1.1;
                if (gamepad1.dpad_down)
                    pGain /= 1.1;
                turnHoldController = new PID_Controller(pGain, 0.0, 0.0);
                turnHoldController.setSetpoint(0.0);
                while (gamepad1.dpad_up || gamepad1.dpad_down);
            }
            currentHeading = driveDirection.getRelativeHeading();
            turnPower = turnHoldController.update(currentHeading);
            setDrive(0.0, turnPower, 0.0);

            telemetry.addData("P Gain", pGain);
            telemetry.addData("Heading", currentHeading);
            telemetry.addData("TurnPower", turnPower);
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
