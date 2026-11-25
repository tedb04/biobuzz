package org.firstinspires.ftc.teamcode.subsystem;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

@Configurable
public class RampDoorSubsystem extends SubsystemBase {
    public static double DOOROPEN = .9;
    public static double DOORCLOSED = 0;
    private Servo kingBobvexethe5thsShooterDoor;
    public RampDoorSubsystem(HardwareMap hm) {
        this.kingBobvexethe5thsShooterDoor=hm.get(Servo.class,"sdoor");
        closed();
    }
    public void open (){
        kingBobvexethe5thsShooterDoor.setPosition(DOOROPEN);

    }
    public void closed () {
        kingBobvexethe5thsShooterDoor.setPosition(DOORCLOSED);
    }
}
