package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Aiden Auto Minibot Circle Figure-8",group = "")
public class Aiden_Figure8_Minibot_Expansion extends AidenMinibotAuto {
    @Override
    public void runAuto() {
       setDrive(1.0, 0.2);

       sleep(3000);

       setDrive(1.0,1.0);

       sleep(50);

       setDrive(0.2,1.0);

       sleep(3000);

    }
}