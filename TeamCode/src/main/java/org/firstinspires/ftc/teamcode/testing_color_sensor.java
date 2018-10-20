package org.firstinspires.ftc.teamcode;

import android.graphics.Color;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "Sensor: HT color", group = "Sensor")
public class testing_color_sensor extends LinearOpMode {

   private ColorSensor testing_color_sensor;

    private float[] hsvValues = {0f, 0f, 0f};

    private boolean bCurrState;


    @Override
    public void runOpMode() {
        Color.RGBToHSV(testing_color_sensor.red(), testing_color_sensor.green(),
                testing_color_sensor.blue(), hsvValues);


        telemetry.addData("Red  ", testing_color_sensor.red());
        telemetry.addData("Green", testing_color_sensor.green());
        telemetry.addData("Blue ", testing_color_sensor.blue());
        telemetry.addData("Hue", hsvValues[0]);
        telemetry.addData("Hue", hsvValues[1]);
        telemetry.addData("Hue", hsvValues[2]);

        while (opModeIsActive()) {

            bCurrState = gamepad1.x;

        }
    }
}