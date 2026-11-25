package org.firstinspires.ftc.teamcode.opmode;

import com.bylazar.telemetry.JoinedTelemetry;
import com.bylazar.telemetry.PanelsTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Utility;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.command.RampDoorCloseCommand;
import org.firstinspires.ftc.teamcode.command.RampDoorOpenCommand;
import org.firstinspires.ftc.teamcode.subsystem.RampDoorSubsystem;
import org.firstinspires.ftc.teamcode.subsystem.TelemetryUpdateSubsystem;

@Utility(name = "Rd teste")
public class RampDoorTest extends CommandOpMode {
    private TelemetryUpdateSubsystem telemetryUpdateSubsystem;
    private GamepadEx operator;
    private RampDoorSubsystem rampDoorSubsystem;
    private RampDoorOpenCommand rampDoorOpenCommand;
    private RampDoorCloseCommand rampDoorClosedCommand;
    @Override
    public void initialize() {
        telemetry = new JoinedTelemetry(telemetry, PanelsTelemetry.INSTANCE.getFtcTelemetry());
        rampDoorSubsystem = new RampDoorSubsystem(hardwareMap);
        telemetryUpdateSubsystem = new TelemetryUpdateSubsystem(telemetry);
        rampDoorOpenCommand = new RampDoorOpenCommand(rampDoorSubsystem, telemetry);
        rampDoorClosedCommand = new RampDoorCloseCommand(rampDoorSubsystem, telemetry);





        operator = new GamepadEx(gamepad2);
        operator.getGamepadButton(GamepadKeys.Button.A).whenPressed(rampDoorOpenCommand);
        operator.getGamepadButton(GamepadKeys.Button.B).whenPressed(rampDoorClosedCommand);
        register(telemetryUpdateSubsystem);
    }
}
