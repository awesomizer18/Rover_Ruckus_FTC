package org.firstinspires.ftc.teamcode;

public class FDepot_Marker_ParkBack extends Autonomous_Parent{
    @Override
    public void setup() {

    }

    @Override
    public void begin() {
        driveToDepot(false);
        placeTeamMarker();
        driveFromDepotToPark(false);
    }
}
