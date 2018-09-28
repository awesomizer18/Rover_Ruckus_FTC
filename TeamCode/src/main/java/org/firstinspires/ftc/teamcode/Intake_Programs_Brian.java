package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Brian Intake Test",group = "")
public class Intake_Programs_Brian extends OpMode{

    private final double INTAKE_MULTIPLIER = 1.0;
    private DcMotor leftIntake;
    private DcMotor rightIntake;

    @Override
    public void init() {
        leftIntake = hardwareMap.get(DcMotor.class,"li");
        rightIntake = hardwareMap.get(DcMotor.class,"ri");

        leftIntake.setDirection(DcMotor.Direction.FORWARD);
        rightIntake.setDirection(DcMotor.Direction.REVERSE);

        leftIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        gamepad1.setJoystickDeadzone(0.1f);
    }

    @Override
    public void loop() {
        double intakePower = -gamepad1.right_stick_y;
        intakePower *= INTAKE_MULTIPLIER;

        leftIntake.setPower(intakePower);
        rightIntake.setPower(intakePower);

        telemetry.addData("Intake Power",intakePower);
        telemetry.update();
    }
}
