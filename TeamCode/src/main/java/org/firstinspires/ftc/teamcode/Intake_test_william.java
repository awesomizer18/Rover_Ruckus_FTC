package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.sun.org.apache.xml.internal.security.Init;

@TeleOp(name = "IntakeTestW", group = "A")
public class Intake_test_william extends OpMode {
    private DcMotor leftIntake = null;
    private DcMotor rightIntake = null;

    @Override
    public void init() {
        leftIntake = hardwareMap.get(DcMotor.class,"lint");
        rightIntake = hardwareMap.get(DcMotor.class, "rint");

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

