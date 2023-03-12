package com.mfcms.laba.console;

import java.io.IOException;

import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.exceptions.CommandInvalidArgumentAmountException;
import com.mfcms.laba.exceptions.CommandNotFoundException;
import com.mfcms.laba.service.IOManager;

public class ScriptExecutor extends CommandExecutor {

    public ScriptExecutor(final CommandService commandService, final IOManager ioManager) {
        super(commandService, ioManager);
    }

    @Override
    protected void executeCore(String commandName, String[] args) throws CommandNotFoundException,
            CommandExecutionException, CommandInvalidArgumentAmountException {
        try {
            io.out().append("> ");
            io.out().append(commandName);
            io.out().append(" ");
            io.out().append(String.join(" ", args));
            io.out().append("\n");
        } catch (IOException e) {
            throw new CommandExecutionException(e);
        }
        super.executeCore(commandName, args);
    }

    @Override
    public void readCommand() throws IOException {
        super.readCommand();
        if (!io.in().hasNextLine()) {
            this.exitSignal();
        }
    }
}