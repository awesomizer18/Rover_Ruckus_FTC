package org.firstinspires.ftc.teamcode;

public class Jakes_MiniBot_Auto extends Jakes_Auto_Minibot {
    @Override
    public void runAuto() {
        setDrive(1.0,1.0);

        sleep(5000);

        setDrive(1.0,0.0);

        sleep(3456);

        setDrive(0.0,1.0);

        sleep(3456);
    }
}
