package com.mfcms.laba.server.commands;

import com.mfcms.laba.common.model.objects.Label;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.net.requests.NetStatus;
import com.mfcms.laba.server.database.MusicBandManager;

public class CountLessThanLabelCommand extends BaseServerCommand {

    private MusicBandManager musicBandManager;

    public CountLessThanLabelCommand(final MusicBandManager musicBandManager) {
        super("countltl", "Counts labels whitch are less than this one");
        this.musicBandManager = musicBandManager;
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        Label label = request.getContext(Label.class);
        Long result = musicBandManager.collection()
                .stream()
                .map(band -> band.getContent().getLabel())
                .filter(l -> l.compareTo(label) < 0)
                .count();
        return new CommandResponse(NetStatus.OK, null, result);
    }
}
