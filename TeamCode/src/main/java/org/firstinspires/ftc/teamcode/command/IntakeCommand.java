package org.firstinspires.ftc.teamcode.command;

import com.qualcomm.robotcore.robocol.Command;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystem.IntakeSubsystem;

public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;

    public IntakeCommand(IntakeSubsystem intakeSubsystem) {

        this.intakeSubsystem = intakeSubsystem;

        addRequirements(intakeSubsystem);

    }

    @Override
    public void execute() {
    intakeSubsystem.intake();
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
