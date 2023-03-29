package com.mfcms.laba.server.core;

import java.io.IOException;

import com.mfcms.laba.server.database.DatabaseManager;
import com.mfcms.laba.server.net.ServerListener;

public class ServerCore {
    private ServerListener listener;
    private DatabaseManager database;

    private boolean exit;

    public ServerCore(ServerListener listener, DatabaseManager database) {
        super();
        this.listener = listener;
        this.database = database;
    }

    public void exitSignal() {
        this.exit = true;
    }

    public boolean didExit() {
        return this.exit;
    }

    public void run() {
        try {
            while (!didExit()) {
                if(!listener.isOpen()) {
                    listener.open();
                }
                listener.process();
                Thread.sleep(250);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
