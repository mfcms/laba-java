package com.mfcms.laba.server.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import com.mfcms.laba.common.exceptions.CommandExecutionException;
import com.mfcms.laba.common.exceptions.CommandNotFoundException;
import com.mfcms.laba.common.exceptions.UnauthorizedException;
import com.mfcms.laba.common.net.Config;
import com.mfcms.laba.common.net.requests.CommandRequest;
import com.mfcms.laba.common.net.requests.CommandResponse;
import com.mfcms.laba.common.net.requests.NetStatus;
import com.mfcms.laba.common.services.IOManager;
import com.mfcms.laba.server.commands.BaseServerCommand;
import com.mfcms.laba.server.core.ServerCommandRegistry;
import com.mfcms.laba.server.database.MusicBandManager;

public class ServerListener {
    private ServerSocketChannel serverChannel;
    private ServerCommandRegistry commandService;
    private IOManager io;
    private MusicBandManager musicBandManager;

    public ServerListener(ServerCommandRegistry commandService, IOManager ioManager, MusicBandManager musicBandManager) {
        this.commandService = commandService;
        this.io = ioManager;
        this.musicBandManager = musicBandManager;
    }

    public void open() throws IOException {
        openOn(Config.PORT);
    }

    public void openOn(int port) throws IOException {
        io.out().append("\nOpening on port " + port);

        serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(port));

        io.out().append("\n");
        io.out().flush();
    }

    public boolean isOpen() {
        return serverChannel != null && serverChannel.isOpen();
    }

    private static final String[] spinners = "|/-\\".split("");
    private int spin = 0;

    public void process() throws IOException {
        io.out().print("Receiving... " + spinners[spin++] + "\r");
        if (spin >= spinners.length)
            spin = 0;
        while (true) {
            try {
                SocketChannel channel = serverChannel.accept();
                if (channel == null) {
                    return;
                }
                Socket socket = channel.socket();
                io.out().print("> " + socket.getChannel().getRemoteAddress().toString());
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                Object o;
                try {
                    io.out().print(" -> ");
                    o = in.readObject();
                } catch (ClassNotFoundException e) {
                    io.out().print(e.getMessage());
                    e.printStackTrace();
                    return;
                }

                if (!(o instanceof CommandRequest request)) {
                    io.out().print(o.getClass().getSimpleName() + ", not expected, discarding");
                    return;
                }

                CommandResponse response;
                try {
                    BaseServerCommand command = commandService.getCommand(request.getCommandName());
                    io.out().print("command '" + command.getName() + "'");
                    try {
                        if(command.isRequireAuthorization()) {
                            musicBandManager.authorize(request.getCredentials());
                        }
                        response = command.execute(request);
                    } catch (UnauthorizedException e) {
                        io.out().print(" is unauthorized for the operation due to: " + e.getMessage());
                        response = new CommandResponse(NetStatus.UNAUTHORIZED, e.getMessage());
                    } catch (CommandExecutionException e) {
                        io.out().print(" failed to execute: " + e.getMessage());
                        e.printStackTrace();
                        response = new CommandResponse(NetStatus.ERROR, e.getMessage());
                    } catch (IllegalStateException e) {
                        io.out().print(" got malformed request: " + e.getMessage());
                        e.printStackTrace();
                        response = new CommandResponse(NetStatus.ERROR, e.getMessage());
                    } 
                } catch (CommandNotFoundException e) {
                    io.out().print("command '" + request.getCommandName() + "' was not found");
                    response = new CommandResponse(NetStatus.ERROR, e.getMessage());
                }
                
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
                out.flush();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            io.out().println();
        }
    }
}
