package com.mfcms.laba.server.app;

import com.mfcms.laba.server.commands.*;
import com.mfcms.laba.server.core.ServerCommandRegistry;
import com.mfcms.laba.server.core.ServerCore;
import com.mfcms.laba.server.database.DatabaseManager;
import com.mfcms.laba.server.database.MusicBandManager;
import com.mfcms.laba.server.database.DatabaseManager.DatabasePathProvider;
import com.mfcms.laba.server.net.ServerListener;
import com.mfcms.laba.common.app.BaseApplication;
import com.mfcms.laba.common.services.IOManager;
import com.mfcms.laba.common.services.ServiceCollection;

public class ServerApplication extends BaseApplication {

    public static final Class<?>[] commands = new Class<?>[] {
            AddCommand.class,
            ClearCommand.class,
            CountLessThanLabelCommand.class,
            RemoveByGenreCommand.class,
            RemoveCommand.class,
            ShowCommand.class,
            UpdateCommand.class,
            LoginCommand.class,
            RegisterCommand.class,
    };

    @Override
    public void configure(ServiceCollection services) {
        services.add(IOManager.class, s -> new IOManager(null, System.out));

        services.add(MusicBandManager.class, s -> s.get(DatabaseManager.class).loadDatabase());

        services.add(DatabaseManager.class);
        services.add(DatabasePathProvider.class, s -> new DatabasePathProvider() {
            @Override
            public String getDatabasePath() {
                return "database.xml";
            }
        });

        services.add(ServerCore.class);

        services.add(ServerCommandRegistry.class);

        services.add(ServerListener.class);

        for (Class<?> command : commands) {
            services.add(command);
        }
    }

    public void run() {
        System.out.println(this.getClass().getSimpleName() + " is running");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            serviceProvider.get(IOManager.class).out().println("Saving database...");
            serviceProvider.get(DatabaseManager.class)
                .saveDatabase(serviceProvider.get(MusicBandManager.class));
            serviceProvider.get(IOManager.class).out().println("Database saved.");
        }));

        serviceProvider.get(DatabaseManager.class).loadDatabase();

        serviceProvider.get(ServerCommandRegistry.class).initialize();

        serviceProvider.get(ServerCore.class).run();
    }
}