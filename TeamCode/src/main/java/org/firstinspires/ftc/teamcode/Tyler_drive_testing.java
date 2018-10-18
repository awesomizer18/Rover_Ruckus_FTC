package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Tyler_drive_testing",group = "")

public class Tyler_drive_testing extends OpMode {

    private Servo botArm;

    private double newPosition;

  @Override
    public void init() {

      botArm = hardwareMap.get(Servo.class,"ba");

      botArm.setPosition(0.00);
    }

    public void loop() {

      if (gamepad1.dpad_right)
          newPosition = botArm.getPosition() + 0.5;

      if (newPosition > 1.0) botArm.setPosition(1.0);

      if (gamepad1.dpad_left)
          newPosition = botArm.getPosition() - 0.5;

      if (newPosition < 0.0) botArm.setPosition(0.0);
    }
}