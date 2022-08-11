package ru.geekbrains;

import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;
import ru.geekbrains.utils.ApplicationProperties;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {
        ApplicationProperties applicationProperties = new ApplicationProperties("app.properties").readProperties();

        try (ServerSocket serverSocket = new ServerSocket(applicationProperties.getPort())) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                new Thread(new RequestHandler(new SocketService(socket), new FileService(applicationProperties.getRoot()))).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
