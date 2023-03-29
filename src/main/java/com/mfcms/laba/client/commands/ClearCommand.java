package com.mfcms.laba.client.commands;

import com.mfcms.laba.client.net.ClientConnectionManager;

public class ClearCommand extends BaseNetClientCommand {
    public ClearCommand(final ClientConnectionManager connectionManager) {
        super(connectionManager, "clear", "delete all collection");
    }
}
