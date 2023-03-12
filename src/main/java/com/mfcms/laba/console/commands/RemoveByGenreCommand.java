package com.mfcms.laba.console.commands;

import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.model.MusicBandManager;
import com.mfcms.laba.model.objects.MusicGenre;

public class RemoveByGenreCommand extends CommandBase {

    private final MusicBandManager musicBandManager;

    public RemoveByGenreCommand(final MusicBandManager musicBandManager) {
        super("remove", "Removes all elements by genre", 1);
        this.musicBandManager = musicBandManager;
    }

    @Override
    protected boolean executeCore(String[] args) throws CommandExecutionException {
        for (MusicGenre genre : MusicGenre.values()) {
            if (genre.toString().equalsIgnoreCase(args[0])) {
                musicBandManager.removebygenre(genre);
                return true;
            }

        }
        throw new CommandExecutionException();
    }
}
