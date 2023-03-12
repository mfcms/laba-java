package com.mfcms.laba.exceptions;

public class CommandNotFoundException extends Exception {
    private String commandName;

    public CommandNotFoundException(String commandName) {
        super(String.format("Could not find command '%s'", commandName));
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }

}
