package com.mfcms.laba.console;

import java.io.IOException;

import com.mfcms.laba.service.IOManager;

public class ConsoleInterface extends CommandExecutor {
    
    public ConsoleInterface(final CommandService commandService, final IOManager ioManager) {
        super(commandService, ioManager);
    }
    
    @Override
    public void readCommand() throws IOException {
        io.out().append("Введите команду: ");
        super.readCommand();
    }
}