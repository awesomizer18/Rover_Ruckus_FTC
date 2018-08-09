package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous (name = "AidenAutoProgram", group = "")
public class AidenMinibotAuto extends LinearOpMode {
    DcMotor LeftDrive;
    DcMotor RightDrive;
    @Override
    public void runOpMode() throws InterruptedException {
        LeftDrive = hardwareMap.get(DcMotor.class,"left");
        RightDrive = hardwareMap.get(DcMotor.class,"right");

        LeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        RightDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        LeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();

        LeftDrive.setPower(1.0);
        RightDrive.setPower(1.0);

        wait(3000);

        LeftDrive.setPower(1.0);
        RightDrive.setPower(-1.0);

        wait(2999);



    }
}