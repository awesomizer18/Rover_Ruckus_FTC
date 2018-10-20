package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp(name="Brian's TeleOp Test")
public class Brian_TeleOp_Test extends OpMode {

    private Servo intakeServo;
    private DcMotor Left;
    private DcMotor Right;

    @Override
    public void init() {

        Left = hardwareMap.get(DcMotor.class,"l");
        Right = hardwareMap.get(DcMotor.class,"r");
        intakeServo = hardwareMap.get(Servo.class,"i");


        Left.setDirection(DcMotor.Direction.REVERSE);
        Right.setDirection(DcMotor.Direction.FORWARD);
        intakeServo.setDirection(Servo.Direction.FORWARD);


        Left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        gamepad1.setJoystickDeadzone(0.1f);
    }

    @Override
    public void loop() {
        double forward = -gamepad1.left_stick_y;
        double turnPower = gamepad1.left_stick_x;

        if (gamepad1.dpad_left) {

            intakeServo.setPosition(-1);

        else (gamepad1.dpad_right)

                intakeServo.setPosition(1);

            else(gamepad1.dpad_down)
                intakeServo.setPosition(0);
    }
        setDrive(forward, turnPower);



    }
    private void setDrive(double forward, double turnPower){

        Left.setPower(forward + turnPower);
        Right.setPower(forward - turnPower);

    }
}
