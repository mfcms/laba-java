package com.mfcms.laba.console.commands;

import com.mfcms.laba.console.CommandExecutor;

public class ExitCommand extends CommandBase {
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
