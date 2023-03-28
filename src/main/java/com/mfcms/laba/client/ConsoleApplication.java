package com.mfcms.laba.client;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.mfcms.laba.client.console.ConsoleInterface;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.client.parsing.InteractiveFieldReader;
import com.mfcms.laba.client.parsing.object.CoordinatesReader;
import com.mfcms.laba.client.parsing.object.LabelReader;
import com.mfcms.laba.client.parsing.object.MusicBandReader;
import com.mfcms.laba.client.parsing.object.MusicGenreReader;
import com.mfcms.laba.common.IOManager;
import com.mfcms.laba.common.commands.*;
import com.mfcms.laba.common.model.MusicBandGetter;
import com.mfcms.laba.common.model.MusicBandManager;
import com.mfcms.laba.common.net.ConnectionManager;
import com.mfcms.laba.server.CommandService;
import com.mfcms.laba.server.database.DatabaseService;

public class ConsoleApplication extends ApplicationBase {

    public ConsoleApplication() {
        musicBandManager = new MusicBandManager();

        ioManager = new IOManager(
                new Scanner(new InputStreamReader(System.in)),
                new OutputStreamWriter(System.out));

        fieldReader = new InteractiveFieldReader(ioManager);
        musicBandReader = new MusicBandReader(
                musicBandManager, fieldReader,
                new LabelReader(fieldReader),
                new CoordinatesReader(fieldReader),
                new MusicGenreReader(fieldReader));

        commandService = new CommandService();
        ConnectionManager = new ClientConnectionManager(null);
        MusicBandGetter = new MusicBandGetter() ;

        commandExecutor = new ConsoleInterface(commandService, ioManager);
        databaseService = new DatabaseService("file.xml", musicBandManager);

        commandService.addCommands(
                new HelpCommand(commandService, ioManager),
                new ExitCommand(commandExecutor),
                new AddCommand(ConnectionManager, musicBandManager, musicBandReader),
                new ShowCommand(musicBandManager, ioManager),
                new SaveCommand(databaseService),
                new UpdateCommand(musicBandManager, musicBandReader),
                new RemoveCommand(musicBandManager),
                new ClearCommand(musicBandManager),
                new RemoveByGenreCommand(musicBandManager),
                new CountLessThanLabelCommand(musicBandManager, ioManager),
                new ExecuteScriptCommand(musicBandManager));
    }
}