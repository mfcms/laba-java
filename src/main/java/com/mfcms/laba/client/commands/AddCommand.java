package com.mfcms.laba.client.commands;

import java.io.IOException;
import java.util.Date;

import com.mfcms.laba.client.parsing.object.MusicBandReader;
import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.common.net.requests.CommandRequest;

public class AddCommand extends BaseNetClientCommand {
    private final MusicBandReader musicBandReader;

    public AddCommand(final ClientConnectionManager connectionManager, final MusicBandReader musicBandReader) {
        super(connectionManager, "add", "adds a MusicBand");
        this.musicBandReader = musicBandReader;
    }

    @Override
    protected CommandRequest createRequest(String[] args) throws CommandExecutionException {
        try {
            return new CommandRequest(client.getCurrentUser(), this, musicBandReader.read(-1, new Date()));
        } catch (IOException e) {
            throw new CommandExecutionException(e);
        }
    }
}
