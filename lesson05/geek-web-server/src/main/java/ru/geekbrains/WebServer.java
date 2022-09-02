package ru.geekbrains;


import ru.geekbrains.handler.MethodHandlerFactory;
import ru.geekbrains.service.ServicesFactory;

import ru.geekbrains.service.interfaces.SocketService;
import ru.geekbrains.utils.ApplicationProperties;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

                SocketService socketService = ServicesFactory.getSocketService(socket);

                new Thread(new RequestHandler(
                        socketService,
                        new RequestParser(),
                        MethodHandlerFactory.createAnnotated(socketService,
                                new ResponseSerializer(),
                                ServicesFactory.getFileService(applicationProperties.getRoot()))

                )).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
