package com.mfcms.laba.client.commands;

import com.mfcms.laba.client.core.ClientCommandRegistry;
import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.services.IOManager;

public class HelpCommand extends BaseClientCommand {
    private final ClientCommandRegistry commandService;
    private final IOManager io;

    public HelpCommand(final ClientCommandRegistry commandService, final IOManager ioManager) {
        super("help", "вывести справку по доступным командам");
        this.commandService = commandService;
        this.io = ioManager;
    }

    @Override
    protected boolean executeCore(final String[] args) throws CommandExecutionException {
        io.out().append("Available commands:\n");
        for (BaseClientCommand command : commandService.getCommands()) {
            io.out().append("\n\t");
            io.out().append(command.getName());
            io.out().append(" -\n\t\t");
            io.out().append(command.getDescription());
            io.out().append("\n");
        }
        io.out().flush();
        return true;
    }

}
