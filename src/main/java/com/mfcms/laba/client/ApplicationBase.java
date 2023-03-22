package com.mfcms.laba.client;

import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.client.parsing.FieldReader;
import com.mfcms.laba.client.parsing.object.MusicBandReader;
import com.mfcms.laba.common.IOManager;
import com.mfcms.laba.common.console.CommandExecutor;
import com.mfcms.laba.server.CommandService;
import com.mfcms.laba.server.MusicBandManager;
import com.mfcms.laba.server.database.DatabaseService;

public abstract class ApplicationBase {
    protected CommandService commandService;
    protected CommandExecutor commandExecutor;
    protected IOManager ioManager;
    protected MusicBandManager musicBandManager;
    protected MusicBandReader musicBandReader;
    protected FieldReader fieldReader;
    protected DatabaseService databaseService;
    protected ClientConnectionManager clientConnectionManager;

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

    public ClientConnectionManager gClientConnectionManager(){
        return clientConnectionManager;
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