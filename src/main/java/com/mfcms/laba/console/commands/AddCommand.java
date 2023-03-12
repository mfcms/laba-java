package com.mfcms.laba.console.commands;

import java.io.IOException;

import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.model.MusicBandManager;
import com.mfcms.laba.service.parsing.object.MusicBandReader;

public class AddCommand extends CommandBase {
    private final MusicBandManager musicBandManager;
    private final MusicBandReader musicBandReader;

    public AddCommand(final MusicBandManager musicBandManager, final MusicBandReader musicBandReader) {
        super("add", "adds a MusicBand");
        this.musicBandManager = musicBandManager;
        this.musicBandReader = musicBandReader;
    }

    @Override
    protected boolean executeCore(String[] args) throws CommandExecutionException {
        try {
            musicBandManager.add(musicBandReader.read());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
