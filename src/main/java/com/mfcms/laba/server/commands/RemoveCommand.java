package com.mfcms.laba.server.commands;

import com.mfcms.laba.common.exceptions.UnauthorizedException;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.net.requests.NetStatus;
import com.mfcms.laba.server.database.MusicBandManager;

public class RemoveCommand extends BaseServerCommand {
    private final MusicBandManager musicBandManager;

    public RemoveCommand(final MusicBandManager musicBandManager) {
        super("remove", "Removes by id");
        this.musicBandManager = musicBandManager;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws UnauthorizedException {
        Integer id = request.getContext(Integer.class);
        musicBandManager.remove(request.getCredentials(), id);
        return new CommandResponse(NetStatus.OK);
    }
}
