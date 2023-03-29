package com.mfcms.laba.client.net;

import java.io.IOException;
import java.net.Socket;

import com.mfcms.laba.common.model.Credentials;
import com.mfcms.laba.common.net.Config;

public class ClientConnectionManager {
    Credentials currentUser = null;

    private String lastIp = null;
    private Integer lastPort = null;

    private Socket socket;

    public boolean connect() throws IOException {
        return connectTo(lastIp, lastPort);
    }

    public boolean connectTo(String ip, Integer port) throws IOException {
        ip = ip != null ? ip : Config.IP;
        port = port != null ? port : Config.PORT;

        lastIp = ip;
        lastPort = port;

        socket = new Socket(ip, port);
        return true;
    }

    public Socket getSocket() {
        if(socket == null || socket.isConnected()) {
            try {
                connect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return socket;
    }

    public Credentials getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Credentials currentUser) {
        this.currentUser = currentUser;
    }
}
