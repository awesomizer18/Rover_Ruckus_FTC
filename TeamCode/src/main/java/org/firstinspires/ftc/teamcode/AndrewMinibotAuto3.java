package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous (name = "andrew Minibot figurate", group = "")
public class AndrewMinibotAuto3 extends andrewMinibotAuto {
    DcMotor leftDrive;
    DcMotor rightDrive;

  @Override
    public void runAuto() {
      // move forward
      setDrive( 1.0, 1.0 );

      sleep(1000);
      // turn right
      setDrive( 1.0, -1.0);

      sleep(1000);
      // move forward
      setDrive( 1.0, 1.0);

      sleep( 1000);
      //turn right
      setDrive( 1.0, -1.0);

      sleep( 1000);
      //move forward
      setDrive( 1.0, 1.0);

      sleep( 1000);
      //turn right
      setDrive( 1.0, -1.0);

      sleep( 1000);
      //move forward
      setDrive( 1.0, 1.0);

      sleep( 1000);
      //turn right
      setDrive( 1.0, -1.0);

      sleep( 1000);
      //move forward
      setDrive( 1.0, 1.0);

      sleep( 1000);
      //turn right
      setDrive( 1.0, -1.0);

      sleep( 1000);
      //move forward
      setDrive( 1.0, 1.0);

      sleep( 1000);
      //turn right
      setDrive( 1.0, -1.0);

      sleep( 1000);
      //move forward
      setDrive( 1.0, 1.0);

      sleep( 1000);
    }
}
