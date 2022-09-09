package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.service.interfaces.FileService;
import ru.geekbrains.service.interfaces.SocketService;

public final class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) {
        PutMethodHandler putHandler = new PutMethodHandler(null, socketService, responseSerializer,fileService);
        PostMethodHandler postHandler = new PostMethodHandler(putHandler, socketService, responseSerializer, fileService);
        return new GetMethodHandler(postHandler, socketService, responseSerializer, fileService);
    }
}
