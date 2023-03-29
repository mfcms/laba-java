package com.mfcms.laba.server.commands;

import com.mfcms.laba.common.exceptions.UnauthorizedException;
import com.mfcms.laba.common.model.objects.MusicGenre;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.net.requests.NetStatus;
import com.mfcms.laba.server.database.MusicBandManager;

public class RemoveByGenreCommand extends BaseServerCommand {

    private final MusicBandManager musicBandManager;

    public RemoveByGenreCommand(final MusicBandManager musicBandManager) {
        super("removeByGenre", "Removes all elements by genre");
        this.musicBandManager = musicBandManager;
    }

    @Override
    public CommandResponse execute(CommandRequest request) throws UnauthorizedException {
        MusicGenre genre = request.getContext(MusicGenre.class);
        musicBandManager.removebygenre(request.getCredentials(), genre);
        return new CommandResponse(NetStatus.OK);
    }
}
