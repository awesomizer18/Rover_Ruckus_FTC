package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous (name = "andrew Minibot", group = "")
public class andrewMinibotAuto extends LinearOpMode {

    DcMotor leftDrive;
    DcMotor rightDrive;

    @Override
    public void runOpMode() throws InterruptedException {
        leftDrive = hardwareMap.get(DcMotor.class,"left");
        rightDrive = hardwareMap.get(DcMotor.class,"right");

        leftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        waitForStart();

        leftDrive.setPower(1.0);
        rightDrive.setPower(1.0);

        sleep (3000);

        leftDrive.setPower(1.0);
        rightDrive.setPower(-1.0);

        sleep ( 1500);
    }
}
