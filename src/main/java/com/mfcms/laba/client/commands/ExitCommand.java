package com.mfcms.laba.client.commands;

import com.mfcms.laba.client.core.CommandExecutor;

public class ExitCommand extends BaseClientCommand {
    private final CommandExecutor commandExecutor;

    public ExitCommand(final CommandExecutor commandExecutor) {
        super("exit", "завершить выполнение программы");
        this.commandExecutor = commandExecutor;
    }

    @Override
    protected boolean executeCore(String[] args) {
        commandExecutor.exitSignal();
        return true;
    }
}
