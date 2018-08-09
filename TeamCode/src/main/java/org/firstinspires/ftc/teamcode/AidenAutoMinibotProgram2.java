package org.firstinspires.ftc.teamcode;

public class AidenAutoMinibotProgram2 extends AidenMinibotAuto {
@Override
    public void runAuto() {
setDrive(1.0,1.0);

sleep(3000);

setDrive(1.0, -1.0);

sleep(3000);

setDrive(-1.0, 1.0);

sleep(3000);

setDrive(-1.0, -1.0);

sleep(3000);

    }
}