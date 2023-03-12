package com.mfcms.laba.console.commands;

import java.io.IOException;

import com.mfcms.laba.console.CommandService;
import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.service.IOManager;

public class HelpCommand extends CommandBase {
    private final CommandService commandService;
    private final IOManager io;

    public HelpCommand(final CommandService commandService, final IOManager ioManager) {
        super("help", "вывести справку по доступным командам");
        this.commandService = commandService;
        this.io = ioManager;
    }

    @Override
    protected boolean executeCore(final String[] args) throws CommandExecutionException {
        try {
            io.out().append("Available commands:\n");
            for (CommandBase command : commandService.getCommands()) {
                io.out().append("\n\t");
                io.out().append(command.getName());
                io.out().append(" -\n\t\t");
                io.out().append(command.getDescription());
                io.out().append("\n\n");
            }
            io.out().flush();
        } catch (IOException e) {
            throw new CommandExecutionException(e.getMessage(), e);
        }
        return true;
    }

}
