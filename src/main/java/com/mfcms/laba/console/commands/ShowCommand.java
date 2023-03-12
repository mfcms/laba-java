package com.mfcms.laba.console.commands;

import java.io.IOException;

import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.model.MusicBandManager;
import com.mfcms.laba.model.objects.MusicBand;
import com.mfcms.laba.service.IOManager;

public class ShowCommand extends CommandBase {
    private final MusicBandManager musicBandManager;
    private final IOManager io;

    public ShowCommand(final MusicBandManager musicBandManager, final IOManager ioManager) {
        super("show", "вывести справку по доступным командам");
        this.musicBandManager = musicBandManager;
        this.io = ioManager;
    }

    @Override
    protected boolean executeCore(final String[] args) throws CommandExecutionException {
        try {
            io.out().append("Music bands:\n\n");
            for (MusicBand musicBand : musicBandManager.getMusicBands()) {
                io.out().append("\n");
                io.out().append(musicBand.getName());
                io.out().append(" (");
                io.out().append(musicBand.getId().toString());
                io.out().append("):\n\t- albums count: ");
                io.out().append(Integer.toString(musicBand.getAlbumsCount()));
                io.out().append("\n\t- participants: ");
                io.out().append(Integer.toString(musicBand.getNumberOfParticipants()));
                io.out().append("\n\t- coordinates: ");
                io.out().append(Float.toString(musicBand.getCoordinates().getX()));
                io.out().append(", ");
                io.out().append(Float.toString(musicBand.getCoordinates().getY()));
                io.out().append("\n\t- label: ");
                io.out().append(musicBand.getLabel() == null ? "<null>" : musicBand.getLabel().getName());
                io.out().append("\n\t- genre: ");
                io.out().append(musicBand.getGenre().toString());
                io.out().append("\n\t- created at: ");
                io.out().append(musicBand.getCreationDate().toString());
                io.out().append("\n\t- established at: ");
                io.out().append(musicBand.getEstablishmentDate() == null ? "<null>" : musicBand.getEstablishmentDate().toString());
                io.out().append("\n\n");
            }
            io.out().flush();
        } catch (IOException e) {
            throw new CommandExecutionException(e.getMessage(), e);
        }
        return true;
    }

}
