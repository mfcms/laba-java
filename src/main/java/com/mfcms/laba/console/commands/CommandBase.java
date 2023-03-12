package com.mfcms.laba.console.commands;

import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.exceptions.CommandInvalidArgumentAmountException;

public abstract class CommandBase {
    private String name;
    private String description;
    private int argumentCount;

    public CommandBase(String name, String description, final int argumentCount) {
        this.name = name;
        this.description = description;
        this.argumentCount = argumentCount;
    }

    public CommandBase(String name, String description) {
        this(name, description, 0);
    }

    public int getArgumentCount() {
        return argumentCount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean execute(String[] args)
            throws CommandExecutionException, CommandInvalidArgumentAmountException {
        if(args.length != argumentCount) {
            throw new CommandInvalidArgumentAmountException(name, args, args.length, argumentCount);
        }
        return executeCore(args);
    }

    protected abstract boolean executeCore(String[] args) throws CommandExecutionException;
}
