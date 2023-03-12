package com.mfcms.laba.console.commands;

import com.mfcms.laba.ScriptApplication;
import com.mfcms.laba.exceptions.CommandExecutionException;
import com.mfcms.laba.model.MusicBandManager;

public class ExecuteScriptCommand extends CommandBase {
    

    private MusicBandManager musicBandManager;

    public ExecuteScriptCommand(final MusicBandManager musicBandManager) {
        super("execute_script", "executes script from a given file", 1);
        this.musicBandManager = musicBandManager;
    }

    @Override
    protected boolean executeCore(String[] args) throws CommandExecutionException {
        ScriptApplication scriptApp = new ScriptApplication(args[0], musicBandManager);
        scriptApp.run();
        return true;
    }
}
