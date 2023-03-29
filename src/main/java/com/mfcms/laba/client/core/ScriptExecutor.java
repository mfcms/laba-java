package com.mfcms.laba.client.core;

import java.io.IOException;

import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.exceptions.CommandInvalidArgumentAmountException;
import com.mfcms.laba.common.exceptions.CommandNotFoundException;
import com.mfcms.laba.common.services.IOManager;

public class ScriptExecutor extends CommandExecutor {

    public ScriptExecutor(final ClientCommandRegistry commandService, final IOManager ioManager) {
        super(commandService, ioManager);
    }

    @Override
    protected void executeCore(String commandName, String[] args) throws CommandNotFoundException,
            CommandExecutionException, CommandInvalidArgumentAmountException {
        io.out().append("> ");
        io.out().append(commandName);
        io.out().append(" ");
        io.out().append(String.join(" ", args));
        io.out().append("\n");
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