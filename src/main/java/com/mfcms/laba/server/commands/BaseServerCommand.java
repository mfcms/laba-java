package com.mfcms.laba.server.commands;

import com.mfcms.laba.common.commands.BaseCommand;
import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.exceptions.UnauthorizedException;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;

public abstract class BaseServerCommand extends BaseCommand {
    protected boolean requireAuthorization = true;

    public BaseServerCommand(String name, String description) {
        super(name, description);
    }

    public abstract CommandResponse execute(CommandRequest request) throws IllegalStateException, CommandExecutionException, UnauthorizedException;

    public boolean isRequireAuthorization() {
        return requireAuthorization;
    }
}
