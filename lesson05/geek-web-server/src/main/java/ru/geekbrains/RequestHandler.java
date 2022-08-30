package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.handler.MethodHandler;
import ru.geekbrains.service.interfaces.SocketService;

import java.io.IOException;

public class RequestHandler implements Runnable {

    private MethodHandler methodHandler;

    private RequestParser requestParser;

    private SocketService socketService;

    public RequestHandler() {

    }

    public RequestHandler(SocketService socketService, RequestParser requestParser, MethodHandler methodHandler) {
        this.methodHandler = methodHandler;
        this.requestParser = requestParser;
        this.socketService = socketService;
    }

    @Override
    public void run() {
        HttpRequest request = requestParser.parse(socketService.readRequest());

        methodHandler.handle(request);

        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }

        System.out.println("Client disconnected!");
    }

}
