package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "William's Autonomous Test", group = "A")
public class William_Autonomous_Test extends Autonomous_Parent{

    @Override
    public void go() {
        setDrive(1.0, 0.0, 0.0);
        sleep(1000);
        setDrive(0.0,1.0,0.0);
        sleep(1000);
        setDrive(0.0,0.0,1.0);
        sleep(1000);
        setDrive(0.0,0.0,0.0);
    }
}
