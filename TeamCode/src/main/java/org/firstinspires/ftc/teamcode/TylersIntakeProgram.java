package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Tyler's Intake Test ",group = "")
public class TylersIntakeProgram extends OpMode {

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

        leftIntake.setPower(intakePower);
        rightIntake.setPower(intakePower);
    }
}