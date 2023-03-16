package com.mfcms.laba.model.Host;

import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.mfcms.laba.console.commands.Configuration.Config;

public class ServerConnectionMananger {
    public Socket {
    Socket socket = new Socket(Config.IP, port);

    OutputStream os = socket.getOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(os);
    }
}
