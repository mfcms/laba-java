package com.mfcms.laba.client.commands;

import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.model.objects.Label;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.services.IOManager;

public class CountLessThanLabelCommand extends BaseNetClientCommand {

    private IOManager io;

    public CountLessThanLabelCommand(final ClientConnectionManager connectionManager, final IOManager ioManager) {
        super(connectionManager, "countltl", "Counts labels whitch are less than this one", 1);
        this.io = ioManager;
    }

    @Override
    protected void processResponse(CommandResponse response) throws CommandExecutionException {
        io.out().println("Less than title: " + response.getContext(Long.class));
    }

    @Override
    protected CommandRequest createRequest(String[] args) throws CommandExecutionException {
        return new CommandRequest(client.getCurrentUser(), this, new Label(args[0]));
    }
}
