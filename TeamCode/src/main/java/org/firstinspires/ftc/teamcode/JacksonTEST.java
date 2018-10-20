package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp (name = "Jackson's code", group = "")
public class JacksonTEST extends OpMode {
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private Servo servoOne = null;


    @Override
    public void init() {
        backLeftDrive = hardwareMap.get(DcMotor.class, "l");
        backRightDrive = hardwareMap.get(DcMotor.class, "r");
        servoOne = hardwareMap.get(Servo.class, "i");

        backLeftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeftDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        backRightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        servoOne.setDirection(Servo.Direction.FORWARD);

        gamepad1.setJoystickDeadzone((float) 0.1);
    }

    @Override
    public void loop() {
        double forwardPower = -gamepad1.left_stick_y;
        double turnPower = gamepad1.right_stick_x;

        if (gamepad1.right_trigger > 0.5f)
            servoOne.setPosition(1.0);
        else if (gamepad1.left_trigger > 0.5f)
            servoOne.setPosition(0.0);
        else if (gamepad1.right_trigger > 0.5f, gamepad1.left_trigger > 0.5f)
            servoOne.setPosition(0.5);
        else if(gamepad1.right_trigger > 0.1f, gamepad1.left_trigger > 0.1f)
            servoOne.setPosition(0.5);



        setDrive(forwardPower, turnPower);
    }

    private void setDrive(double forwardPower, double turnPower) {
        backLeftDrive.setPower(forwardPower + turnPower);
        backRightDrive.setPower(forwardPower - turnPower);


    }
}
