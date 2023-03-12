package com.mfcms.laba.console.commands;

import java.io.IOException;

import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.model.MusicBandManager;
import com.mfcms.laba.model.objects.Label;
import com.mfcms.laba.model.objects.MusicBand;
import com.mfcms.laba.service.IOManager;

public class CountLessThanLabelCommand extends CommandBase {

    private MusicBandManager musicBandManager;
    private final IOManager io;

    public CountLessThanLabelCommand(final MusicBandManager musicBandManager, final IOManager ioManager) {
        super("countltl", "Counts labels whitch are less than this one", 1);
        this.musicBandManager = musicBandManager;
        this.io = ioManager;
    }

    @Override
    protected boolean executeCore(String[] args) throws CommandExecutionException {
        int k = 0;
        Label label = new Label(args[0]);
        for (MusicBand band : musicBandManager.getMusicBands()) {
            if (band.getLabel().compareTo(label) < 0) {
                k++;
            }
        }
        String j = Integer.toString(k);
        try {
            io.out().append(j);
            io.out().append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
