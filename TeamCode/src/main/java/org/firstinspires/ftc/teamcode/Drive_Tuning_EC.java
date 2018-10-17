package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Encoder Measuring Code", group = "A")
public class Drive_Tuning_EC extends Autonomous_Parent {

    @Override
    public void go() {
        super.go();
        forwardPID.setSetpoint(getForwardPosition() + 60.0);
        while (opModeIsActive())
        {
            double forwardPower = forwardPID.update(getForwardPosition());
            setDrive(forwardPower, 0.0, 0.0);
        }
    }
}
