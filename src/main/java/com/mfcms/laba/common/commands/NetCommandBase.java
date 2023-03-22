package com.mfcms.laba.common.commands;

import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.exceptions.CommandInvalidArgumentAmountException;
import com.mfcms.laba.common.net.ConnectionManager;

public abstract class NetCommandBase extends CommandBase {
    private String name;
    private String description;
    private int argumentCount;
    private ConnectionManager connectionManager;

    public NetCommandBase(ConnectionManager connectionManager, String name, String description, final int argumentCount) {
        super(name, description, argumentCount);
        this.connectionManager = connectionManager;
    }

    public NetCommandBase(ConnectionManager connectionManager, String name, String description) {
        this(connectionManager, name, description, 0);
    }

    @Override
    public boolean execute(String[] args)
            throws CommandExecutionException, CommandInvalidArgumentAmountException {
        if(args.length != argumentCount) {
            throw new CommandInvalidArgumentAmountException(name, args, args.length, argumentCount);
        }
        
        // TODO Bogdanov
        //connectionManager.send().writeObject(new );
        //connectionManager.receive().
        return true;
    }

    public boolean executeServer(/* ... */) {
        // TODO -- separate client and server scopes
        // return executeCore();
        // connectionManager.send().writeObject(new );
        // connectionManager.receive().
        return true;
    }

    protected abstract boolean executeCore(String[] args) throws CommandExecutionException;
}
