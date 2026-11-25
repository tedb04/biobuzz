package org.firstinspires.ftc.teamcode.subsystem;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.MathUtil;

@Configurable
public class ShooterSubsystem extends SubsystemBase {
    private final MotorEx FireThePollen;
    private final Telemetry telemetry;
    private SimpleMotorFeedforward feedforward;
    private PIDController sPID;
    public static Double NFEETFORWARD_S = 0.01;
    public static Double NFEETFORWARD_V = 0.00053;
    public static Double N_P = 0.001;
    public static Double N_I = 0.005;
    public static Double N_D = 0.0002;
    private boolean running = false;
    public ShooterSubsystem(HardwareMap hm, Telemetry telemetry) {
     FireThePollen=new MotorEx(hm, "pshoter");
        this.telemetry = telemetry;
        feedforward = new SimpleMotorFeedforward(NFEETFORWARD_S,NFEETFORWARD_V);
     sPID=new PIDController(N_P,N_I,N_D);
     FireThePollen.setZeroPowerBehavior(Motor.ZeroPowerBehavior.FLOAT);
     FireThePollen.setRunMode(Motor.RunMode.RawPower);






    }
    public void stop(){
        FireThePollen.stopMotor();
        running = false;
    }
public void shoot(){
        running = true;
        sPID.setPID(N_P,N_I,N_D);
        sPID.setSetPoint(1000);
        feedforward = new SimpleMotorFeedforward(NFEETFORWARD_S, NFEETFORWARD_V);
}

    @Override
    public void periodic() {
        if(running){
            double currentvelocity = FireThePollen.getCorrectedVelocity();
            double ffoutput = feedforward.calculate(currentvelocity,FireThePollen.getAcceleration());
            double PIDoutput = sPID.calculate(currentvelocity);
            double clamped = MathUtil.clamp(ffoutput+PIDoutput,-1,1);
            FireThePollen.set(clamped);
            telemetry.addData("clamped",clamped);
            telemetry.addData("currentvelocity",currentvelocity);
            telemetry.addData("setpoints", sPID.getSetPoint());





        }
    }
}
