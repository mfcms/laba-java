package com.mfcms.laba.console.commands;

import com.mfcms.laba.service.DatabaseService;

public class SaveCommand extends CommandBase {

    private DatabaseService databaseService;

    public SaveCommand(final DatabaseService databaseService) {
        super("save", "сохранить все MusicBand в файл");
        this.databaseService = databaseService;
    }

    @Override
    protected boolean executeCore(String[] args) {
        databaseService.saveDatabase();
        return true;
    }
}
