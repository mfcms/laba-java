package com.mfcms.laba.client.commands;

import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.model.SecuredHolder;
import com.mfcms.laba.common.model.objects.MusicBand;
import com.mfcms.laba.client.net.ClientConnectionManager;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.services.IOManager;

public class ShowCommand extends BaseNetClientCommand {
    private IOManager io;

    public ShowCommand(final ClientConnectionManager connectionManager, IOManager ioManager) {
        super(connectionManager, "show", "вывести справку по доступным командам");
        this.io = ioManager;
    }

    @Override
    public void processResponse(CommandResponse response) throws CommandExecutionException {
        var bands = response.getContext(SecuredHolder[].class);

        io.out().append("\nPrinting music bands, count ");
        io.out().append(Integer.toString(bands.length));
        io.out().append("\n\n");
        for (SecuredHolder<?> o : bands) {
            if(!(o.getContent() instanceof MusicBand musicBand)) {
                throw new CommandExecutionException("Bad response, collection contains " + o.toString());
            }
            io.out().append(musicBand.getName());
            io.out().append(" (");
            io.out().append(musicBand.getId().toString());
            io.out().append("):\n\t- owner: ");
            io.out().append(o.getOwnerName());
            io.out().append("\n\t- albums count: ");
            io.out().append(Integer.toString(musicBand.getAlbumsCount()));
            io.out().append("\n\t- participants: ");
            io.out().append(Integer.toString(musicBand.getNumberOfParticipants()));
            io.out().append("\n\t- coordinates: ");
            io.out().append(Float.toString(musicBand.getCoordinates().getX()));
            io.out().append(", ");
            io.out().append(Float.toString(musicBand.getCoordinates().getY()));
            io.out().append("\n\t- label: ");
            io.out().append(musicBand.getLabel() == null ? "<null>" : musicBand.getLabel().getName());
            io.out().append("\n\t- genre: ");
            io.out().append(musicBand.getGenre().toString());
            io.out().append("\n\t- created at: ");
            io.out().append(musicBand.getCreationDate().toString());
            io.out().append("\n\t- established at: ");
            io.out().append(musicBand.getEstablishmentDate() == null
                    ? "<null>"
                    : musicBand.getEstablishmentDate().toString());
            io.out().append("\n\n");
        }
        io.out().flush();
    }
}
