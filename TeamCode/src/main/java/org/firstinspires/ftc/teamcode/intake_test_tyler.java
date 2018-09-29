package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "intake test tyler", group = "main")
public class intake_test_tyler extends OpMode {
    private CRServo leftIntake = null;
    private CRServo rightIntake = null;

    @Override
    public void init() {
       leftIntake = hardwareMap.get(CRServo.class,"lint");
       rightIntake = hardwareMap.get(CRServo.class,"rint");

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