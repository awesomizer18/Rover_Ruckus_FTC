package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by user on 7/10/2018.
 */
// TODO: When creating a PID_Controller object, it doesn't accept Gains like it used to
public class PID_Controller{
    ElapsedTime runtime;

    private double setpoint = 0;
    private double error = 0;
    private double lastError = 0;
    private double time = 0;
    private double lastTime = 0;
    private double pValue = 0;
    private double PGAIN;
    private double iValue = 0;
    private double IGAIN;
    private double dValue = 0.0;
    private double DGAIN;
    private boolean isFirstTime = true;
    // TODO: Either implement CONVERSION_RATE or remove it if the feature is forgotten.
    private double CONVERSION_RATE;

    public void PID_Loop(double PGAIN, double IGAIN, double DGAIN){
        this.runtime = new ElapsedTime();
        // TODO: Make the constructor fully reset the controller
        this.runtime.reset();
        this.PGAIN = PGAIN;
        this.IGAIN = IGAIN;
        this.DGAIN = DGAIN;
    }

    public void setSetpoint(double newSetpoint) {
        this.setpoint = newSetpoint;
    }

    public double update(double input){
        lastError = error;
        error = setpoint - input;
        time = runtime.seconds();
        pValue = PGAIN * error;
        iValue += IGAIN * (lastError + error) * (0.5) * (time - lastTime);
        //dValue
        dValue = DGAIN * (error - lastError) / (time - lastTime);
        if (isFirstTime)
        {
            iValue = 0.0;
            isFirstTime = false;
        }
        return pValue + iValue + dValue;
    }

    public void resetPID() {
        resetPID(0.0);
    }

    public void resetPID(double startingIValue) {
        runtime.reset();
        isFirstTime = true;
        iValue = startingIValue;
    }

}