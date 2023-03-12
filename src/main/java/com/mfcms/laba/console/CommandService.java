package com.mfcms.laba.console;

import java.util.TreeMap;

import com.mfcms.laba.console.commands.CommandBase;
import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.exceptions.CommandInvalidArgumentAmountException;
import com.mfcms.laba.exceptions.CommandNotFoundException;

public class CommandService {
    private final TreeMap<String, CommandBase> commands = new TreeMap<>();

    public void addCommand(final CommandBase command) {
        commands.put(command.getName(), command);
    }

    public void addCommands(final CommandBase... commands) {
        for (CommandBase command : commands) {
            addCommand(command);
        }
    }

    public boolean execute(final String commandName, final String[] args)
            throws CommandNotFoundException, CommandExecutionException, CommandInvalidArgumentAmountException {
        if (!commands.containsKey(commandName)) {
            throw new CommandNotFoundException(commandName);
        }
        var command = commands.get(commandName);
        return command.execute(args);
    }

    public Iterable<CommandBase> getCommands() {
        return commands.values();
    }
}
