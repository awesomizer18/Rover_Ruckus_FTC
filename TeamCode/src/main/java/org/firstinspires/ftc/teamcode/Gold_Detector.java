package org.firstinspires.ftc.teamcode;

import com.sun.tools.javac.util.Position;

import org.opencv.android.CameraBridgeViewBase;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Gold_Detector extends Base_Detector {
    private Mat displayMat = new Mat();//will be displayed on the phone
    private Mat mainMat = new Mat();//will worked on the most
    private Mat yellowMat = new Mat();//yellow returned by
    private Mat countorMat = new Mat();

    private boolean found = false;//If we've seen the gold yet
    private Point screenPosition = new Point();//coordinates of gold
    private Rect goldRect = new Rect();//sets up rectangle around gold
    private YellowColorFilter yellowColorFilter = new YellowColorFilter();

    public Image_Triangulator triangulator = new Image_Triangulator( 1.0,3);


    //HSV values for yellow

    public Gold_Detector() {
        super();
        detectorName = "Gold_Detector";
    }


    @Override
    public Mat process(Mat input) {

        input.copyTo(displayMat);
        input.copyTo(mainMat);
        input.release();

        Imgproc.GaussianBlur(mainMat, mainMat, new Size(5, 5), 0);
        yellowColorFilter.process(mainMat.clone(), yellowMat);

        List<MatOfPoint> contoursYellow = new ArrayList<>();
        Imgproc.findContours(yellowMat, contoursYellow, countorMat, Imgproc.RETR_TREE, Imgproc.
                CHAIN_APPROX_SIMPLE);
        Imgproc.drawContours(displayMat, contoursYellow, -1, new Scalar(230, 70, 70),
                2);

        Rect bestRect = null;
        double bestDifference = Double.MAX_VALUE;

        for (MatOfPoint cont : contoursYellow) {
            double score = calculateScore(cont); // Get the diffrence score using the scoring API

            // Get bounding rect of contour
            Rect rect = Imgproc.boundingRect(cont);
            Imgproc.rectangle(displayMat, rect.tl(), rect.br(), new Scalar(0, 0, 255), 2); // Draw rect

            // If the result is better then the previously tracked one, set this rect as the new best
            if (score < Double.MAX_VALUE) {
                score = Double.MAX_VALUE;
                bestRect = rect;
            }

        }

        if (bestRect != null) {
            Imgproc.rectangle(displayMat, bestRect.tl(), bestRect.br(), new Scalar(255, 0, 0), 4);
            Imgproc.putText(displayMat, "Chosen", bestRect.tl(), 0, 1, new Scalar(255, 255, 255));

            screenPosition = new Point(bestRect.x, bestRect.y);
            goldRect = bestRect;
            found = true;
        } else {
            found = false;
        }
        Imgproc.putText(displayMat, "Result: " + screenPosition.x + "/" +
                        screenPosition.y, new Point(10, getAdjustedSize().height - 30), 0,
                1, new Scalar(255, 255, 0), 1);
        return displayMat;
    }
    @Override
    public void useDefaults(){
        addScorer(triangulator);

    }
    public Point getScreenPosition(){
        return screenPosition;
    }

    /**
     * Returns the gold element's found rectangle
     * @return gold element rect
     */
    public Rect getFoundRect() {
        return goldRect;
    }

    /**
     * Returns if a gold mineral is being tracked/detected
     * @return if a gold mineral is being tracked/detected
     */
    public boolean isFound() {
        return found;
    }
}


