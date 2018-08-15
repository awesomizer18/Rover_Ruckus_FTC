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

    private double dGain = 0.006;
    private double pGain = 0.025;

    PID_Controller PController = new PID_Controller(pGain, 0.0, 0.0);
    PID_Controller DController = new PID_Controller(0.0, 0.0, dGain);

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

        PController.setSetpoint(0.0);
        DController.setSetpoint(0.0);

        double currentHeading;
        double PPower;
        double DPower;
        boolean useP = true;
        boolean useD = false;
        while (opModeIsActive()) {
            if (gamepad1.dpad_up || gamepad1.dpad_down)
            {
                if (gamepad1.dpad_up)
                    dGain *= 1.1;
                if (gamepad1.dpad_down)
                    dGain /= 1.1;
                DController = new PID_Controller(0.0, 0.0, dGain);
                DController.setSetpoint(0.0);
                while (gamepad1.dpad_up || gamepad1.dpad_down);
                for (int i = 0; i < 3; i++)
                {
                    DController.update(driveDirection.getRelativeHeading());
                }
            }

            if (gamepad1.x) {
                useP = !useP;
                while (gamepad1.x);
            }

            if (gamepad1.y) {
                useD = !useD;
                while (gamepad1.y);
            }

            currentHeading = driveDirection.getRelativeHeading();

            PPower = PController.update(currentHeading);
            DPower = Math.max(Math.min(DController.update(currentHeading), 0.5), -0.5);

            setDrive(0.0, (useP?PPower:0.0) + (useD?DPower:0.0), 0.0);


            telemetry.addData("P Gain (" + (useP?"On":"Off") + ")", pGain);
            telemetry.addData("D Gain (" + (useD?"On":"Off") + ")", dGain);
            telemetry.addData("Heading", currentHeading);
            telemetry.addData("PPower", PPower);
            telemetry.addData("DPower", DPower);
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
