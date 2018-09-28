package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Andrew Intake Program",group = "")
public class AndrewIntakeProgram extends OpMode{

    private CRServo leftIntake;
    private CRServo rightIntake;

    @Override
    public void init() {
        leftIntake = hardwareMap.get(CRServo.class, "li");
        rightIntake = hardwareMap.get(CRServo.class, "ri");

        leftIntake.setDirection(CRServo.Direction.FORWARD);
        rightIntake.setDirection(CRServo.Direction.REVERSE);

        gamepad1.setJoystickDeadzone(0.1f);
    }

    @Override
    public void loop() {
        double intakePower = -gamepad1.left_stick_y;

        leftIntake.setPower(intakePower);
        rightIntake.setPower(intakePower);
    }
}
