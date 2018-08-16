package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="0")
public class Lukes2_minibot_auto extends lukes_minibot_auto{
    @Override

    public void runAuto() {
        setDrive(1.0,1.0);
        sleep(1500);
        setDrive(-1.0,1.0);
        sleep(100);
        setDrive(1,1);
        sleep(750);
        setDrive(-1,1);
        sleep(100);
        setDrive(1,1);
        sleep(1500);
        setDrive(-1,1);
        sleep(100);
        setDrive(1,1);
        sleep(750);
        setDrive(0,0);
    }
}
