package com.mfcms.laba.client.commands;

import com.mfcms.laba.common.commands.BaseCommand;
import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.exceptions.CommandInvalidArgumentAmountException;

public abstract class BaseClientCommand extends BaseCommand {
    private int argumentCount;

    public BaseClientCommand(String name, String description, int argumentCount) {
        super(name, description);
        this.argumentCount = argumentCount;
    }

    public BaseClientCommand(String name, String description) {
        this(name, description, 0);
    }

    public boolean execute(String[] args)
            throws CommandExecutionException, CommandInvalidArgumentAmountException {
        if(args.length != getArgumentCount()) {
            throw new CommandInvalidArgumentAmountException(getName(), args, args.length, getArgumentCount());
        }
        return executeCore(args);
    }

    public int getArgumentCount() {
        return argumentCount;
    }

    protected abstract boolean executeCore(String[] args) throws CommandExecutionException;
}
