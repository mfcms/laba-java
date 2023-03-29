package com.mfcms.laba.server.commands;

import com.mfcms.laba.common.exceptions.UnauthorizedException;
import com.mfcms.laba.common.model.Credentials;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.net.requests.NetStatus;
import com.mfcms.laba.server.database.MusicBandManager;

public class LoginCommand extends BaseServerCommand {
    private MusicBandManager musicBandManager;

    public LoginCommand(final MusicBandManager musicBandManager) {
        super("login", "logs in to the server");
        this.musicBandManager = musicBandManager;
        requireAuthorization = false;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws UnauthorizedException {
        
        Credentials credentials = request.getContext(Credentials.class);
        musicBandManager.authorize(credentials);
        return new CommandResponse(NetStatus.OK, null, credentials);
    }
}
