package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = ":(",group = "")
public class Jakes_MiniBot_Auto extends Jakes_Auto_Minibot {
    @Override
    public void runAuto() {
        setDrive(1.0,1.0);

        sleep(800);

        setDrive(1.0,-0.2);

        sleep(650);

        setDrive(1.0, 1.0);

        sleep(800);

        setDrive(1.0, -0.2);

        sleep(650);

        setDrive(1.0,1.0);

        sleep(800);

        setDrive(1.0,-0.2);

        sleep( 650);

        setDrive(1.0, 1.0);

        sleep(800);

      

    }
}
