package org.firstinspires.ftc.teamcode;

//import com.qualcomm.hardware.bosch

public class Heading {
    private static float fieldOffset;

    private float relativeOffset;

    public static float getFieldOffset(){
        return fieldOffset;
    }
    public static void setFieldOffset(float fieldOffset){
        Heading.fieldOffset = fieldOffset;
    }
    public static float getAbsoluteHeading(){
        return 0f;
    }

    public void setRelativeOffset(float relativeOffset){
        this.relativeOffset = relativeOffset;
    }

}
