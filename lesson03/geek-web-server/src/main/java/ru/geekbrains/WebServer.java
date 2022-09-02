package ru.geekbrains;

import ru.geekbrains.service.FileServiceImpl;
import ru.geekbrains.service.ServicesFactory;
import ru.geekbrains.service.SocketServiceImpl;
import ru.geekbrains.utils.ApplicationProperties;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {
        ApplicationProperties applicationProperties = new ApplicationProperties("server.properties").readProperties();

        try (ServerSocket serverSocket = new ServerSocket(applicationProperties.getPort())) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                new Thread (
                        RequestHandler.getBuilder()
                        .setSocketService((SocketServiceImpl) ServicesFactory.getSocketService(socket))
                        .setFileService((FileServiceImpl) ServicesFactory.getFileService(applicationProperties.getRoot()))
                        .build()
                ).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
