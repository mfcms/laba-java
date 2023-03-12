package com.mfcms.laba.console.commands;

import java.io.IOException;

import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.model.MusicBandManager;
import com.mfcms.laba.service.parsing.object.MusicBandReader;
import com.mfcms.laba.model.objects.MusicBand;

public class UpdateCommand extends CommandBase {
    private final MusicBandManager musicBandManager;
    private final MusicBandReader musicBandReader;

    public UpdateCommand(final MusicBandManager musicBandManager, final MusicBandReader musicBandReader) {
        super("update", "Updates a band by id", 1);
        this.musicBandManager = musicBandManager;
        this.musicBandReader = musicBandReader;
    }

    @Override
    protected boolean executeCore(String[] args) throws CommandExecutionException {
        try {
            int id = Integer.decode(args[0]);
            MusicBand bandid = musicBandManager.get(id);
            if (bandid == null) {
                throw new CommandExecutionException();
            }
            MusicBand upd = musicBandReader.update("", bandid);
            musicBandManager.add(upd);
        } catch (NumberFormatException e) {
            throw new CommandExecutionException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
