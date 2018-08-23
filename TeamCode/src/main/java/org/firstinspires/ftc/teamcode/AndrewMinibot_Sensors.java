package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "andrew Minibot Sensors", group = "")
public class AndrewMinibot_Sensors extends andrewMinibotAuto {

    private final double INCHES_PER_SECOND = 26.0;
    private final double ENCODER_PER_INCH = 288.0 / 11.0;

    @Override
    public void runAuto() {
        moveForward(72.0);
    }

    public void moveForward(double inches) {
        setDrive( 1.0, 1.0 );
        int initialPosition = leftDrive.getCurrentPosition();
        int finalPosition = initialPosition + (int)(inches * ENCODER_PER_INCH);

        while (leftDrive.getCurrentPosition() < finalPosition ) {
            setDrive(1.0, 1.0);
        }
        setDrive(0.0, 0.0);
    }

}
