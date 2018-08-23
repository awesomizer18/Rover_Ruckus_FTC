package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Aiden Auto Minibot Figure-8",group = "")
public class AidenAutoMinibotProgram2 extends AidenMinibotAuto {
@Override
    public void runAuto() {
        setDrive(1.0,1.0);

        sleep(1000);

        setDrive(1.0, -1.0);

        sleep(215);

        setDrive(1.0, 1.0);

        sleep(1000);

        setDrive(1.0, -1.0);

        sleep(215);

        setDrive(1.0, 1.0);

        sleep(1000);

        setDrive(1.0, -1.0);

        sleep(215);

        setDrive(1.0, 1.0);

        sleep(1000);

        setDrive(-1.0, 1.0);

        sleep(215);

        setDrive(1.0, 1.0);

        sleep(1000);

        setDrive(-1.0, 1.0);

        sleep(215);

        setDrive(1.0, 1.0);

        sleep(1000);

        setDrive(-1.0, 1.0);

        sleep(215);

        setDrive(1.0, 1.0);

        sleep(1000);



    }
}