package org.firstinspires.ftc.teamcode;

import android.graphics.Color;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class YellowColorFilter extends OpenCVPipeline {
    private Imgproc image;
    private Imgproc implementedImage;
    private Mat hsv = new Mat();
    private int hsvArray;


    public YellowColorFilter(Mat image) {
        this.image = implementedImage;
    }

    @Override
    public Mat processFrame(Mat rgba, Mat gray) {
        rgba.convertTo(hsv, Imgproc.COLOR_RGB2HSV);
        //TODO: Finish putting Mat into array

        return null;//remove after compiling
    }
}