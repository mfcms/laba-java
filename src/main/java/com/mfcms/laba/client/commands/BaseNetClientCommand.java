package com.mfcms.laba.client.commands;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;

public abstract class BaseNetClientCommand extends BaseClientCommand {
    protected ClientConnectionManager client;

    public BaseNetClientCommand(ClientConnectionManager connectionManager, String name, String description,
            final int argumentCount) {
        super(name, description, argumentCount);
        this.client = connectionManager;
    }

    public BaseNetClientCommand(ClientConnectionManager connectionManager, String name, String description) {
        this(connectionManager, name, description, 0);
    }

    protected CommandRequest createRequest(String[] args) throws CommandExecutionException {
        return new CommandRequest(client.getCurrentUser(), this);
    }

    protected void processResponse(CommandResponse response) throws CommandExecutionException {
        if (response.getMessage() != null) {
            throw new CommandExecutionException("Discarded message: " + response.getMessage());
        }
        try{

            var o = response.getContext(Object.class);
            if (o != null) {
                throw new CommandExecutionException("Discarded context: " + o.toString());
            }
        } catch (IllegalStateException e) {}
    }
    
    @Override
    protected boolean executeCore(String[] args) throws CommandExecutionException {
        try {
            Socket socket = client.getSocket();
            var out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(createRequest(args));
            var in = new ObjectInputStream(socket.getInputStream());
            Object received = in.readObject();
            if (received instanceof CommandResponse response) {
                switch (response.getStatus()) {
                    case OK:
                        processResponse(response);
                        break;
                    case UNAUTHORIZED:
                        throw new CommandExecutionException("Unauthorized: " + response.getMessage());
                    case ERROR:
                        throw new CommandExecutionException("Error: " + response.getMessage());
                }
            } else {
                throw new CommandExecutionException("bad response received");
            }
            return true;
        } catch (ClassNotFoundException e) {
            throw new CommandExecutionException(e);
        } catch (IOException e) {
            throw new CommandExecutionException(e);
        }
    }
}
