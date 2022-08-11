package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;


public class RequestHandler implements Runnable {

    private final SocketService socketService;

    private final FileService fileService;

    public RequestHandler(SocketService socketService, FileService fileService) {
        this.socketService = socketService;
        this.fileService = fileService;
    }

    @Override
    public void run() {
        HttpRequest request = new RequestParser().parse(socketService.readRequest());

        if (!fileService.exists(request.getPath())) {
            HttpResponse resp = new HttpResponse(404, "NOT_FOUND", "<h1>Файл не найден!</h1>");
            sendResponse(resp);
            return;
        }

        if (fileService.isDirectory(request.getPath())) {
            HttpResponse resp = new HttpResponse(400, "BAD_REQUEST", "<h1>Вы запросили директорию!</h1>");
            sendResponse(resp);
            return;
        }
        HttpResponse resp = new HttpResponse(200, "OK", fileService.readFile(request.getPath()));
        sendResponse(resp);

        System.out.println("Client disconnected!");
    }
    private void sendResponse(HttpResponse resp) {
        socketService.writeResponse(new ResponseSerializer().serialize(resp));

        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
