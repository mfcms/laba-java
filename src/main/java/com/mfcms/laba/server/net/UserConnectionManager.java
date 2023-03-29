package com.mfcms.laba.server.net;

import java.nio.channels.SocketChannel;

import com.mfcms.laba.common.services.ServiceCollection.ServiceScope;

public class UserConnectionManager {
    private SocketChannel channel;
    private ServiceScope scope;

    public UserConnectionManager(SocketChannel channel, ServiceScope scope) {
        this.channel = channel;
        this.scope = scope;
    }

    
}
