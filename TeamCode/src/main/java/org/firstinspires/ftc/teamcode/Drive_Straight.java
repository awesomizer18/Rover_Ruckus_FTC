package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by user on 7/17/2018.
 */
@Autonomous(name="Autonomous Control Loop Test", group="Main")
public class Drive_Straight extends LinearOpMode {

    // Declare member variables
    private DcMotor leftMotor;
    private DcMotor rightMotor;

    private BNO055IMU imu;

    @Override
    public void runOpMode() {
        // Set up motors
        leftMotor = hardwareMap.get(DcMotor.class, "left");
        rightMotor = hardwareMap.get(DcMotor.class, "right");

        leftMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Initialize IMU
        setupImu();

        // Initialize PID and Heading
        // TODO: Finish
        PID_Loop(0.01, 0.01, 0.01);
        setSetpoint(calculateHeading());

        // Run forwards X feet
        // TODO: Convert old code and create method
        while (opModeIsActive() &&  (backLeftDrive.getCurrentPosition() < 7356.5)){
            double turnCorrection = update(calculateHeading());
            setDrive(1.0, -turnCorrection, 0);
        }
        if (opModeIsActive() &&  (backLeftDrive.getCurrentPosition() == 7356)){
            double turnCorrection = 0;

            setDrive(1.0, turnCorrection, 0);
        }
        setDrive(0, 0 , 0);
    }

    private void setupImu() {
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        // TODO: Finish setting up IMU
    }

}