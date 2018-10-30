package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Drive Functions Test", group = "A")
public class Autonomous_Drive_Funcions_Test extends Autonomous_Parent{
    @Override
    public void go() {
        super.go();
        driveForwardNorm(12);
        waitSeconds(1);
        driveBackwardNorm(12);
        waitSeconds(1);
        driveTurnLeftNorm(90);
        waitSeconds(1);
        driveTurnRightNorm(90);
        waitSeconds(1);
        driveForwardPID(12);
        waitSeconds(1);
        driveBackwardPID(12);
        waitSeconds(1);

        waitSeconds(10.0);

        driveForward(1.0);
        waitSeconds(1.0);
        driveForward(-1.0);
        waitSeconds(1.0);
        driveStrafe(1.0);
        waitSeconds(1.0);
        driveStrafe(-1.0);
        waitSeconds(1.0);


    }
}
