package com.mfcms.laba.client.app;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.mfcms.laba.client.core.CommandExecutor;
import com.mfcms.laba.client.core.ScriptExecutor;
import com.mfcms.laba.client.parsing.FieldReader;
import com.mfcms.laba.client.parsing.ScriptFieldReader;
import com.mfcms.laba.common.services.IOManager;
import com.mfcms.laba.common.services.ServiceCollection;

public class ScriptApplication extends BaseClientApplication {

    public interface ScriptPathProvider {
        String getScriptPath();
    } 

    @Override
    public void configure(ServiceCollection services) {
        super.configure(services);

        services.add(IOManager.class, serviceProvider -> {
            var pathProvider = serviceProvider.get(ScriptPathProvider.class);
            try {
                FileReader fileReader = new FileReader(pathProvider.getScriptPath());
                return new IOManager(new Scanner(fileReader), System.out);
            } catch (FileNotFoundException e) {
                throw new IllegalStateException(e);
            }
        });
        services.add(FieldReader.class, ScriptFieldReader.class);
        services.add(CommandExecutor.class, ScriptExecutor.class);
    }
}