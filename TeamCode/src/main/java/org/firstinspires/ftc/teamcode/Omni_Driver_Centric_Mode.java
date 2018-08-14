package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="Driver Centric Mode Test", group="Competition")//Competition/Main
public class Omni_Driver_Centric_Mode extends Omni_Parent
{
    private double correctionValueInDegrees;

    @Override
    public void initializeRobot() {
        correctionValueInDegrees = calculateHeading();
    }

    public void cycle() {
        double leftPower;
        double rightPower;


        //leftPower = -controllerThreshold(gamepad1.left_stick_y);
        //rightPower = -controllerThreshold(gamepad1.right_stick_y);

        double away = -controllerThreshold(gamepad1.left_stick_y);
        double side  = controllerThreshold(gamepad1.left_stick_x);
        double turn  =  controllerThreshold(gamepad1.right_stick_x);
        double directionOfJoystick = Math.toDegrees(Math.atan2(side, away));

        double heading = -1 * calculateHeading();
        double robotOrientedCommand = directionOfJoystick - heading;

        double inputSize = Math.sqrt(Math.pow(away, 2) + Math.pow(side, 2));
        double strafe = inputSize * Math.sin(Math.toRadians(robotOrientedCommand));
        double forward =  inputSize * Math.cos(Math.toRadians(robotOrientedCommand));

        setDrive(forward, turn, strafe);

        telemetry.addData("directionOfJoystick",directionOfJoystick);
        telemetry.addData("robotOrientedCommand", robotOrientedCommand);
        telemetry.addData("Heading", heading);
        telemetry.addData("Stafe", strafe);
        telemetry.addData("Forward", forward);
        telemetry.update();

    }
}
