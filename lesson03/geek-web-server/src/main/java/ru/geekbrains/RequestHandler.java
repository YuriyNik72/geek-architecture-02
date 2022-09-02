package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileServiceImpl;
import ru.geekbrains.service.SocketServiceImpl;

import java.io.IOException;

public class RequestHandler implements Runnable {

    private SocketServiceImpl socketServiceImpl;

    private FileServiceImpl fileServiceImpl;
    public RequestHandler(){

    }
//    private final RequestParser requestParser;
//    private final ResponseSerializer responseSerializer;

    public RequestHandler(SocketServiceImpl socketServiceImpl,
                          FileServiceImpl fileServiceImpl) {
        this.socketServiceImpl = socketServiceImpl;
        this.fileServiceImpl = fileServiceImpl;

    }

    @Override
    public void run() {
        HttpRequest req = new RequestParser().parse(socketServiceImpl.readRequest());

        if (!fileServiceImpl.exists(req.getPath())) {
            HttpResponse resp = HttpResponse.getBuilder()
                    .setStatusCode(404)
                    .setStatusText("NOT_FOUND")
                    .setBody("<h1> File not found </h1>")
                    .build();
            sendResponse(resp);
            return;
        }

        if (fileServiceImpl.isDirectory(req.getPath())) {
            HttpResponse resp = HttpResponse.getBuilder()
                    .setStatusCode(400)
                    .setStatusText("BAD_REQUEST")
                    .setBody("<h1> This directory </h1>")
                    .build();
            sendResponse(resp);
            return;
        }

        HttpResponse resp = HttpResponse.getBuilder()
                .setStatusCode(200)
                .setStatusText("OK")
                .setBody("<h1> This directory </h1>")
                .build();
        sendResponse(resp);
        System.out.println("Client disconnected!");
    }
        private void sendResponse(HttpResponse resp){
         socketServiceImpl.writeResponse(new ResponseSerializer().serialize(resp));

        try {
            socketServiceImpl.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }
    public static Builder getBuilder(){
        return new Builder();
    }
    public static class Builder{
        private final RequestHandler handler;

        public Builder() {
           this.handler = new RequestHandler();
        }
        public Builder setSocketService(SocketServiceImpl socketServiceImpl){
            this.handler.setSocketService(socketServiceImpl);
            return this;
        }
        public Builder setFileService(FileServiceImpl fileServiceImpl){
            this.handler.setFileService(fileServiceImpl);
            return this;
        }
        public RequestHandler build(){
            return this.handler;
        }
    }
        private void setFileService(FileServiceImpl fileServiceImpl){
            this.fileServiceImpl = fileServiceImpl;
        }
        private void setSocketService(SocketServiceImpl socketServiceImpl){
            this.socketServiceImpl = socketServiceImpl;
        }

}
