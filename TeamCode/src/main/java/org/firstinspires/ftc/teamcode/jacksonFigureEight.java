package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous (name = ":D", group = "")
public class jacksonFigureEight extends jacksonMinibotAuto {
    @Override
    public void runAuto() {
        figureEight();
        figureEight();

    }

    private void figureEight() {
        setDrive(1.0,1.0);

        sleep(900);

        setDrive(1.0, -0.2);

        sleep(600);

        setDrive(1.0,1.0);

        sleep(900);

        setDrive(1.0, -0.2);

        sleep(600);

    }
}
