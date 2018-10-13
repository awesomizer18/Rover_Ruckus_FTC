package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Andrew Teleop Test")
public class Andrew_Teleop_Test extends OpMode {

   private DcMotor leftDrive;
   private DcMotor rightDrive;

   private Servo arm;


    @Override
    public void init() {
        leftDrive = hardwareMap.get(DcMotor.class, "ld");
        rightDrive = hardwareMap.get(DcMotor.class, "rd");
        arm = hardwareMap.get(Servo.class, "arm");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(Servo.Direction.FORWARD);

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm.setPosition(Servo.MAX_POSITION);

        gamepad1.setJoystickDeadzone(0.1f);
    }

    @Override
    public void loop() {
        double forwardPower = -gamepad1.left_stick_y;

        setDrive(forwardPower);

        if (gamepad1.dpad_left)
            arm.setPosition(0.5);
        else if (gamepad1.dpad_right)
            arm.setPosition(1.0);
        else
            arm.setPosition(0.0);

    }

    private void setDrive(double forwardPower) {
        leftDrive.setPower(forwardPower);
        rightDrive.setPower(forwardPower);

    }
}
