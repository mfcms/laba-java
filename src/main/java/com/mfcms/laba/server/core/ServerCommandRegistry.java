package com.mfcms.laba.server.core;

import java.util.Collection;
import java.util.TreeMap;

import com.mfcms.laba.server.app.ServerApplication;
import com.mfcms.laba.server.commands.BaseServerCommand;
import com.mfcms.laba.common.exceptions.CommandNotFoundException;
import com.mfcms.laba.common.services.ServiceCollection.ServiceScope;

public class ServerCommandRegistry {
    private final TreeMap<String, BaseServerCommand> commands = new TreeMap<>();
    private ServiceScope serviceProvider;

    public ServerCommandRegistry(ServiceScope serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void initialize() {
        for (Class<?> command : ServerApplication.commands) {
            addCommand((BaseServerCommand) serviceProvider.get(command));
        }
    }


    private void addCommand(final BaseServerCommand command) {
        commands.put(command.getName(), command);
    }


    public BaseServerCommand getCommand(final String commandName) throws CommandNotFoundException {
        if (!commands.containsKey(commandName)) {
            throw new CommandNotFoundException(commandName);
        }
        return commands.get(commandName);
    }

    public Collection<BaseServerCommand> getCommands() {
        return commands.values();
    }
}
