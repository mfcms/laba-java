package com.mfcms.laba.client.app;

import com.mfcms.laba.client.app.ScriptApplication.ScriptPathProvider;

public class RunScript {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: <path-to-script.txt>");
        }

        ScriptApplication app = new ScriptApplication();
        app.initialize(services -> {
            services.add(ScriptPathProvider.class, s -> new ScriptPathProvider() {
                @Override
                public String getScriptPath() {
                    return args[0];
                }
            });
        });
        
        app.run();
    }
}
