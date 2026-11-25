package org.firstinspires.ftc.teamcode.subsystem;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

@Configurable
public class IntakeSubsystem extends SubsystemBase {
    private final DcMotorEx intakeTheRickrollMotor;
    public static double INTAKE_SPEED=.9;
    public static double OUTTAKE_SPEED=-.9;
    public IntakeSubsystem(HardwareMap hm) {
        this.intakeTheRickrollMotor=hm.get(DcMotorEx.class,"intake");

    }

    public void intake(){
        intakeTheRickrollMotor.setPower(INTAKE_SPEED);
    }
    public void outtake(){
        intakeTheRickrollMotor.setPower(OUTTAKE_SPEED);
    }
    public void em_stop(){
        intakeTheRickrollMotor.setPower(0);
    }
}
