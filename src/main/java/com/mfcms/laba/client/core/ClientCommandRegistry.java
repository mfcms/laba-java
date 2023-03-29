package com.mfcms.laba.client.core;

import java.util.Collection;
import java.util.TreeMap;

import com.mfcms.laba.client.app.BaseClientApplication;
import com.mfcms.laba.client.commands.BaseClientCommand;
import com.mfcms.laba.common.exceptions.CommandNotFoundException;
import com.mfcms.laba.common.services.ServiceCollection.ServiceScope;

public class ClientCommandRegistry {
    
    private final TreeMap<String, BaseClientCommand> commands = new TreeMap<>();
    private ServiceScope serviceProvider;
    
    public ClientCommandRegistry(ServiceScope serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public void initialize() {
        for (Class<?> command : BaseClientApplication.commands) {
            addCommand((BaseClientCommand) serviceProvider.get(command));
        }
    }

    private void addCommand(final BaseClientCommand command) {
        commands.put(command.getName(), command);
    }

    // private void addCommands(final BaseClientCommand... commands) {
    //     for (BaseClientCommand command : commands) {
    //         addCommand(command);
    //     }
    // }

    public BaseClientCommand getCommand(final String commandName) throws CommandNotFoundException {
        if (!commands.containsKey(commandName)) {
            throw new CommandNotFoundException(commandName);
        }
        return commands.get(commandName);
    }

    public Collection<BaseClientCommand> getCommands() {
        return commands.values();
    }
}
