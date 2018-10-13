package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Williams Minipot Controller",group = "A")
public class William_Minibot_Test extends OpMode{
    private DcMotor right;
    private DcMotor left;
    private Servo centerArm;
    double armPos = 0.5;

    @Override
    public void init() {
        right = hardwareMap.get(DcMotor.class, "r");
        left = hardwareMap.get(DcMotor.class, "l");
        centerArm = hardwareMap.get(Servo.class, "ca");

        left.setDirection(DcMotor.Direction.FORWARD);
        right.setDirection(DcMotor.Direction.REVERSE);
        centerArm.setDirection(Servo.Direction.FORWARD);

        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        gamepad1.setJoystickDeadzone(0.1f);
    }

    @Override
    public void loop() {
        double forwardPower = -gamepad1.left_stick_y;
        double turnPower = gamepad1.right_stick_x;

        left.setPower(forwardPower - turnPower);
        right.setPower(turnPower - forwardPower);

        if (gamepad1.dpad_left) {
            armPos = 0.0;
        } else if (gamepad1.dpad_right) {
            armPos = 1.0;
        } else {
            armPos = 0.5;
        }

        centerArm.setPosition(armPos);

    }



}
