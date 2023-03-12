package com.mfcms.laba.console;

import java.io.IOException;
import java.util.stream.Stream;

import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.exceptions.CommandInvalidArgumentAmountException;
import com.mfcms.laba.exceptions.CommandNotFoundException;
import com.mfcms.laba.service.IOManager;

public class CommandExecutor {
    protected final IOManager io;
    private final CommandService commandService;
    private boolean exit;

    public CommandExecutor(final CommandService commandService, final IOManager ioManager) {
        this.commandService = commandService;
        this.io = ioManager;
    }

    protected void executeCore(String commandName, String[] args) throws CommandNotFoundException,
            CommandExecutionException, CommandInvalidArgumentAmountException {
        commandService.execute(commandName, args);
    }

    public void readCommand() throws IOException {
        String command[] = { null, null };
        io.out().flush();
        command = (io.in().nextLine().trim() + " ").split(" ", 2);
        String[] args = Stream
                .of(command[1].trim().split(" "))
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
        try {
            executeCore(command[0], args);
        } catch (CommandNotFoundException e) {
            io.out().write(e.getMessage());
            e.printStackTrace();
        } catch (CommandExecutionException e) {
            e.printStackTrace();
        } catch (CommandInvalidArgumentAmountException e) {
            e.printStackTrace();
        }
    }

    public void exitSignal() {
        this.exit = true;
    }

    public boolean didExit() {
        return this.exit;
    }

    public void run() {
        try {
            while (!didExit()) {
                readCommand();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
