package com.mfcms.laba.client.commands;

import java.io.IOException;

import com.mfcms.laba.client.parsing.object.MusicBandReader;
import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.common.net.requests.CommandRequest;

public class UpdateCommand extends BaseNetClientCommand {
    private final MusicBandReader musicBandReader;

    public UpdateCommand(final ClientConnectionManager connectionManager, final MusicBandReader musicBandReader) {
        super(connectionManager, "update", "Updates a band by id", 1);
        this.musicBandReader = musicBandReader;
    }

    @Override
    protected CommandRequest createRequest(String[] args) throws CommandExecutionException {
        try {
            int id = Integer.decode(args[0]);
            return new CommandRequest(client.getCurrentUser(), this, musicBandReader.read(id, null));
        } catch (NumberFormatException e) {
            throw new CommandExecutionException(e);
        } catch (IOException e) {
            throw new CommandExecutionException(e);
        }
    }
}
