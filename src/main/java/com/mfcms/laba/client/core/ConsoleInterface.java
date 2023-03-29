package com.mfcms.laba.client.core;

import java.io.IOException;

import com.mfcms.laba.common.services.IOManager;

public class ConsoleInterface extends CommandExecutor {
    
    public ConsoleInterface(final ClientCommandRegistry commandService, final IOManager ioManager) {
        super(commandService, ioManager);
    }
    
    @Override
    public void readCommand() throws IOException {
        io.out().append("Введите команду: ");
        super.readCommand();
    }
}