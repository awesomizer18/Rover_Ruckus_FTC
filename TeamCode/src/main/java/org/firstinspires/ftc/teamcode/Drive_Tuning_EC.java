package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "PID Test", group = "A")
public class Drive_Tuning_EC extends Autonomous_Parent {

    @Override
    public void go() {
        super.go();
        holdTurnPID.setSetpoint(getTurnPosition());
        forwardPID.setSetpoint(getForwardPosition() + 60.0);
        for (int i = 0; i < 200; i++)
        {
            double forwardPower = forwardPID.update(getForwardPosition());
            double turnPower = holdTurnPID.update(getTurnPosition());
            setDrive(forwardPower, turnPower, 0.0);
            sleep(15);
        }

        setDrive(0.0, 0.0, 0.0);
        sleep(1000);

        strafePID.setSetpoint(getStrafePosition() - 60.0);
        for (int i = 0; i < 200; i++)
        {
            double strafePower = strafePID.update(getStrafePosition());
            double turnPower = holdTurnPID.update(getTurnPosition());
            setDrive(0.0, turnPower, strafePower);
            sleep(15);
        }
        setDrive(0.0, 0.0, 0.0);
    }
}
