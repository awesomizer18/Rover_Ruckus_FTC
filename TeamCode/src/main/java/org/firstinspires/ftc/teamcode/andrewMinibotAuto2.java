package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = "andrew.MinibotAuto", group = "")
public class andrewMinibotAuto2 extends andrewMinibotAuto {
    @Override
    public void runAuto() {
        setDrive(1.0,1.0);

        sleep(2500);

        setDrive(1.0, -1.0);

        sleep(3000);
    }
}
