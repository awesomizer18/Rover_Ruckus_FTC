package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;

@Autonomous (name = "Color Sensor Test")
public class Color_Sensor_Test extends OpMode {

    ColorSensor senseColor;

    @Override
    public void init() {

        senseColor = hardwareMap.get(ColorSensor.class, "Sense Color");

    }


    @Override
    public void loop() {

        telemetry.addData("Red  ", senseColor.red());
        telemetry.addData("Green", senseColor.green());
        telemetry.addData("Blue ", senseColor.blue());
        telemetry.update();
    }
}
