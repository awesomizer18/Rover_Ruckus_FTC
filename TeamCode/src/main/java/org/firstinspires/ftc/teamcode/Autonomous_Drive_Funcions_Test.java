package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Drive Functions Test", group = "A")
public class Autonomous_Drive_Funcions_Test extends Autonomous_Parent{
    @Override
    public void go() {
        super.go();
        driveForwardNorm(12);
        driveBackwardNorm(12);
        driveTurnLeftNorm(90);
        driveTurnRightNorm(90);
        driveForwardPID(12);
        driveBackwardPID(12);

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
