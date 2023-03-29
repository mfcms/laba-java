package com.mfcms.laba.server.commands;

import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.exceptions.UnauthorizedException;
import com.mfcms.laba.common.model.objects.MusicBand;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.net.requests.NetStatus;
import com.mfcms.laba.server.database.MusicBandManager;

public class UpdateCommand extends BaseServerCommand {
    private final MusicBandManager musicBandManager;

    public UpdateCommand(final MusicBandManager musicBandManager) {
        super("update", "Updates a band by id");
        this.musicBandManager = musicBandManager;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws CommandExecutionException, UnauthorizedException {
        MusicBand musicBand = request.getContext(MusicBand.class);
        MusicBand oldBand = musicBandManager.get(musicBand.getId());
        if (oldBand == null) {
            throw new CommandExecutionException("No music band found with id " + musicBand.getId());
        }
        musicBand.overrideCreationDate(oldBand.getCreationDate());
        musicBandManager.put(request.getCredentials(), musicBand);
        return new CommandResponse(NetStatus.OK);
    }
}
