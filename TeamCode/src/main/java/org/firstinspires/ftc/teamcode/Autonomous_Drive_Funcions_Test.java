package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Drive Functions Test", group = "A")
public class Autonomous_Drive_Funcions_Test extends Autonomous_Parent{
    @Override
    public void go() {
        super.go();
        driveForwardNorm(12);
        while(!gamepad1.x);while(gamepad1.x);
        driveBackwardNorm(12);
        while(!gamepad1.x);while(gamepad1.x);
        driveTurnLeftNorm(90);
        while(!gamepad1.x);while(gamepad1.x);
        driveTurnRightNorm(90);
        while(!gamepad1.x);while(gamepad1.x);
        driveForwardPID(12);
        while(!gamepad1.x);while(gamepad1.x);
        driveBackwardPID(12);
        while(!gamepad1.x);while(gamepad1.x);

        driveForward(1.0);
        while(!gamepad1.x);while(gamepad1.x);
        driveForward(-1.0);
        while(!gamepad1.x);while(gamepad1.x);
        driveStrafe(1.0);
        while(!gamepad1.x);while(gamepad1.x);
        driveStrafe(-1.0);
        while(!gamepad1.x);while(gamepad1.x);


    }
}
