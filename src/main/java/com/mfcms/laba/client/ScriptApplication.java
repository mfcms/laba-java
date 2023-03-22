package com.mfcms.laba.client;

import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.mfcms.laba.client.parsing.ScriptFieldReader;
import com.mfcms.laba.client.parsing.object.CoordinatesReader;
import com.mfcms.laba.client.parsing.object.LabelReader;
import com.mfcms.laba.client.parsing.object.MusicBandReader;
import com.mfcms.laba.client.parsing.object.MusicGenreReader;
import com.mfcms.laba.common.IOManager;
import com.mfcms.laba.common.commands.*;
import com.mfcms.laba.common.console.ScriptExecutor;
import com.mfcms.laba.server.CommandService;
import com.mfcms.laba.server.MusicBandManager;
import com.mfcms.laba.server.database.DatabaseService;

public class ScriptApplication extends ApplicationBase {

    public ScriptApplication(String scriptPath, MusicBandManager manager) {
        musicBandManager = manager != null
                ? manager
                : new MusicBandManager();
        try {
            FileReader reader = new FileReader(scriptPath);

            ioManager = new IOManager(
                new Scanner(reader),
                new OutputStreamWriter(System.out));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        fieldReader = new ScriptFieldReader(ioManager);
        musicBandReader = new MusicBandReader(
                musicBandManager, fieldReader,
                new LabelReader(fieldReader),
                new CoordinatesReader(fieldReader),
                new MusicGenreReader(fieldReader));

        commandService = new CommandService();
        commandExecutor = new ScriptExecutor(commandService, ioManager);
        databaseService = new DatabaseService("file.xml", musicBandManager);

        commandService.addCommands(
                new HelpCommand(commandService, ioManager),
                new ExitCommand(commandExecutor),
                //new AddCommand(musicBandManager, musicBandReader),
                new ShowCommand(musicBandManager, ioManager),
                new SaveCommand(databaseService),
                new UpdateCommand(musicBandManager, musicBandReader),
                new RemoveCommand(musicBandManager),
                //new ClearCommand(musicBandManager),
                new RemoveByGenreCommand(musicBandManager),
                //new CountLessThanLabelCommand(musicBandManager, ioManager),
                new ExecuteScriptCommand(musicBandManager));
    }//ту ду
    //добавить штуку

    public ScriptApplication(String scriptPath) {
        this(scriptPath, null);
    }
}