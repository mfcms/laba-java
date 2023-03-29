package com.mfcms.laba.client.commands;

import com.mfcms.laba.client.parsing.field.MusicGenreFieldInfo;
import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.exceptions.ValidationException;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.common.net.requests.CommandRequest;

public class RemoveByGenreCommand extends BaseNetClientCommand {
    public RemoveByGenreCommand(final ClientConnectionManager connectionManager) {
        super(connectionManager, "removeByGenre", "Removes all elements by genre", 1);
    }

    @Override
    protected CommandRequest createRequest(String[] args) throws CommandExecutionException {
        try {
            return new CommandRequest(client.getCurrentUser(), this, MusicGenreFieldInfo.parseFromString(args[0]));
        } catch (ValidationException e) {
            throw new CommandExecutionException(e);
        }
    }
}
