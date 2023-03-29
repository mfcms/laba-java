package com.mfcms.laba.server.commands;

import com.mfcms.laba.common.model.SecuredHolder;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.net.requests.NetStatus;
import com.mfcms.laba.server.database.MusicBandManager;

public class ShowCommand extends BaseServerCommand {
    private final MusicBandManager musicBandManager;

    public ShowCommand(final MusicBandManager musicBandManager) {
        super("show", "вывести справку по доступным командам");
        this.musicBandManager = musicBandManager;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        return new CommandResponse(NetStatus.OK, null,
            musicBandManager.collection().toArray(SecuredHolder[]::new));
    }
}
