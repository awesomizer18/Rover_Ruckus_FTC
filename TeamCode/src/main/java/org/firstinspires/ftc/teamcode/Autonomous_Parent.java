package org.firstinspires.ftc.teamcode;

public abstract class Autonomous_Parent extends Robot_Parent {

    // TODO: remember to set values to diff number
    private final double ENCODER_COUNTSPER_INCH = 81.19;

    private PID_Controller forwardPid = new PID_Controller(0.071,0.0,0.0);
    private PID_Controller strafePid = new PID_Controller(0.071,0.0,0.0);

    @Override
    public void initialize()
    {
        setup();
    }

    @Override
    public void play()
    {
        begin();
    }

    private double getForwardPosition() {
        double position = backLeftDrive.getCurrentPosition() +
                frontLeftDrive.getCurrentPosition() + frontRightDrive.getCurrentPosition() +
                backRightDrive.getCurrentPosition();

        position /= 4.0;
        position /= ENCODER_COUNTSPER_INCH;

        return position;
    }

    private double getStrafePosition() {
        double position = -backLeftDrive.getCurrentPosition() +
                frontLeftDrive.getCurrentPosition() - frontRightDrive.getCurrentPosition() +
                backRightDrive.getCurrentPosition();

        position /= 4.0;
        position /= ENCODER_COUNTSPER_INCH;

        return position;
    }
}