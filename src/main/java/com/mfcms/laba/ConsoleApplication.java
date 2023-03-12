package com.mfcms.laba;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import com.mfcms.laba.console.ConsoleInterface;
import com.mfcms.laba.console.CommandService;
import com.mfcms.laba.console.commands.*;
import com.mfcms.laba.model.MusicBandManager;
import com.mfcms.laba.service.DatabaseService;
import com.mfcms.laba.service.IOManager;
import com.mfcms.laba.service.parsing.InteractiveFieldReader;
import com.mfcms.laba.service.parsing.object.CoordinatesReader;
import com.mfcms.laba.service.parsing.object.LabelReader;
import com.mfcms.laba.service.parsing.object.MusicBandReader;
import com.mfcms.laba.service.parsing.object.MusicGenreReader;

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
        commandExecutor = new ConsoleInterface(commandService, ioManager);
        databaseService = new DatabaseService("file.xml", musicBandManager);

        commandService.addCommands(
                new HelpCommand(commandService, ioManager),
                new ExitCommand(commandExecutor),
                new AddCommand(musicBandManager, musicBandReader),
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