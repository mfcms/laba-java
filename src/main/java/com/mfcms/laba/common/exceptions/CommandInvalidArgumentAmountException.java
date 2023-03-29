package com.mfcms.laba.common.exceptions;

public class CommandInvalidArgumentAmountException extends Exception {
    private String commandName;
    private String[] commandArguments;
    private int actualAmount;
    private int expectedAmount;

    public CommandInvalidArgumentAmountException(String commandName,
            String[] commandArguments, int actualAmount, int expectedAmount) {
        super(String.format(
                "Invalid argument amount for command '%s', expected: %d, got: %d (args: ['%s'])",
                commandName, expectedAmount, actualAmount, String.join("', '", commandArguments)));
        this.commandName = commandName;
        this.commandArguments = commandArguments;
        this.actualAmount = actualAmount;
        this.expectedAmount = expectedAmount;
    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getCommandArguments() {
        return commandArguments;
    }

    public int getActualArguments() {
        return actualAmount;
    }

    public int getExpectedArguments() {
        return expectedAmount;
    }
}
