package org.firstinspires.ftc.teamcode;

import static com.sun.tools.doclint.Entity.and;
import static com.sun.tools.doclint.Entity.or;
import static com.sun.tools.doclint.Entity.thorn;

public abstract class Autonomous_Parent extends Robot_Parent {

    protected PID_Controller forwardPID = new PID_Controller(0.071, 0.0, 0.0);
    protected PID_Controller strafePID = new PID_Controller(0.071, 0.0, 0.0);
    protected PID_Controller turnPID = new PID_Controller(0.025, 0.0, 0.0);

    private final double EC_PER_IN = 104.7;
    private final double SECONDS_PER_IN = 0.16;
    private final long CYCLE_TIME_MS = 5;

    @Override
    public void getReady() {

    }

    @Override
    public void go() {

    }

    protected double getForwardPosition() {
        double position = backLeftDrive.getCurrentPosition() + backRightDrive.getCurrentPosition() +
                frontLeftDrive.getCurrentPosition() + frontRightDrive.getCurrentPosition();
        position /= 4.0;
        position /= EC_PER_IN;
        return position;
    }

    protected double getStrafePosition() {
        double position = -backLeftDrive.getCurrentPosition() + backRightDrive.getCurrentPosition()
                + frontLeftDrive.getCurrentPosition() - frontRightDrive.getCurrentPosition();
        position /= 4.0;
        position /= EC_PER_IN;
        return position;
    }

    protected void driveForwardPID(double inches) {
        forwardPID.setSetpoint(getForwardPosition() + inches);
        holdTurnPID.setSetpoint(getTurnPosition());
        int iterations = 0;
        int numIterations = (int) (SECONDS_PER_IN * 1000.0 * inches / CYCLE_TIME_MS);
        while (opModeIsActive() && (iterations <= numIterations)) {
            setDrive(forwardPID.update(getForwardPosition()), holdTurnPID.update(getTurnPosition()), 0.0);
            iterations++;
            sleep(CYCLE_TIME_MS);
        }
        setDrive(0.0, 0.0, 0.0);
    }
    protected void driveBackwardPID(double inches) {
        forwardPID.setSetpoint(getForwardPosition() - inches);
        holdTurnPID.setSetpoint(getTurnPosition());
        int iterations = 0;
        int numIterations = (int) (SECONDS_PER_IN * 1000.0 * inches / CYCLE_TIME_MS);
        while (opModeIsActive() && (iterations <= numIterations)) {
            setDrive(forwardPID.update(getForwardPosition()), holdTurnPID.update(getTurnPosition()), 0.0);
            iterations++;
            sleep(CYCLE_TIME_MS);
        }
        setDrive(0.0, 0.0, 0.0);
    }

    protected void driveForwardNorm(double inches) {
        double finalDistance = getForwardPosition() + inches;
        while (getForwardPosition() < finalDistance && opModeIsActive()) {
            setDrive(1.0, 0.0, 0.0);
        }
        setDrive(0.0, 0.0, 0.0);
    }

    protected void driveTurnRightNorm(double degrees) {
        double finalDistance = getTurnPosition() + degrees;
        while (getForwardPosition() < finalDistance && opModeIsActive()) {
            setDrive(0.0, 1.0, 0.0);
        }
        setDrive(0.0, 0.0, 0.0);
    }

    protected void driveBackwardNorm(double inches) {
        double finalDistance = getForwardPosition() - inches;
        while (getForwardPosition() > finalDistance && opModeIsActive()) {
            setDrive(-1.0, 0.0, 0.0);
        }
        setDrive(0.0, 0.0, 0.0);
    }

    protected void driveTurnLeftNorm(double degrees) {
        double finalDistance = getTurnPosition() - degrees;
        while (getForwardPosition() > finalDistance && opModeIsActive()) {
            setDrive(0.0, -1.0, 0.0);
        }
    }
}