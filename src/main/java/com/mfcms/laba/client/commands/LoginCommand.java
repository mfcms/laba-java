package com.mfcms.laba.client.commands;

import java.io.IOException;

import com.mfcms.laba.client.parsing.object.CredentialsReader;
import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.model.Credentials;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.services.IOManager;

public class LoginCommand extends BaseNetClientCommand {
    private CredentialsReader credentialsReader;
    private IOManager io;

    public LoginCommand(final ClientConnectionManager connectionManager, final CredentialsReader credentialsReader, final IOManager io) {
        super(connectionManager, "login", "logs in to the server");
        this.credentialsReader = credentialsReader;
        this.io = io;
    }

    @Override
    protected void processResponse(CommandResponse response) throws CommandExecutionException {
        var credentials = response.getContext(Credentials.class);
        client.setCurrentUser(credentials);
        io.out().println("Logged in successfully as " + credentials.getUsername());
    }

    @Override
    protected CommandRequest createRequest(String[] args) throws CommandExecutionException {
        try {
            Credentials credentials = credentialsReader.read();
            return new CommandRequest(null, this, credentials);
        } catch (IOException e) {
            throw new CommandExecutionException(e);
        }
    }
}
