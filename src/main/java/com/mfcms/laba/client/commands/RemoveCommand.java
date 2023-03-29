package com.mfcms.laba.client.commands;

import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.common.net.requests.CommandRequest;

public class RemoveCommand extends BaseNetClientCommand {
    public RemoveCommand(final ClientConnectionManager connectionManager) {
        super(connectionManager, "remove", "Removes by id", 1);
    }

    @Override
    protected CommandRequest createRequest(String[] args) throws CommandExecutionException {
        try {
            return new CommandRequest(client.getCurrentUser(), this, Integer.decode(args[0]));
        } catch (NumberFormatException e) {
            throw new CommandExecutionException(e);
        }
    }
}
