package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Disabled
@TeleOp (name = "Ben_LittleBot_Test", group = "main")
public class Ben_Drive_Train extends OpMode {
    private DcMotor leftDrive = null;
    private DcMotor rightDrive = null;
    private Servo arm = null;


    @Override
    public void init() {
        leftDrive = hardwareMap.get(DcMotor.class, "l");
        rightDrive = hardwareMap.get(DcMotor.class, "r");
        arm = hardwareMap.get(Servo.class, "i");

        leftDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        rightDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        arm.setDirection(Servo.Direction.FORWARD);

        gamepad1.setJoystickDeadzone(0.1f);

    }


    @Override
    public void loop() {
        double drive = -(gamepad1.left_stick_y);
        double strafe = (gamepad1.left_stick_x);
        double turn = (gamepad1.right_stick_x);

        if (Math.abs(drive) > Math.abs(strafe))
            setDrive(drive, turn, 0.0);
        else
            setDrive(0.0, turn, strafe);

        if (gamepad1.y){
            arm.setPosition(1.0);
        }
        else if (gamepad1.x){
            arm.setPosition(0.5);
        }
        else{
            arm.setPosition(0.0);
        }

    }
    protected void setDrive(double forwardsPower, double turnPower, double strafePower) {
        leftDrive.setPower(forwardsPower + turnPower + strafePower);
        rightDrive.setPower(forwardsPower + turnPower - strafePower);
    }

}
