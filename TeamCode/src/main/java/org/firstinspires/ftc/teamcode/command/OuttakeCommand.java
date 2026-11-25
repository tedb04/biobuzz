package org.firstinspires.ftc.teamcode.command;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;

public class OuttakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;

    public OuttakeCommand(IntakeSubsystem intakeSubsystem) {

        this.intakeSubsystem = intakeSubsystem;

        addRequirements(intakeSubsystem);

    }

    @Override
    public void execute() {
    intakeSubsystem.outtake();
    }
    @Override
    public boolean isFinished() {
     return false;
    }
    @Override
    public void end(boolean interrupted) {
      intakeSubsystem.em_stop();
    }



}
