package org.firstinspires.ftc.teamcode.command;

import com.bylazar.configurables.annotations.Configurable;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.util.Timing;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystem.RampDoorSubsystem;

import java.util.concurrent.TimeUnit;

@Configurable
public class RampDoorCloseCommand extends CommandBase {
    private final RampDoorSubsystem rampdoorsubsystem;
    private final Telemetry telemetry;
    public static Long RAMPEDORPE_CLOSE =500l;
    private Timing.Timer servoOfTimes = new Timing.Timer(RAMPEDORPE_CLOSE, TimeUnit.MILLISECONDS);
    public RampDoorCloseCommand(RampDoorSubsystem rampdoorsubsystem, Telemetry telemetry) {
        this.rampdoorsubsystem = rampdoorsubsystem;
        this.telemetry = telemetry;
    }

    @Override
    public void initialize() {
        servoOfTimes.start();
        rampdoorsubsystem.closed();
    }

    @Override
    public boolean isFinished() {
        telemetry.addData("rd closed delay",RAMPEDORPE_CLOSE);
        return servoOfTimes.done();
    }
}
