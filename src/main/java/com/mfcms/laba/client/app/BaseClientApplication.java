package com.mfcms.laba.client.app;

import com.mfcms.laba.client.commands.*;
import com.mfcms.laba.client.core.ClientCommandRegistry;
import com.mfcms.laba.client.core.CommandExecutor;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.client.parsing.object.CoordinatesReader;
import com.mfcms.laba.client.parsing.object.CredentialsReader;
import com.mfcms.laba.client.parsing.object.LabelReader;
import com.mfcms.laba.client.parsing.object.MusicBandReader;
import com.mfcms.laba.client.parsing.object.MusicGenreReader;
import com.mfcms.laba.common.app.BaseApplication;
import com.mfcms.laba.common.services.ServiceCollection;

public abstract class BaseClientApplication extends BaseApplication {

    public static final Class<?>[] commands = new Class<?>[] {
        AddCommand.class,
        ClearCommand.class,
        CountLessThanLabelCommand.class,
        ExecuteScriptCommand.class,
        ExitCommand.class,
        HelpCommand.class,
        RemoveByGenreCommand.class,
        RemoveCommand.class,
        ShowCommand.class,
        UpdateCommand.class,
        LoginCommand.class,
        RegisterCommand.class,
    };

    @Override
    public void configure(ServiceCollection services) {
        services.add(ClientCommandRegistry.class);

        services.add(ClientConnectionManager.class, ClientConnectionManager.class);

        services.add(CredentialsReader.class);
        services.add(CoordinatesReader.class);
        services.add(LabelReader.class);
        services.add(MusicGenreReader.class);
        services.add(MusicBandReader.class);

        for (Class<?> command : commands) {
            services.add(command);
        }
    }

    public void run() {
        System.out.println(this.getClass().getSimpleName() + " is running");

        serviceProvider.get(ClientCommandRegistry.class).initialize();

        serviceProvider.get(CommandExecutor.class).run();
    }
}