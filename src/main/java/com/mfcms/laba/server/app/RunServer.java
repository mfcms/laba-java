package com.mfcms.laba.server.app;

public class RunServer {
    public static void main(String[] args) {
        ServerApplication app = new ServerApplication();
        app.initialize();
        app.run();
    }
}
