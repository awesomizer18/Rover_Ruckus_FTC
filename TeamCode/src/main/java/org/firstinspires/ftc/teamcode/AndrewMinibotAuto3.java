package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "andrew Minibot figurate", group = "")
public class AndrewMinibotAuto3 extends andrewMinibotAuto {

    @Override
    public void runAuto() {
        moveForward();
        turnRight();
        moveForward();
        turnRight();
        moveForward();
        turnRight();
        moveForward();
        turnLeft();
        moveForward();
        turnLeft();
        moveForward();
        turnLeft();
        moveForward();
    }

    public void moveForward() {
        setDrive( 1.0, 1.0 );
        sleep(1000);
        brake();
    }

    public void turnLeft() {
        setDrive(-1.0, 1.0);
        sleep(333);
        brake();
    }

    public void turnRight() {
        setDrive(1.0, -1.0);
        sleep(333);
        brake();
    }

    public void brake() {
        setDrive(0.0, 0.0);
        sleep(333);
    }
}
