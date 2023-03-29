package com.mfcms.laba.client.commands;

import com.mfcms.laba.client.app.ScriptApplication;
import com.mfcms.laba.client.app.ScriptApplication.ScriptPathProvider;
import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.common.services.ServiceCollection.ServiceScope;

public class ExecuteScriptCommand extends BaseClientCommand {
    private ServiceScope serviceScope;

    public ExecuteScriptCommand(ServiceScope serviceScope) {
        super("execute_script", "executes script from a given file", 1);
        this.serviceScope = serviceScope;
    }

    @Override
    protected boolean executeCore(String[] args) throws CommandExecutionException {
        try {
            String scriptPath = args[0];
            ScriptApplication scriptApp = new ScriptApplication();

            scriptApp.initialize(services -> {
                services.add(ScriptPathProvider.class, s -> new ScriptPathProvider() {
                    @Override
                    public String getScriptPath() {
                        return scriptPath;
                    }
                });
                services.add(ClientConnectionManager.class, s -> serviceScope.get(ClientConnectionManager.class));
            });

            scriptApp.run();
        } catch (IllegalStateException e) {
            throw new CommandExecutionException(e);
        }
        return true;
    }
}
