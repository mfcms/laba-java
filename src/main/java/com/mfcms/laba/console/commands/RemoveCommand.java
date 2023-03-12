package com.mfcms.laba.console.commands;


import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.model.MusicBandManager;
import com.mfcms.laba.model.objects.MusicBand;

public class RemoveCommand extends CommandBase{
    private final MusicBandManager musicBandManager;

    public RemoveCommand(final MusicBandManager musicBandManager) {
        super("remove", "Removes by id", 1);
        this.musicBandManager = musicBandManager;
    }

    @Override
    protected boolean executeCore(String[] args) throws CommandExecutionException {
        try {
            int id = Integer.decode(args[0]);
            MusicBand bandid = musicBandManager.get(id);
            if (bandid == null) {
                throw new CommandExecutionException();
            }
            musicBandManager.remove(id);
        } catch (NumberFormatException e) {
            throw new CommandExecutionException(e);
        }
        return true;
    }
    
}
