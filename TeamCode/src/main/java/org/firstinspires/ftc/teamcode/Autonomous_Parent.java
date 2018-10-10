package org.firstinspires.ftc.teamcode;

public abstract class Autonomous_Parent extends Robot_Parent {

    private PID_Controller forwardPID = new PID_Controller(0.071, 0.0, 0.0);
    private PID_Controller strafePID = new PID_Controller(0.071,0.0,0.0);
    private PID_Controller turnPID = new PID_Controller(0.025, 0.0, 0.0);

    private final double EC_PER_IN = 81.19;

    @Override
    public void getReady() {

    }

    @Override
    public void go() {

    }

    private double getForwardPosition() {
        double position = backLeftDrive.getCurrentPosition() + backRightDrive.getCurrentPosition() +
                frontLeftDrive.getCurrentPosition() + frontRightDrive.getCurrentPosition();
        position /= 4.0;
        position /= EC_PER_IN;
        return position;
    }

    private double getStrafePosition(){
        double position = -backLeftDrive.getCurrentPosition() + backRightDrive.getCurrentPosition()
                + frontLeftDrive.getCurrentPosition() - frontRightDrive.getCurrentPosition();
        position /= 4.0;
        position /= EC_PER_IN;
        return position;
    }
}
