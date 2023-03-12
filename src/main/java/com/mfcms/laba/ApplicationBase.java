package com.mfcms.laba;

import com.mfcms.laba.console.CommandExecutor;
import com.mfcms.laba.console.CommandService;
import com.mfcms.laba.model.MusicBandManager;
import com.mfcms.laba.service.DatabaseService;
import com.mfcms.laba.service.IOManager;
import com.mfcms.laba.service.parsing.FieldReader;
import com.mfcms.laba.service.parsing.object.MusicBandReader;

public abstract class ApplicationBase {
    protected CommandService commandService;
    protected CommandExecutor commandExecutor;
    protected IOManager ioManager;
    protected MusicBandManager musicBandManager;
    protected MusicBandReader musicBandReader;
    protected FieldReader fieldReader;
    protected DatabaseService databaseService;

    public void run() {
        System.out.println(this.getClass().getSimpleName() + " is running");
        
        databaseService.loadDatabase();
        commandExecutor.run();
    }

    public FieldReader getFieldReader() {
        return fieldReader;
    }

    public CommandService getCommandService() {
        return commandService;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public IOManager getIoManager() {
        return ioManager;
    }

    public MusicBandManager getMusicBandManager() {
        return musicBandManager;
    }

    public MusicBandReader getMusicBandReader() {
        return musicBandReader;
    }

    public DatabaseService getDatabaseService() {
        return databaseService;
    }

    public void useMusicBandManager(MusicBandManager musicBandManager) {
        if(musicBandManager == null) {
            musicBandManager = this.musicBandManager == null
                ? new MusicBandManager()
                : this.musicBandManager;
        } 
        this.musicBandManager = musicBandManager;
    }
}