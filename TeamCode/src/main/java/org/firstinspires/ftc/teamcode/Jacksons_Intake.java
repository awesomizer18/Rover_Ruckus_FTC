package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Jackson's Teleop", group = "Main")
public class Jacksons_Intake extends OpMode {
    private DcMotor intakeNumberOne = null;
    private DcMotor intakeNumberTwo = null;

    @Override
    public void init() {
        intakeNumberOne = hardwareMap.get(DcMotor.class, "lint");
        intakeNumberTwo = hardwareMap.get(DcMotor.class, "rint");

        intakeNumberOne.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeNumberTwo.setDirection(DcMotorSimple.Direction.REVERSE);

        intakeNumberTwo.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeNumberOne.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        gamepad1.setJoystickDeadzone(0.1f);
    }
    @Override
    public void loop()  {
        double intakePower = -gamepad1.left_stick_y;

        intakeNumberOne.setPower(intakePower);
        intakeNumberTwo.setPower(intakePower);

    }




}
