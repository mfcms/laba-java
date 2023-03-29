package com.mfcms.laba.server.commands;

import com.mfcms.laba.common.exceptions.UnauthorizedException;
import com.mfcms.laba.common.model.objects.MusicBand;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.net.requests.NetStatus;
import com.mfcms.laba.server.database.MusicBandManager;

public class AddCommand extends BaseServerCommand {
    private final MusicBandManager musicBandManager;

    public AddCommand(final MusicBandManager musicBandManager) {
        super("add", "adds a MusicBand");
        this.musicBandManager = musicBandManager;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws UnauthorizedException {
        MusicBand musicBand = request.getContext(MusicBand.class);
        musicBand.overrideId(musicBandManager.getNextId());
        musicBandManager.put(request.getCredentials(), musicBand);
        return new CommandResponse(NetStatus.OK);
    }
}
