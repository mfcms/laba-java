package com.mfcms.laba.console.commands;

import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.model.MusicBandManager;

public class ClearCommand extends CommandBase {

    private MusicBandManager musicBandManager;

    public ClearCommand(final MusicBandManager musicBandManager) {
        super("clear", "delete all collection");
        this.musicBandManager = musicBandManager;
    }
    
    @Override
    protected boolean executeCore(String[] args) throws CommandExecutionException {
            musicBandManager.clear();
            return true;
    }
}
