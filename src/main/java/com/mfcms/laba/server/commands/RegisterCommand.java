
package com.mfcms.laba.server.commands;

import com.mfcms.laba.common.exceptions.UnauthorizedException;
import com.mfcms.laba.common.model.Credentials;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.net.requests.NetStatus;
import com.mfcms.laba.server.database.MusicBandManager;

public class RegisterCommand extends BaseServerCommand {
    private MusicBandManager musicBandManager;

    public RegisterCommand(final MusicBandManager musicBandManager) {
        super("register", "adds a new user to the server and logs in with it");
        this.musicBandManager = musicBandManager;
        requireAuthorization = false;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws UnauthorizedException {
        
        Credentials credentials = request.getContext(Credentials.class);
        if(!musicBandManager.register(credentials)) {
            throw new UnauthorizedException();
        }
        return new CommandResponse(NetStatus.OK, null, credentials);
    }
}