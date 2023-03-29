package com.mfcms.laba.server.commands;

import com.mfcms.laba.common.exceptions.UnauthorizedException;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.net.requests.NetStatus;
import com.mfcms.laba.server.database.MusicBandManager;

public class ClearCommand extends BaseServerCommand {
    private MusicBandManager musicBandManager;

    public ClearCommand(final MusicBandManager musicBandManager) {
        super("clear", "delete all collection");
        this.musicBandManager = musicBandManager;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws UnauthorizedException {
        musicBandManager.clear(request.getCredentials());
        return new CommandResponse(NetStatus.OK);
    }
}
