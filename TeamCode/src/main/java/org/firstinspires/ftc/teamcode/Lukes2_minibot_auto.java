package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Luke_Minbot")
public class Lukes2_minibot_auto extends lukes_minibot_auto{
    @Override

    public void runAuto() {

        setDrive(1.0, 0.5);

        sleep(3500);

        setDrive(0.5,1.0);

        sleep(3500);
    }
}
