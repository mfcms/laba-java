package com.mfcms.laba.client.app;

import java.util.Scanner;

import com.mfcms.laba.client.core.CommandExecutor;
import com.mfcms.laba.client.core.ConsoleInterface;
import com.mfcms.laba.client.parsing.FieldReader;
import com.mfcms.laba.client.parsing.InteractiveFieldReader;
import com.mfcms.laba.common.services.IOManager;
import com.mfcms.laba.common.services.ServiceCollection;

public class ConsoleApplication extends BaseClientApplication {
    @Override
    public void configure(ServiceCollection services) {
        super.configure(services);
        services.add(IOManager.class, serviceProvider -> new IOManager(new Scanner(System.in), System.out));
        services.add(FieldReader.class, InteractiveFieldReader.class);
        services.add(CommandExecutor.class, ConsoleInterface.class);
    }
}