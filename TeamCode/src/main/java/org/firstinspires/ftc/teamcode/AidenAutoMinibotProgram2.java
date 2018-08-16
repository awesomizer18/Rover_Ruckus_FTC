package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Aiden Auto Minibot Program 2",group = "")
public class AidenAutoMinibotProgram2 extends AidenMinibotAuto {
@Override
    public void runAuto() {
        setDrive(1.0,1.0);

        sleep(1000);

        setDrive(1.0, -1.0);

        sleep(250);

        setDrive(1.0, 1.0);

        sleep(1000);

        setDrive(1.0, -1.0);

        sleep(250);

        setDrive(1.0, 1.0);

        sleep(1000);

        setDrive(1.0, -1.0);

        sleep(250);

        setDrive(1.0, 1.0);

        sleep(1000);

        setDrive(-1.0, 1.0);

        sleep(250);

        setDrive(1.0, 1.0);

        sleep(1000);

        setDrive(-1.0, 1.0);

        sleep(250);

        setDrive(1.0, 1.0);

        sleep(1000);

        setDrive(-1.0, 1.0);

        sleep(250);

        setDrive(1.0, 1.0);

        sleep(1000);



    }
}