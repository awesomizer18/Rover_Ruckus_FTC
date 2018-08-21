package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "andrew Minibot Sensors", group = "")
public class AndrewMinibot_Sensors extends andrewMinibotAuto {

    private final double INCHES_PER_SECOND = 26.0;

    @Override
    public void runAuto() {
        moveForward(72.0);
    }

    public void moveForward(double inches) {
        setDrive( 1.0, 1.0 );
        sleep((long) (1000.0 * inches / INCHES_PER_SECOND));
    }

}
