package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Cameron Auto Minibot Program 2",group = "")
public class CameronAutoMinibotProgram2 extends CameronMinibotAuto {
    @Override
    public void runAuto() {

    //will do a figure 8

        setDrive(1.0,1.0);

        sleep(300);

        setDrive(1.0, -1.0);

        sleep(300);

        setDrive(1.0, 1.0);

        sleep(300);

        setDrive(-1.0, 1.0);

        sleep(300);

        setDrive( 1.0, 1.0);

        sleep(300);

        setDrive( -1.0, 1.0);

        sleep(300);

        setDrive( 1.0, 1.0);

        sleep(300);

        setDrive( -1.0, 1.0);

        sleep(300);

        setDrive( 1.0, 1.0);

        sleep(300);

        setDrive( -1.0, 1.0);

        sleep(300);

        setDrive( 1.0, 1.0);

        sleep(300);

        setDrive( 1.0, -1.0);

        sleep(300);

        setDrive( 1.0, 1.0);

        sleep(300);

        setDrive( 1.0, -1.0);

        sleep(300);

        setDrive( 1.0, 1.0);

        sleep(300);

        setDrive( 1.0, -1.0);

        sleep(300);

    }
}