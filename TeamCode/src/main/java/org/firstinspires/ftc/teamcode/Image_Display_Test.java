package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;
import org.opencv.core.Mat;

@TeleOp(name = "Image_Display_Test", group = "main")
public class Image_Display_Test extends OpMode {
    private Gold_Detector detector;


    @Override
    public void init() {
        telemetry.addData("Status", "Gold_Test");

        detector = new Gold_Detector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();
        detector.reducedImageRatio = 0.4;
        detector.triangulator.perfectRatio = 5;
        detector.triangulator.saturation = 1.0;
        detector.enable();


    }

    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

    }


    @Override
    public void loop() {
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
        detector.disable();
    }

}

