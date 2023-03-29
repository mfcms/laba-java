package com.mfcms.laba.common.net.requests;

import com.mfcms.laba.common.commands.BaseCommand;
import com.mfcms.laba.common.model.Credentials;

public class CommandRequest extends ContextHolder {
    private Credentials credentials;
    private String commandName;

    public CommandRequest(Credentials credentials, BaseCommand command) {
        this(credentials, command, null);
    }

    public CommandRequest(Credentials credentials, BaseCommand command, Object context) {
        super(context);
        this.credentials = credentials;
        this.commandName = command.getName();
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String getCommandName() {
        return commandName;
    }
}
