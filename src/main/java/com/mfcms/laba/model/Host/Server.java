package com.mfcms.laba.model.Host;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputFilter.Config;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;


import com.mfcms.laba.model.objects.server.DataManager;
import com.mfcms.laba.service.parsing.object.MusicBandReader;


public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static int port = Config.PORT;
    private static BufferedReader in;
    private static BufferedWriter out;

    private static Thread getUserInputHandler(DataManager dataManager, AtomicBoolean exit){
        return new Thread(() -> {
            Scanner scanner = new Scanner(System.in);

            while (true){
                if(scanner.hasNextLine()){
                    String serverCommand = scanner.nextLine();

                    switch (serverCommand){
                        case "save":
                            save(dataManager);
                            break;
                        case "exit":
                            exit.set(true);
                            return;
                        default:
                            System.out.println("Такой команды нет.");
                            break;
                    }
                }
                else{
                    exit.set(true);
                    return;
                }
            }
        });
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception exception) {
                System.out.println("Не получается спарсить порт. Используется " + port);
            }
        }
        MusicBandReader dataManager;
        try {
            dataManager = new MusicBandReader((Config.ENV_VAR))}
         catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
        
        try{
            server = new ServerSocket(6666);
        try{
            while (()) {
            clientSocket = server.accept();
            ObjectInputStream objectInputStream = new ObjectInputStream(ClientSocket.socket().getInputStream());
                
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                Request<?> request = (Request<?>) in.readObject();
        }
        AtomicBoolean exit = new AtomicBoolean(false);
        getUserInputHandler(dataManager, exit).start();     }
}
    }
}
