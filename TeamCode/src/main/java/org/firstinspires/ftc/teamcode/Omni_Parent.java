package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;



public class Omni_Parent extends LinearOpMode {
    protected DcMotor frontRightDrive = null;
    protected DcMotor frontLeftDrive = null;
    protected DcMotor backRightDrive = null;
    protected DcMotor backLeftDrive = null;
    protected BNO055IMU imu = null;

    private final double JOYSTICK_THRESHOLD = 0.1;
    private float zeroFactor = 0.0f;


    @Override
    public void runOpMode() {
        frontLeftDrive = hardwareMap.get(DcMotor.class, "fl");
        frontRightDrive = hardwareMap.get(DcMotor.class, "fr");
        backLeftDrive = hardwareMap.get(DcMotor.class, "bl");
        backRightDrive = hardwareMap.get(DcMotor.class, "br");
        imu = hardwareMap.get(BNO055IMU.class, "imu");


        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.FORWARD);

        frontRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        BNO055IMU.Parameters imuParameters = new BNO055IMU.Parameters();
        imuParameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imuParameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        imuParameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        imuParameters.loggingEnabled = true;
        imuParameters.loggingTag = "IMU";
        imuParameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu.initialize(imuParameters);


        initializeRobot();

        waitForStart();

        startRobot();
    }
    public void startRobot() {
        while (opModeIsActive())
        {
            cycle();
        }
        sleep(250);
    }
    public void initializeRobot() {

    }


    protected void setDrive(double forwardsPower, double turnPower, double strafePower) {
        frontRightDrive.setPower(forwardsPower - turnPower - strafePower);
        backRightDrive.setPower(forwardsPower - turnPower + strafePower);
        frontLeftDrive.setPower(forwardsPower + turnPower + strafePower);
        backLeftDrive.setPower(forwardsPower + turnPower - strafePower);
    }

    protected double controllerThreshold(double number) {
        if (Math.abs(number) <= JOYSTICK_THRESHOLD) {
            return 0.0;
        } else {
            return number;
        }
    }

    protected float calculateHeading() {
        try {
            Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
            float heading = angles.firstAngle - zeroFactor;
            while (heading > 180.0f)
                heading -= 360.0f;
            while (heading < -180.0f)
                heading += 360.0f;
            return heading;
        } catch (Exception e) {
            telemetry.addLine("calculateHeading Failed.");
            telemetry.update();
            sleep(2000);
            e.printStackTrace();
            return 0.0f;
        }


    }
    public void cycle() {

    }


}
